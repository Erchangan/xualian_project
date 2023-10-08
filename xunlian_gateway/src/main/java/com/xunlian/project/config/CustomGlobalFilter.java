package com.xunlian.project.config;


import com.example.xunlian_client_sdk.utils.SignUtils;
import com.xunlian.common.model.InterfaceInfo;
import com.xunlian.common.model.User;
import com.xunlian.common.service.InnerInterfaceInfoService;
import com.xunlian.common.service.InnerUserInterfaceInfoService;
import com.xunlian.common.service.InnerUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

@Component
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {
    @DubboReference
    private InnerUserService innerUserService;
    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;
    @DubboReference
    private InnerInterfaceInfoService innerInterfaceInfoService;

    private static final List<String> IP_WHITE_LIST = Arrays.asList("127.0.0.1");
    public static final String REQUEST_HOME="http://localhost:8181";
    /**
     * 全局过滤
     *
     * @param exchange 拿到请求参数的详细信息
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.输出日志信息
        ServerHttpRequest request = exchange.getRequest();
        String url=REQUEST_HOME+request.getPath().value();
        String method = request.getMethod().toString();
        log.info("请求路径" + url);
        log.info("请求方法" + request.getMethod());
        log.info("请求参数" + request.getQueryParams());
        String sourceAddress = String.valueOf(request.getRemoteAddress().getHostString());
        log.info("请求来源地址" + sourceAddress);
        log.info("请求来源地址" + request.getRemoteAddress());
        ServerHttpResponse response = exchange.getResponse();
        //黑白名单
        if (!IP_WHITE_LIST.contains(sourceAddress)) {
            handlerNoAuth(response);
        }
        //2.权限校验
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String accessKey = headers.getFirst("accessKey");
        String nonce = headers.getFirst("nonce");
        String timestamp = headers.getFirst("timestamp");
        String body = headers.getFirst("body");
        String sign = headers.getFirst("sign");
        User invokeUser = innerUserService.getInvokeUser(accessKey);
        if(invokeUser==null){
            log.info("getInvokeUser error");
            return handlerNoAuth(response);
        }
        //Long currentTime = System.currentTimeMillis();
        //final Long FIVE_MINUTES=60*5L;
        //if((currentTime-Long.parseLong(timestamp))/10>FIVE_MINUTES){
        //    return handlerNoAuth(response);
        //}
        //判断签名是否合法
        String secretKey = invokeUser.getSecretKey();
        String serverSign = SignUtils.genSign(body, secretKey);
        if (sign==null||!sign.equals(serverSign)) {
            return handlerNoAuth(response);
        }
        //检查接口是否存在
        InterfaceInfo interfaceInfo = innerInterfaceInfoService.getInterfaceInfo(url,method);
        if(interfaceInfo==null){
            log.info("getInterfaceInfo error");
            return handlerNoAuth(response);
        }
        int leftCount = innerUserInterfaceInfoService.getLeftCount(invokeUser.getId(),interfaceInfo.getId());
        if(leftCount<0){
            log.info("调用剩余次数不足");
            return handlerNoAuth(response);
        }
        return handlerResponse(exchange, chain,interfaceInfo.getId(),invokeUser.getId());
    }

    public Mono<Void> handlerResponse(ServerWebExchange exchange, GatewayFilterChain chain,long interfaceInfoId,long userId) {
        // 执行chain.filter，并在Mono中处理响应结果
        try {
            //从交换器拿响应对象
            ServerHttpResponse originalResponse = exchange.getResponse();
            //缓冲区工厂，拿到缓存数据
            DataBufferFactory bufferFactory = originalResponse.bufferFactory();
            //拿到响应码
            HttpStatus statusCode = originalResponse.getStatusCode();
            if (statusCode == HttpStatus.OK) {
                //装饰，增强能力
                ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                    //等调用完转发的接口后才会执行
                    @Override
                    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                        log.info("body instanceof Flux: {}", (body instanceof Flux));
                        if (body instanceof Flux) {
                            //拿到真正的body
                            Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                            //往返回值里写数据
                            //拼接字符串
                            return super.writeWith(fluxBody.map(dataBuffer -> {
                                //8.todo 调用成功 接口调用次数+1 invokeCount
                                try {
                                    innerUserInterfaceInfoService.invokeCount(userId,interfaceInfoId);
                                } catch (Exception e) {
                                    log.error("invokeCount error", e);
                                }
                                byte[] content = new byte[dataBuffer.readableByteCount()];
                                dataBuffer.read(content);
                                DataBufferUtils.release(dataBuffer);//释放掉内存
                                // 构建日志
                                StringBuilder sb2 = new StringBuilder(200);
                                List<Object> rspArgs = new ArrayList<>();
                                rspArgs.add(originalResponse.getStatusCode());
                                String data = new String(content, StandardCharsets.UTF_8);//data
                                sb2.append(data);
                                log.info("响应结果： ", data);
                                return bufferFactory.wrap(content);
                            }));
                        } else {
                            //9. 调用失败，返回规范错误码
                            log.error("<--- {} 响应code异常", getStatusCode());
                        }
                        return super.writeWith(body);
                    }
                };
                //设置response 对象为装饰过的
                return chain.filter(exchange.mutate().response(decoratedResponse).build());
            }
            return chain.filter(exchange);//降级处理返回数据
        } catch (Exception e) {
            log.error("网关处理响应异常" + e);
            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    /**
     * 无权限处理
     */
    public Mono<Void> handlerNoAuth(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        return response.setComplete();
    }

    /**
     * 错误处理
     */
    public Mono<Void> handlerInvokeError(ServerHttpResponse response) {
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }
}

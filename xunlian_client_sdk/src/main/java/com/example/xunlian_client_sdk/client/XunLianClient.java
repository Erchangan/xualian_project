package com.example.xunlian_client_sdk.client;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.xunlian_client_sdk.model.User;
import com.example.xunlian_client_sdk.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;

public class XunLianClient {
    //127.0.0.1
    private final String GATEWAY_HOST = "http://127.0.0.1:8090";

    private String accessKey;

    private String secretKey;

    public XunLianClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getName(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get(GATEWAY_HOST + "/api/name/getName", paramMap);
        return result;
    }

    public String postName(User user) {
        String jsonUser = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/name/postName")
                .addHeaders(getHeaders(jsonUser))
                .body(jsonUser)
                .execute();
        String result = httpResponse.body();
        return result;
    }

    public String getRandomImage() {
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/name/randomImages")
                .addHeaders(getHeaders("{}"))
                .execute();
        String result = httpResponse.body();
        return result;
    }

    private Map<String, String> getHeaders(String body) {
        Map<String, String> headers = new HashMap<>();
        headers.put("accessKey", accessKey);
        //headers.put("secretKey", secretKey);不能将秘钥传输
        headers.put("nonce", RandomUtil.randomNumbers(4));
        headers.put("timestamp", String.valueOf(System.currentTimeMillis() / 100));
        headers.put("body", body);
        headers.put("sign", SignUtils.genSign(body, secretKey));
        return headers;
    }
}

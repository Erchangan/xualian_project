package com.xunlian.project;


import com.xunlian.common.service.DemoService;
import com.xunlian.common.service.InnerUserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class XunLianGatewayApplicationTests {
    @DubboReference
    private DemoService demoService;
    @DubboReference
    private InnerUserInterfaceInfoService innerUserInterfaceInfoService;
    @Test
    void testDemo() {
        String result=demoService.sayHello("张三");
        System.out.println(result);
    }
    @Test
    void testInvokeCount(){
        innerUserInterfaceInfoService.invokeCount(3,2);
    }

}

package com.xunlian.project;


import com.xunlian.project.provider.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class XunLianGatewayApplicationTests {
    @DubboReference
    private DemoService demoService;
    @Test
    void testDemo() {
        String result = demoService.sayHello("zhangsan");
        System.out.println(result);
    }

}

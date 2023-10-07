package com.xunlian.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserInterfaceInfoServiceTest {
    @Resource
    UserInterfaceInfoService userInterfaceInfoService;
    @Test
    public void invokeCountTest(){
        userInterfaceInfoService.invokeCount(1,2);

    }
}

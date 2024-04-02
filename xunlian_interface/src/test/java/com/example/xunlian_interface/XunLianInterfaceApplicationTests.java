package com.example.xunlian_interface;

import com.example.xunlian_client_sdk.client.XunLianClient;
import com.example.xunlian_client_sdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XunLianInterfaceApplicationTests {
    @Autowired
    XunLianClient xunLianClient;
}

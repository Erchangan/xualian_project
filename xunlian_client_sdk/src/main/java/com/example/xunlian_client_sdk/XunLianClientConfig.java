package com.example.xunlian_client_sdk;

import com.example.xunlian_client_sdk.client.XunLianClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("xunlian.client")
@ComponentScan
@Data
public class XunLianClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public XunLianClient getXunLianClient() {
        XunLianClient xunLianClient = new XunLianClient(accessKey, secretKey);
        return xunLianClient;
    }
}

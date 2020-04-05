package com.ijavac.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zzyy
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AliPaymentMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(AliPaymentMain9003.class, args);
    }
}
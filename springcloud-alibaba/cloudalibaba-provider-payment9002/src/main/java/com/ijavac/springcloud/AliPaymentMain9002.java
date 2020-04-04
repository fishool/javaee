package com.ijavac.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zzyy
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AliPaymentMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(AliPaymentMain9002.class, args);
    }
}

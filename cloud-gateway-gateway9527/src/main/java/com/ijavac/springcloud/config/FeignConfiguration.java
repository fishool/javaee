package com.ijavac.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


/**
 * feign相互调用过程中 将header传到被调用的fegin中
 *
 * @author 李文龙
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

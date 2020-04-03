package com.ijavac.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zzyy
 * @version 1.0
 * @create 2020/03/06
 *
 * @RefreshScope  具备刷新配置文件的能力
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @Value("${order.info}")
    private String orderInfo;

    //http://localhost:3345/configInfo
    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }

    //http://localhost:3345/orderInfos
    @GetMapping("/orderInfo")
    public String getOrderInfo(){
        return orderInfo;
    }
}

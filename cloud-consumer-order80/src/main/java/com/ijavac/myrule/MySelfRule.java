package com.ijavac.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义负载均衡路由规则类
 *
 * @author zzyy
 * @date 2020/3/6 15:15
 **/
@Configuration
public class MySelfRule {

//    @Bean
//    public IRule myRule() {
//        // 复核判断可用性和性能。
//        return new ZoneAvoidanceRule();
//    }


//    @Bean
//    public IRule myRule() {
//        // 定义为轮训  默认出厂
//        return new RoundRobinRule();
//
//    }

    @Bean
    public IRule myRule() {
        // 定义为随机
        return new RandomRule();

    }

//    @Bean
//    public IRule myRule() {
//        // 定义高可用优先
//        return new BestAvailableRule();
//    }
//


}

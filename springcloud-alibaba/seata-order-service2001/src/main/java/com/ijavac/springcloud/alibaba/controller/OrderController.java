package com.ijavac.springcloud.alibaba.controller;

import com.ijavac.springcloud.alibaba.service.IdGeneratorSnowflake;
import com.ijavac.springcloud.alibaba.service.OrderService;
import com.ijavac.springcloud.entities.CommonResult;
import com.ijavac.springcloud.entities.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zzyy
 * @date 2020/3/8 14:21
 **/
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;
    @Resource
    private IdGeneratorSnowflake idGeneratorSnowflake;

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    @GetMapping("order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }

    /**
     * 生成id,通过雪花算法
     *
     * @return
     */
    @GetMapping("snowflake")
    public String getIDBySnowflake() {
        for (int i = 0; i < 200; i++) {
                System.out.println(idGeneratorSnowflake.snowflakeId());
        }
        return "hello snowflake";
    }
}

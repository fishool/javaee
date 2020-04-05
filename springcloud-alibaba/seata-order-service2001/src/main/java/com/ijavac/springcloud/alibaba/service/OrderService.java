package com.ijavac.springcloud.alibaba.service;

import com.ijavac.springcloud.entities.Order;

/**
 * @author zzyy
 * @date 2020/3/8 13:55
 **/
public interface OrderService {
    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}

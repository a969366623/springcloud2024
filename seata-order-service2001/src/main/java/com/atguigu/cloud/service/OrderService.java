package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Order;

/**
 * @auther jiaronghao
 * @create 2023-12-01 17:52
 */
public interface OrderService {

    /**
     * 创建订单
     */
    void create(Order order);

}
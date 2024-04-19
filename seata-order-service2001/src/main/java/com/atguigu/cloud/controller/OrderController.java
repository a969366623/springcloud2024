package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Order;
import com.atguigu.cloud.resp.R;
import com.atguigu.cloud.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 创建订单
     */
    @PostMapping("/order/create")
    public R create(Order order)
    {
        orderService.create(order);
        return R.success(order);
    }
}
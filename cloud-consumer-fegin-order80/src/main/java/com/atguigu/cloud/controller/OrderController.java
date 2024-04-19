package com.atguigu.cloud.controller;


import cn.hutool.core.date.DateUtil;
import com.atguigu.cloud.apis.PayFeignApi;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.R;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderController {

    @Resource
    private PayFeignApi payFeignApi;

    @PostMapping(value = "feign/pay/add")
    public R addOrder(@RequestBody PayDTO payDTO){
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping(value = "/feign/pay/get/{id}")
    public R getPayInfo(@PathVariable("id")Integer id){
        System.out.println("-------支付微服务远程调用，按照id查询订单支付流水信息");
        R r = null;
        try
        {
            System.out.println("调用开始-----:"+ DateUtil.now());
            r = payFeignApi.getPayInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("调用结束-----:"+DateUtil.now());
            r.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }
        return r;
//        return payFeignApi.getPayInfo(id);
    }

    /**
     * 天然支持负载均衡演示
     * @return
     */
    @GetMapping(value = "/feign/pay/mylb")
    public String mylb() {
        return payFeignApi.mylb();
    }

}

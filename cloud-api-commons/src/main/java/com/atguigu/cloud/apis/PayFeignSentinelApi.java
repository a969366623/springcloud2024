package com.atguigu.cloud.apis;


import com.atguigu.cloud.resp.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider", fallback = PayFeignSentinelApiFallBack.class)
public interface PayFeignSentinelApi {

    @GetMapping("/pay/nacos/get/{orderNo}")
    public R getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}

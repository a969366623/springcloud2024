package com.atguigu.cloud.apis;


import com.atguigu.cloud.resp.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @auther jiaronghao
 * @create 2023-12-01 17:42
 */
@FeignClient(value = "seata-account-service")
public interface AccountFeignApi {

    @PostMapping(value = "/account/decrease")
    public R decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}

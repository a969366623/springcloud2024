package com.atguigu.cloud.apis;


import com.atguigu.cloud.resp.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {


    /**
     * 扣减库存
     * @param productId
     * @param count
     * @return
     */
    @PostMapping(value = "/storage/decrease")
    public R decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count);
}

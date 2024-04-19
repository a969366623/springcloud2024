package com.atguigu.cloud.controller;

import com.atguigu.cloud.resp.R;
import com.atguigu.cloud.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiaronghao
 * @date 2024/4/19 12:32
 * @Description 需求说明：
 */
@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping("/storage/decrease")
    public R decrease(Long productId, Integer count){
        storageService.decrease(productId,count);
        return R.success("扣减库存成功~");
    }
}

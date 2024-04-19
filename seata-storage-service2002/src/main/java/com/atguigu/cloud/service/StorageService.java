package com.atguigu.cloud.service;

/**
 * @author jiaronghao
 * @date 2024/4/19 12:28
 * @Description 需求说明：
 */
public interface StorageService {

    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}

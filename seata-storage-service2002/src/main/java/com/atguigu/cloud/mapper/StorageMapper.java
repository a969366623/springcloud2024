package com.atguigu.cloud.mapper;

import com.atguigu.cloud.entities.Storage;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.common.Mapper;

public interface StorageMapper extends Mapper<Storage> {


    /**
     * 扣减库存
     * @param productId
     * @param count
     */
    public void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
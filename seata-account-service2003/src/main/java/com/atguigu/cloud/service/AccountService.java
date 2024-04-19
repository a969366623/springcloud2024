package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Account;
import org.apache.ibatis.annotations.Param;

/**
 * @author jiaronghao
 * @date 2024/4/19 12:40
 * @Description 需求说明：账户下单扣减余额
 */

public interface AccountService {

    public void decrease(@Param("userId") Long userId, @Param("money") Long money);
}

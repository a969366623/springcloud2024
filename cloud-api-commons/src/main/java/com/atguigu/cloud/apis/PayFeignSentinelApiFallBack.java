package com.atguigu.cloud.apis;

import com.atguigu.cloud.resp.R;
import com.atguigu.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;


@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi {
    @Override
    public R getPayByOrderNo(String orderNo) {
        return R.fail(ReturnCodeEnum.RC500.getCode(),"对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
    }
}

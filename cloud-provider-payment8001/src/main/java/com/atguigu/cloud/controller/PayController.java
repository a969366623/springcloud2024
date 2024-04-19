package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.R;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@Tag(name = "支付微服务模块", description = "支付微服务模块的相关接口")
public class PayController {


    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增", description = "添加支付记录，json串作为参数")
    public R<String> addPay(@RequestBody Pay pay){
        System.out.println(pay.toString());
        int i = payService.add(pay);
        return R.success("成功插入记录，返回值："+i);
    }
    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除", description = "根据id删除支付记录")
    public R<Integer> deletePay(@PathVariable("id") Integer id) {
        int i = payService.delete(id);
        return R.success(i);
    }
    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改", description = "修改支付记录，json串作为参数")
    public R<String> updatePay(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        int i = payService.update(pay);
        return R.success("成功修改记录，返回值："+i);
    }


    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "查询", description = "根据id查询支付记录")
    public R<Pay> getById(@PathVariable("id") Integer id){
        if (id <= 0)    throw new RuntimeException("id不合法");
        try {
            // 测试处fegin的默认调用超时时间
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Pay pay = payService.getById(id);
        return R.success(pay);
    }

    //全部查询getAll作为家庭作业
    @Operation(summary = "查询", description = "查询所有支付记录")
    @GetMapping(value = "/pay/getAll")
    public R<List<Pay>> getAll() {
        List<Pay> list = payService.getAll();
        return R.success(list);
    }


    @Value("${server.port}")
//    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/pay/get/info")
    public String getInfoByConsul(@Value("${atguigu.info}") String atguiguInfo) {
        return "atguiguInfo:"+ atguiguInfo +  "port:" + port + " consul";
    }
}

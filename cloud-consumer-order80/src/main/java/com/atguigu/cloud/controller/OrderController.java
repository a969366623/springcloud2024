package com.atguigu.cloud.controller;


import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.R;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

//    public static final String Payment_URL = "http://localhost:8001";// 先写死，硬编码
    public static final String Payment_URL = "http://cloud-payment-service";// 服务注册中心上的微服务名称
    /**
     * 一般情况下，通过浏览器的地址栏输入url，发送的只能是get请求
     * 我们底层调用的是post方法，模拟消费者发送get请求，客户端消费者
     * 参数可以不添加@RequestBody
     * @param payDTO
     * @return
     */
    @GetMapping("/consumer/pay/add")
    public R addOrder(PayDTO payDTO) {
        return restTemplate.postForObject(Payment_URL + "/pay/add",payDTO,R.class);
    }

    // 删除+修改操作作为家庭作业，O(∩_∩)O。。。。。。。
    @GetMapping("/consumer/pay/get/{id}")
    public R getPayInfo(@PathVariable("id") Integer id){
        return restTemplate.getForObject(Payment_URL + "/pay/get/"+id, R.class, id);
    }


    @GetMapping(value = "/consumer/pay/get/info")
    private String getInfoByConsul() {
        return restTemplate.getForObject(Payment_URL + "/pay/get/info", String.class);
    }


    @GetMapping("/consumer/discovery")
    public String discovery()
    {
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            System.out.println(element);
        }
        System.out.println("===================================");

        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
        }

        return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
    }

}

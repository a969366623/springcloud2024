package com.atguigu.cloud;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//import tk.mybatis.spring.annotation.MapperScan;
@EnableDiscoveryClient
@MapperScan("com.atguigu.cloud.mapper")
public class Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main8001.class, args);
        // vo 负责接受； 而dto一般都是响应
    }
}
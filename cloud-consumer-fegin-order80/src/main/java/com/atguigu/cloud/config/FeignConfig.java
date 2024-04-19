package com.atguigu.cloud.config;


import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {


    @Bean
    public Retryer myRetryer(){
        return Retryer.NEVER_RETRY;
        // 最大请求次数3=（1 default + 2）， 初始间隔时间为100ms，最大重试间隔为1s
//        return new Retryer.Default(100, 1, 3);
    }

/*    // 开启日志
    NONE：默认的，不显示任何日志；
    BASIC：仅记录请求方法、URL、响应状态码及执行时间；
    HEADERS：除了 BASIC 中定义的信息之外，还有请求和响应的头信息；
    FULL：除了 HEADERS 中定义的信息之外，还有请求和响应的正文及元数据。*/
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}

package com.edu_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com"})//因为导入了service_base的包,要用到他的组件就要进行全扫描
//@ComponentScan(basePackages = {"com"})本来这个是代替上面的scanBasePackages = {"com"},但是这个加了报错,不知道为什么
@EnableDiscoveryClient //nacos注册
@EnableFeignClients   //openFeign
public class EduApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}

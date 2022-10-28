package com.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com"})
@EnableDiscoveryClient

@MapperScan("com.aclservice.mapper")
public class ServiceAclApplication {

    /*
       Spring Security 认证授权流程:
           在前端页面登录
           TokenLoginFilter.attemptAuthentication()
           UserDetailsServiceImpl.loadUserByUsername()
           TokenLoginFilter.successfulAuthentication
           TokenAuthenticationFilter.getAuthentication()

           */

    public static void main(String[] args) {
        SpringApplication.run(ServiceAclApplication.class, args);
    }

}

package com.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Author gb
 * @Data 2022/8/6 15:42
 */


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,scanBasePackages = {"com"})
//因为导入了service_base的包,要用到他的组件就要进行全扫描
//@ComponentScan(basePackages = {"com"})本来这个是代替上面的scanBasePackages = {"com"},但是这个加了报错,不知道为什么
@EnableDiscoveryClient
public class OSSApplication {

    public static void main(String[] args) {
        SpringApplication.run(OSSApplication.class, args);
    }
}

package com.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
        常量类,读取配置文件application.properties中的配置
                流程: 因为继承了 InitializingBean接口,所以当项目一启动,也就是spring一加载
                各个变量通过@Value()获取到配置文件中的值,然后就会执行 afterPropertiesSet()
                将各个变量的值赋给公开静态变量,使之开放出去
*/
@Component
public class ConstantPropertiesUtil implements InitializingBean {

        @Value("${aliyun.oss.file.endpoint}")
        private String endpoint;

        @Value("${aliyun.oss.file.keyid}")
        private String keyId;

        @Value("${aliyun.oss.file.keysecret}")
        private String keySecret;


        @Value("${aliyun.oss.file.bucketname}")
        private String bucketName;

        public static String END_POINT;
        public static String ACCESS_KEY_ID;
        public static String ACCESS_KEY_SECRET;
        public static String BUCKET_NAME;


        @Override
        public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
        }

}
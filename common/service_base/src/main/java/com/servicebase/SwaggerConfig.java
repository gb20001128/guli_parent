package com.servicebase;
// 要做的功能就是整合swagger进行接口测试,,访问/swagger-ui.html就可
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration//配置类
@EnableSwagger2 //swagger注解
public class SwaggerConfig {


    //swagger插件
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")   //就是当前接口测试的名,好像是一个在一个网站中,不同的接口测试有不同的名
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/adminss/.*")))//好像是不拿此路径进行接口测试
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("网站-课程中心API文档")                   //接口测试标题
                .description("本文档描述了课程中心微服务接口定义")  //接口测试描述
                .version("1.0")
                .contact(new Contact("java", "http://atguigu.com", "1123@qq.com"))
                .build();
    }
}

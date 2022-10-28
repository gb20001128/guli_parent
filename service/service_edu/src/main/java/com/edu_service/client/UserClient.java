package com.edu_service.client;


import com.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "service-ucenter") //调用的服务名称
@Component
public interface UserClient {

    //根据token获取用户信息
    @GetMapping("/educenter/member/getMemberInfoById/{id}")
     R getMemberInfoById(@PathVariable("id") String id) ;


}

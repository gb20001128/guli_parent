package com.edu_service.client;

import com.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//出事了调用对应的降级方法
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class) //调用的服务名称
@Component
public interface VodClient {

    //根据视频id删除阿里云视频(@PathVariable注解一定要指定参数名称,否则出错)
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
     R removeAlyVideo(@PathVariable("id") String id);



    //删除多个阿里云视频的方法
    @DeleteMapping("/eduvod/video/delete-batch")
     R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}

package com.edu_service.controller;


import com.commonutils.R;
import com.edu_service.client.VodClient;
import com.edu_service.entity.EduVideo;
import com.edu_service.service.EduVideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-02
 */
@RestController
@RequestMapping("/eduservice/video")
//@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;


    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节(同时把里面视频删除)
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {

        //根据小节id获取视频id
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        //如果小节里有视频
        if (!StringUtils.isEmpty(videoSourceId)){
            //根据视频id,远程调用实现视频删除
            vodClient.removeAlyVideo(videoSourceId);
        }
        //删除小节
        videoService.removeById(videoSourceId);

        return R.ok();
    }

    //修改小节 TODO

}


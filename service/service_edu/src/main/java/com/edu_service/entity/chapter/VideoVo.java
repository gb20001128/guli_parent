package com.edu_service.entity.chapter;
//封装章节下的小节的实体类
import lombok.Data;

@Data
public class VideoVo {

    private String id;

    private String title;

    private String videoSourceId;//视频id
}

package com.edu_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edu_service.entity.EduChapter;
import com.edu_service.entity.EduComment;

import java.util.Map;

public interface EduCommentService extends IService<EduComment> {

    Map<String, Object> getPage(Page<EduComment> page,String courseId);
}

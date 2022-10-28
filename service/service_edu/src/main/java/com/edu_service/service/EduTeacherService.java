package com.edu_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu_service.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-08-02
 */
public interface EduTeacherService extends IService<EduTeacher> {

    //分页查询讲师的方法
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);

}

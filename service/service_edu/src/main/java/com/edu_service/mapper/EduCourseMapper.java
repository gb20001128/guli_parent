package com.edu_service.mapper;

import com.edu_service.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu_service.entity.frontvo.CourseWebVo;
import com.edu_service.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-08-07
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

    //根据课程id，编写sql语句查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);

}

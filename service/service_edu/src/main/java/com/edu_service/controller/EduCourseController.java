package com.edu_service.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commonutils.R;
import com.edu_service.entity.EduCourse;
import com.edu_service.entity.EduTeacher;
import com.edu_service.entity.vo.CourseInfoVo;
import com.edu_service.entity.vo.CoursePublishVo;
import com.edu_service.entity.vo.CourseQuery;

import com.edu_service.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eduservice/course")
//@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    //课程列表(带分页与查询)
    @PostMapping("/getCourseList/{current}/{limit}")
    public R getCourseList(@PathVariable long current,@PathVariable long limit,
                           @RequestBody(required = false) CourseQuery courseQuery) {

        //创建page对象
        Page<EduCourse> page = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();


        String title=courseQuery.getTitle();
        String status =courseQuery.getStatus();

        //判断条件值是否为空,如果不为空拼接条件
        if(!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }

        wrapper.orderByDesc("gmt_create");

        courseService.page(page,wrapper);
        long total = page.getTotal();
        List<EduCourse> records = page.getRecords();

        return R.ok().data("total",total).data("list",records);
    }

    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后课程id,为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }


    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }


    //课程最终发布(修改课程状态)
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse=new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程状态为发布状态
        courseService.updateById(eduCourse);
        return R.ok();

    }

    //删除课程
    @DeleteMapping("/delete/{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return R.ok();
    }

}




package com.edu_service.controller;


import com.commonutils.R;
import com.edu_service.entity.subject.OneSubject;
import com.edu_service.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-08-07
 */
@Api(tags="课程分类管理")
//@CrossOrigin //跨域
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {


    @Autowired
    private EduSubjectService subjectService;


    //添加课程分类
    //获取上传过来的文件,把文件内容读取出来
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {

            //获取上传的excel文件
           subjectService.saveSubject(file,subjectService);

        return R.ok();
    }


    //课程分类列表（树形）
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }

}


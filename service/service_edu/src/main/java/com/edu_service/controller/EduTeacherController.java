package com.edu_service.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commonutils.R;
import com.edu_service.entity.EduTeacher;
import com.edu_service.entity.vo.TeacherQuery;
import com.edu_service.service.EduTeacherService;
import com.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * 讲师 前端控制器
 * @since 2020-02-24
 */

@Api(tags="讲师管理") //为此controller在swagger中起名,看着更清楚
@RestController
@RequestMapping("/eduservice/teacher")
//@CrossOrigin
public class EduTeacherController {



    @Autowired
    private EduTeacherService teacherService;



    //1 查询讲师表所有数据(rest风格)
    @ApiOperation(value = "所有讲师列表")//为此方法在swagger中起名,看着更清楚
    @GetMapping("findAll")
    public R findAllTeacher() {

        //出现异常抛出自定义异常,并使用自定义异常处理(演示自定义异常处理和日志输出用的)
        try {
            //int a = 10/0;
        }catch(Exception e) {
            throw new GuliException(20001,"出现自定义异常");
        }



        //调用service的方法实现查询所有的操作
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }


    //2 逻辑删除讲师的方法
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)//为此参数在swagger中起名,看着更清楚
                           @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }

    }

    //3 分页查询讲师的方法(current: 当前页,limit: 每页记录数)
    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);


        //调用方法实现分页
        //调用方法时候,底层封装,把分页所有数据封装到pageTeacher对象里面
        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合

//        Map map = new HashMap();
//        map.put("total",total);
//        map.put("rows",records);
//        return R.ok().data(map);

        return R.ok().data("total",total).data("rows",records);
    }

    //4 条件查询带分页的方法
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql,不过我们不用
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //判断条件值是否为空,如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        teacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal();//总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    //添加讲师接口的方法(注意在swagger接口文档中写参时,不要写id、创建时间、修改时间,因为都已经配置自动生成)
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //根据讲师id进行查询
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id) {
        System.out.println("查询了"+id);
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher",eduTeacher);
    }


    //讲师修改功能
    @PutMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {

        boolean flag = teacherService.updateById(eduTeacher);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}


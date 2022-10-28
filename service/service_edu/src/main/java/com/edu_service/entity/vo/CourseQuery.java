package com.edu_service.entity.vo;

//查询讲师时,封装前端传过来的讲师参数的实体类
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseQuery {

    private String title;

    private String status;
}

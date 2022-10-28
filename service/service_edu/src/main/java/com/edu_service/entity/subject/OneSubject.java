package com.edu_service.entity.subject;
//课程类别中一级分类的实体类
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//一级分类
@Data
public class OneSubject {


    private String id;
    private String title;

    //一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}

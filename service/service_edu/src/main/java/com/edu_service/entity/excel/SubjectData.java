package com.edu_service.entity.excel;
//excel课程分类文件对应的实体类
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;



@Data
public class SubjectData {


    //这是excel的表头,并且是第一列(有需要的话还可以指定此表头名)
    @ExcelProperty(index = 0)
    private String oneSubjectName;


    //这是excel的表头,并且是第二列(有需要的话还可以指定此表头名)
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}

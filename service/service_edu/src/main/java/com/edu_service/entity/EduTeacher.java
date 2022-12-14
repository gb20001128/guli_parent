package com.edu_service.entity;
//讲师的实体类
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 讲师
 * @since 2022-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)//重写equals()和hashCode(),false的意思是两个方法在对比的时候,只比较此类的属性
                                     //true的意思是两个方法在对比的时候,还要比较父类的属性相不相等

@Accessors(chain = true)             //采用链式编程,比如可以 new EduTeacher().setXXX.setYYY()

@ApiModel(value="EduTeacher对象", description="讲师") //对象的别名和描述
public class EduTeacher implements Serializable {

    private static final long serialVersionUID = 1L;


    //为此属性在swagger中起别名
    @ApiModelProperty(value = "讲师ID")
    //指定此属性是主键,ID_WORKER_STR意思是采用雪花算法为字符串id自动生成(ID_WORKER是采用雪花算法为long型id自动生成)
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师简介")
    private String intro;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    private String career;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "讲师头像")
    private String avatar;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除, 0（false）未删除")
    //指明用于逻辑删除的字段(注意类型是Boolean)
    @TableLogic
    private Boolean isDeleted;

    @ApiModelProperty(value = "创建时间")
    //创建的时候,自动创建时间,依赖自动填充类
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    //更新的时候,自动更新时间,依赖自动填充类
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

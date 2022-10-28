package com.edu_service.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.edu_service.entity.EduSubject;
import com.edu_service.entity.excel.SubjectData;
import com.edu_service.service.EduSubjectService;
import com.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {


    /*
       在这有一个问题要注意: 这个Excel的的监听器里面要进行数据库的操作,需要有EduSubjectService的支持
                          但是此监听器是需要new出来的,也就是说这个监听器不能交给Spring管理
                          不能交给spring管理也就不能够注入EduSubjectService,
                          所以我们解决的办法就是在此监听器的构造方法中传入EduSubjectService参数
                         这样就能进行数据库的操作
       */


    public EduSubjectService subjectService;


    public SubjectExcelListener() {}

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }





    //读取excel内容,一行一行进行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        //一行一行读取,每次读取有两个值,第一个值一级分类,第二个值二级分类

        //判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());

        //没有相同一级分类,进行添加
        if(existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());//一级分类名称
            subjectService.save(existOneSubject);
        }

        //获取一级分类id值
        String pid = existOneSubject.getId();

        //添加二级分类
        //判断在一个一级分类下,二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);

        //没有相同二级分类,进行添加
        if(existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            subjectService.save(existTwoSubject);
        }
    }




    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService,String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }




    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }







    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

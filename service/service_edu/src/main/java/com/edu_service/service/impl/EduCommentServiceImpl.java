package com.edu_service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu_service.entity.EduChapter;
import com.edu_service.entity.EduComment;
import com.edu_service.entity.EduCourse;
import com.edu_service.mapper.EduChapterMapper;
import com.edu_service.mapper.EduCommentMapper;
import com.edu_service.service.EduChapterService;
import com.edu_service.service.EduCommentService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author gb
 * @Data 2022/8/16 11:34
 */

@Service
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    @Override
    public Map<String, Object> getPage(Page<EduComment> page,String courseId) {

        QueryWrapper<EduComment> wrapper=new QueryWrapper<>();

        wrapper.eq("course_id",courseId);
        baseMapper.selectPage(page,wrapper);

        List<EduComment> records = page.getRecords();
        long current = page.getCurrent();
        long pages = page.getPages();
        long size = page.getSize();
        long total = page.getTotal();
        boolean hasNext = page.hasNext();//下一页
        boolean hasPrevious = page.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }
}

package com.edu_service.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.commonutils.JwtUtils;
import com.commonutils.R;
import com.edu_service.client.UserClient;
import com.edu_service.entity.EduComment;
import com.edu_service.service.EduCommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author gb
 * @Data 2022/8/16 11:37
 */

@RestController
@RequestMapping("/eduservice/commentfront")
//@CrossOrigin
@Api(tags = "前台评论")
public class CommentFrontController {

    @Autowired
    EduCommentService commentService;

    @Autowired
    UserClient userClient;


    @GetMapping("/getComment/{courseId}")
    public R getCommentByCourseId(@PathVariable String courseId){

        QueryWrapper<EduComment>  queryWrapper=new QueryWrapper<>();

        queryWrapper.eq("course_id",courseId);
        List<EduComment> list = commentService.list(queryWrapper);
        return R.ok().data("list",list);

    }

    @GetMapping("/getCommentPage/{courseId}/{current}/{limit}")
    public R getCommentPage(@PathVariable String courseId,
                            @PathVariable long current,
                            @PathVariable long limit){

        Page<EduComment> page=new Page<>(current,limit);


        Map<String, Object> map = commentService.getPage(page, courseId);

        return R.ok().data(map);

    }

    @PostMapping("/publishComment")
    public R publishComment(@RequestBody EduComment eduComment){
        boolean save = commentService.save(eduComment);
        if (save)
            return R.ok();
        else
            return R.error();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        String id = JwtUtils.getMemberIdByJwtToken(request);
        R memberInfo = userClient.getMemberInfoById(id);
        return memberInfo;
    }


}

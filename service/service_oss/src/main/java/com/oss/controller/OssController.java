package com.oss.controller;

import com.commonutils.R;
import com.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author gb
 * @Data 2022/8/6 16:18
 */

@Api(tags="阿里云文件管理")
//@CrossOrigin //跨域
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 头像上传
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload( @ApiParam(name = "file", value = "文件", required = true)
                     @RequestParam("file") MultipartFile file) {

        System.out.println("文件大小"+file.getSize());

        String uploadUrl = ossService.uploadAvatar(file);

        //返回r对象
        return R.ok().message("文件上传成功").data("url", uploadUrl);
    }
}

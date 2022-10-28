package com.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GetObjectRequest;
import com.oss.service.OssService;
import com.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Description
 * @Author gb
 * @Data 2022/8/6 16:19
 */

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadAvatar(MultipartFile file) {

        //获取阿里云存储相关常量
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        //判断oss实例是否存在：如果不存在则创建,如果存在则获取
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

        if (!ossClient.doesBucketExist(bucketName)) {
            //创建bucket
            ossClient.createBucket(bucketName);
            //设置oss实例的访问权限: 公共读
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }

        try {

            //获取上传文件流
            InputStream inputStream = file.getInputStream();

            //构建日期路径：如guli_edu/avatar/2022/08/15/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd");

            //文件名：uuid+文件名.扩展名
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString().replaceAll("-", "");
            String newName = fileName + "-" + original;

            //文件路径
            String avatarPath = "guli_edu/avatar/" + filePath + "/" + newName;

            //文件上传至阿里云
            ossClient.putObject(bucketName, avatarPath, inputStream);


            //返回头像的url地址
            return "https://" + bucketName + "." + endPoint + "/" + avatarPath;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ossClient != null) {
                //关闭oss客户端
                ossClient.shutdown();
            }
        }

    }
}

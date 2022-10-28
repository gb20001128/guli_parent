package com.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Description
 * @Author gb
 * @Data 2022/8/10 16:18
 */
public class InitVod {

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {

        String regionId = "cn-shanghai"; // 点播服务接入区域,就是上海

        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);

        DefaultAcsClient client = new DefaultAcsClient(profile);

        return client;
    }
}

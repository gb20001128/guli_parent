package com.msm_service.service;

import java.util.Map;

public interface MsmService {


    //发送短信的方法(我的)
    boolean sendShortMessage(String code, String phone);

    //发送短信的方法(老师的)
    boolean send(Map<String, Object> param, String phone);
}

package com.msm_service.controller;

import com.commonutils.R;
import com.msm_service.service.MsmService;
import com.msm_service.utils.HttpUtils;
import com.msm_service.utils.RandomUtil;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/edumsm/msm")
//@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;





    //发送短信的方法(我的)
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {

        //1 从redis获取验证码,如果已存在说明验证码未过期
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return R.ok();
        }

        //2 如果redis获取不到,进行阿里云短信发送
        code = RandomUtil.getFourBitRandom();          //生成随机值,当做验证码
        //调用service发送短信的方法
        boolean isSend = msmService.sendShortMessage(code,phone);
        if(isSend) {
            //发送成功,把发送成功验证码放到redis里面,并设置5分钟有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败");
        }
    }




    //发送短信的方法(老师教的方法,我没用)
    @GetMapping("send1/{phone}")
    public R sendMsm1(@PathVariable String phone) {
        //1 从redis获取验证码,如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //2 如果redis获取不到,进行阿里云发送
        //生成随机值,传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service发送短信的方法
        boolean isSend = msmService.send(param,phone);
        if(isSend) {
            //发送成功,把发送成功验证码放到redis里面
            //设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败");
        }
    }

}

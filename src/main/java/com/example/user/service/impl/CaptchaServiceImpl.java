package com.example.user.service.impl;

import com.example.component.MailComponent;
import com.example.globalUitls.captcha.CaptchaUtil;
import com.example.user.dto.CaptchaDTO;
import com.example.user.dto.VerificationCode;
import com.example.user.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/26 下午10:50
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private MailComponent mail;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean sendCaptcha(CaptchaDTO captchaDTO) throws Exception {
        String redisKey = "captcha:" + captchaDTO.getFingerprint();
        Object object = redisTemplate.opsForValue().get(redisKey);
        if (object == null) {
            VerificationCode verificationCode = new VerificationCode();
            verificationCode.setTotal(1);
            HashMap<String, Integer> hashMap = new HashMap<>();
            hashMap.put(captchaDTO.getEmail(), 1);
            verificationCode.setCode(CaptchaUtil.generateNumericCaptcha(6));
            verificationCode.setSendTable(hashMap);
            //存储到redis
            redisTemplate.opsForValue().set(redisKey, verificationCode, 1L, TimeUnit.SECONDS);
            return true;
        }


        return false;
    }
}

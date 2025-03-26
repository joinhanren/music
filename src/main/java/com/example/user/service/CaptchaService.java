package com.example.user.service;

import com.example.user.dto.CaptchaDTO;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/26 下午10:49
 */
public interface CaptchaService {


    public boolean sendCaptcha(CaptchaDTO captchaDTO) throws Exception;


}

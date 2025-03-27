package com.example.user.service;

import com.example.user.dto.CaptchaDTO;
import com.example.user.enums.CaptchaStatus;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/26 下午10:49
 */
public interface CaptchaService {


    public CaptchaStatus sendCaptcha(CaptchaDTO captchaDTO);


}

package com.example.user.controller;

import com.example.user.dto.CaptchaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/26 下午10:26
 */
@Controller
public class CaptchaController {


    @PostMapping(value = "/send-captcha", consumes = "application/json")
    public ResponseEntity<String> sendCaptcha(@RequestBody CaptchaDTO captchaDTO) {

        return null;
    }


}

package com.example.user.controller;

import com.example.user.dto.CaptchaDTO;
import com.example.user.enums.CaptchaStatus;
import com.example.user.service.CaptchaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private CaptchaService captchaService;

    @PostMapping(value = "/send-captcha", consumes = "application/json")
    public ResponseEntity<String> sendCaptcha(@Valid @RequestBody CaptchaDTO captchaDTO) {
        try {
            CaptchaStatus captchaStatus = captchaService.sendCaptcha(captchaDTO);
            return ResponseEntity.status(captchaStatus.getCode()).body(captchaStatus.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CaptchaStatus.SERVER_ERROR.getMessage());
        }
    }


}

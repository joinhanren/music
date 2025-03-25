package com.example.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/25 下午10:33
 */
@Controller
public class RegisterController {


    @PostMapping("/register")
    public ResponseEntity<String> register() {


        return null;
    }


}

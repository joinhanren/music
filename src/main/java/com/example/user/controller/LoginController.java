package com.example.user.controller;

import com.example.user.dto.LoginUserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/25 下午10:25
 */

@Controller
public class LoginController {


    @GetMapping("/")
    public String loginPage() {
        return "index";
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginUserDTO loginUser) {

        return null;
    }


}

package com.example.user.controller;

import com.example.user.dto.RegisterUserDTO;
import com.example.user.enums.RegisterStatus;
import com.example.user.service.RegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/25 下午10:33
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        RegisterStatus registerStatus = registerService.register(registerUserDTO);
        return ResponseEntity.status(registerStatus.getCode()).body(registerStatus.getMessage());
    }


}

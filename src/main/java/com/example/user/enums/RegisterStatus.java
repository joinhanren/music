package com.example.user.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/28 下午9:45
 */
@Getter
public enum RegisterStatus {

    SUCCESS(HttpStatus.OK.value(), "注册成功"),
    FAILED(HttpStatus.BAD_REQUEST.value(), "注册失败"),
    USER_EXISTS(HttpStatus.CONFLICT.value(), "用户已存在"),
    CODE_ERROR(HttpStatus.UNAUTHORIZED.value(), "验证码错误");

    private final int code;

    private final String message;


    RegisterStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

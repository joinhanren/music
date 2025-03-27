package com.example.user.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/27 下午8:38
 */
@Getter
public enum CaptchaStatus {
    SUCCESS(HttpStatus.OK.value(), "验证码发送成功！"),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS.value(), "发送频率过高，请稍后再试！"),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "邮箱未找到，请检查输入！"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "请求无效！"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "验证码发送失败，请稍后重试！");

    private final int code;
    private final String message;


    CaptchaStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

}

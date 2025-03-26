package com.example.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/26 下午10:28
 */
@Data
public class CaptchaDTO {

    @NotBlank(message = "邮箱不能为空！")
    @Email(message = "邮箱格式不正确！")
    private String email;

    @NotBlank(message = "指纹不能为空")
    private String fingerprint;

}

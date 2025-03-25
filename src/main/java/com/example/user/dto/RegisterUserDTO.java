package com.example.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/25 下午10:56
 */
@Data
public class RegisterUserDTO {

    @NotBlank(message = "用户名不能为空！")
    private String username;

    @NotBlank(message = "邮箱不能为空！")
    @Email(message = "邮箱格式不正确！")
    private String email;

    @NotBlank(message = "密码不能为空！")
    private String password;

    @NotBlank(message = "验证码不能为空！")
    private String captcha;


}

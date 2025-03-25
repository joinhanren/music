package com.example.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/25 下午10:16
 */
@Data
public class LoginUserDTO {

    @NotBlank(message = "账号不能为空！")
    @Email(message = "邮箱格式不正确！")
    private String username;

    @NotBlank(message = "密码不能为空！")
    private String password;


}

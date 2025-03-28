package com.example.user.service;

import com.example.user.dto.RegisterUserDTO;
import com.example.user.enums.RegisterStatus;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/28 下午8:55
 */
public interface RegisterService {


    public RegisterStatus register(RegisterUserDTO registerUserDTO);


}

package com.example.user.service.impl;

import com.example.user.dto.RegisterUserDTO;
import com.example.user.dto.VerificationCode;
import com.example.user.enums.RegisterStatus;
import com.example.user.model.User;
import com.example.user.repository.UserRepository;
import com.example.user.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/28 下午8:55
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public RegisterStatus register(RegisterUserDTO registerUserDTO) {
        User user = userRepository.findUserByEmail(registerUserDTO.getEmail());
        if (null != user) {
            return RegisterStatus.USER_EXISTS;
        }
        String redisKey = "captcha:" + registerUserDTO.getFingerprint();
        VerificationCode code = (VerificationCode) redisTemplate.opsForValue().get(redisKey);
        if (!code.getCode().equals(registerUserDTO.getCaptcha())) {
            return RegisterStatus.CODE_ERROR;
        }
        user = new User();
        user.setEmail(registerUserDTO.getEmail());
        user.setUsername(registerUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        userRepository.save(user);
        return RegisterStatus.SUCCESS;
    }
}

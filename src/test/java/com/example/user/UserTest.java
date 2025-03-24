package com.example.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/24 下午10:42
 */
@SpringBootTest
public class UserTest {


    @Test
    public void testBCryptPasswordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123");
        System.out.println(encode);
        boolean matches = bCryptPasswordEncoder.matches("123", encode);
        System.out.println(matches);
    }

}

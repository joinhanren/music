package com.example.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/24 下午10:42
 */
@SpringBootTest
public class UserTest {


    @Test
    public void testBCryptPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123");
        String encode1 = bCryptPasswordEncoder.encode("123");
        System.out.println(encode);
        System.out.println(encode1);
        boolean matches = bCryptPasswordEncoder.matches("123", encode);
        System.out.println(matches);
    }


    @Test
    public void testJwt() {
        String sign = JWT.create()
                .withSubject("aaa")
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .withClaim("aa", "bb")
                .sign(Algorithm.HMAC256("miyao"));
        System.out.println(sign);
    }

}

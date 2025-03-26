package com.example.globalUitls.captcha;

import java.security.SecureRandom;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/26 下午11:09
 */
public class CaptchaUtil {

    private static final String DIGITS = "0123456789";
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 生成指定长度的纯数字验证码
     *
     * @param length 验证码长度
     * @return 生成的验证码
     */
    public static String generateNumericCaptcha(int length) {
        return generateCaptcha(DIGITS, length);
    }

    /**
     * 生成指定长度的字母+数字验证码
     *
     * @param length 验证码长度
     * @return 生成的验证码
     */
    public static String generateAlphanumericCaptcha(int length) {
        return generateCaptcha(ALPHANUMERIC, length);
    }

    /**
     * 通用验证码生成方法
     *
     * @param characters 可选字符集
     * @param length     验证码长度
     * @return 生成的验证码
     */
    private static String generateCaptcha(String characters, int length) {
        StringBuilder captcha = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            captcha.append(characters.charAt(RANDOM.nextInt(characters.length())));
        }
        return captcha.toString();
    }


}

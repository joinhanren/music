package com.example.user.service.impl;

import com.example.component.MailComponent;
import com.example.globalUitls.captcha.CaptchaUtil;
import com.example.user.dto.CaptchaDTO;
import com.example.user.dto.VerificationCode;
import com.example.user.enums.CaptchaStatus;
import com.example.user.service.CaptchaService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/26 下午10:50
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private MailComponent mail;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public CaptchaStatus sendCaptcha(CaptchaDTO captchaDTO) {
        String redisKey = "captcha:" + captchaDTO.getFingerprint();
        VerificationCode code = (VerificationCode) redisTemplate.opsForValue().get(redisKey);
        String captcha = CaptchaUtil.generateNumericCaptcha(6);
        String codeText = STR. "<h3>您的验证码是： \{ captcha }</h3><p>有效期5分钟，请勿泄露。</p>" ;
        String suject = "注册验证码";
        // 如果 Redis 中没有验证码信息，则初始化
        if (code == null) {
            saveNewCaptcha(redisKey, captchaDTO, captcha);
            try {
                mail.sendHtmlMail(captchaDTO.getEmail(), suject, codeText);
            } catch (MessagingException e) {
                redisTemplate.delete(redisKey);
                return CaptchaStatus.EMAIL_NOT_FOUND;
            }
            return CaptchaStatus.SUCCESS;
        }
        //判断发送间隔时间
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - code.getCurrentTimeMillis() < 1000 * 60) {
            return CaptchaStatus.TOO_MANY_REQUESTS;
        }
        VerificationCode clone = code.clone();
        // 检查发送次数是否超限
        if (code.getTotal() >= 10) {
            return CaptchaStatus.TOO_MANY_REQUESTS;
        }
        // 获取当前邮箱的发送次数
        Map<String, Integer> sendTable = code.getSendTable();
        Integer emailCount = sendTable.getOrDefault(captchaDTO.getEmail(), 0);

        // 允许最多 5 次
        if (emailCount >= 5) {
            return CaptchaStatus.TOO_MANY_REQUESTS;
        }
        // 更新验证码数据
        sendTable.put(captchaDTO.getEmail(), emailCount + 1);
        code.setTotal(code.getTotal() + 1);
        code.setCode(captcha);
        code.setCurrentTimeMillis(currentTimeMillis);
        // 存入 Redis
        redisTemplate.opsForValue().set(redisKey, code, 5L, TimeUnit.MINUTES);
        // send email advise
        try {
            mail.sendHtmlMail(captchaDTO.getEmail(), "注册验证码", codeText);
        } catch (MessagingException messagingException) {
            //rollback data
            redisTemplate.opsForValue().set(redisKey, clone, 5L, TimeUnit.MINUTES);
            return CaptchaStatus.EMAIL_NOT_FOUND;
        }
        return CaptchaStatus.SUCCESS;
    }

    /**
     * 生成新的验证码并存入 Redis
     */
    private boolean saveNewCaptcha(String redisKey, CaptchaDTO captchaDTO, String code) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setTotal(1);
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(captchaDTO.getEmail(), 1);
        verificationCode.setCode(code);
        verificationCode.setSendTable(hashMap);
        verificationCode.setCurrentTimeMillis(System.currentTimeMillis());
        // 存入 Redis
        redisTemplate.opsForValue().set(redisKey, verificationCode, 5L, TimeUnit.MINUTES);
        return true;
    }


}

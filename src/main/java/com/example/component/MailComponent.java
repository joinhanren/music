package com.example.component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author : join
 * @Description :
 * Date : 2025/3/23 下午6:03
 */
@Component
public class MailComponent {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment environment;

    /**
     * @param receiver 接收者
     * @param subject  主题
     * @param text     文本内容
     * @throws MessagingException
     */
    public void sendSimpleMail(String receiver, String subject, String text) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(environment.getProperty("spring.mail.username"));
        messageHelper.setSubject(subject);
        messageHelper.setTo(receiver);
        messageHelper.setText(text, false);
        messageHelper.setSentDate(new Date());
        mailSender.send(mimeMessage);
    }

    /**
     * @param receiver
     * @param subject
     * @param htmlContent
     * @throws MessagingException
     */
    public void sendHtmlMail(String receiver, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(environment.getProperty("spring.mail.username")); // 发送者
        helper.setTo(receiver); // 接收者
        helper.setSubject(subject); // 主题
        helper.setSentDate(new Date());
        helper.setText(htmlContent, true); // true 表示支持 HTML
        mailSender.send(message);
    }


}

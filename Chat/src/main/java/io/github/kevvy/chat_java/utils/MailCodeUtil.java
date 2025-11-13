package io.github.kevvy.chat_java.utils;

import io.github.kevvy.chat_java.entity.constants.Constants;
import io.github.kevvy.chat_java.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * 邮箱验证码通用工具
 */
@Component
public class MailCodeUtil {

    private final JavaMailSender mailSender;
    private final RedisUtil r;

    @Value("${from.mail}")
    private String from;

    public MailCodeUtil(JavaMailSender mailSender ,RedisUtil redisUtil) {
        this.mailSender = mailSender;

        this.r = redisUtil;
    }


    /** 发送验证码邮件（带Redis防重复与过期） */
    public String send(String email, String subject) {
        if (r.hasKey(Constants.REDIS_KEY_CHECK_LOCK + email)) {
            return "发送太频繁，请稍后再试";
        }
        String code = StringUtil.randomDigits(6);

        // 存验证码（5分钟）
        r.set( Constants.REDIS_KEY_CHECK_CODE + email, code,Constants.REDIS_TIME_1MIN*5);

        // 发送
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(email);
        msg.setSubject("【NoriChat】" + (subject == null ? "验证码" : subject + "验证码"));
        msg.setText("您的验证码是：" + code + "，5分钟内有效。若非本人操作请忽略。");
        mailSender.send(msg);

        // 设置60s发送锁
        r.set(Constants.REDIS_KEY_CHECK_LOCK + email, "1", Constants.REDIS_TIME_1MIN);
        return null;
    }

    /** 校验验证码 */
    public boolean verify(String email, String inputCode, boolean invalidateOnSuccess) {
        Object cache = r.get(Constants.REDIS_KEY_CHECK_CODE + email);
        if (cache == null) return false;
        boolean ok = cache.toString().equals(inputCode);
        if (ok && invalidateOnSuccess) r.delete(Constants.REDIS_KEY_CHECK_CODE + email);
        return ok;
    }
}


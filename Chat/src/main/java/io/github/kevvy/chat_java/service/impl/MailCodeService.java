package io.github.kevvy.chat_java.service.impl;


import io.github.kevvy.chat_java.utils.MailCodeUtil;
import org.springframework.stereotype.Service;

@Service
public class MailCodeService {

    private final MailCodeUtil mailCodeUtil;


    public MailCodeService(MailCodeUtil mailCodeUtil) {
        this.mailCodeUtil = mailCodeUtil;
    }

    public String sendRegisterCode(String email) {
        return mailCodeUtil.send(email, "注册验证码");
    }

    public boolean checkCode(String email, String code) {
        return mailCodeUtil.verify(email, code, true);
    }
}


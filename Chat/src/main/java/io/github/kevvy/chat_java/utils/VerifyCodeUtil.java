package io.github.kevvy.chat_java.utils;


import java.security.SecureRandom;

/**
 * 生成随机验证数字
 */
public class VerifyCodeUtil {
    private static final SecureRandom R = new SecureRandom();
    public static String sixDigits() {
        int n = R.nextInt(900_000) + 100_000; // 100000-999999
        return String.valueOf(n);
    }
}


package io.github.kevvy.chat_java.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Map;

public class StringUtil {
    private static final SecureRandom RANDOM = new SecureRandom();
    //每位随机生成一下数字的其中一个，可以加上字母
    private static final char[] DIGITS = "0123456789".toCharArray();
    /**
     * 判断字符串是否为空
     * null、""、"   " 都会返回 true
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 判断字符串是否非空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断多个字符串中是否存在空值
     */
    public static boolean hasEmpty(String... strs) {
        if (strs == null) return true;
        for (String s : strs) {
            if (isEmpty(s)) return true;
        }
        return false;
    }

    /**
     * 安全地比较两个字符串是否相等（防止空指针）
     */
    public static boolean equals(String a, String b) {
        if (a == null) return b == null;
        return a.equals(b);
    }

    /**
     * 判断字符串是否为数字
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) return false;
        return str.matches("\\d+");
    }

    /**
     * 将首字母转为大写
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 将首字母转为小写
     */
    public static String uncapitalize(String str) {
        if (isEmpty(str)) return str;
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 限制字符串长度，超出部分用 "..." 省略
     */
    public static String limit(String str, int maxLength) {
        if (isEmpty(str) || str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength) + "...";
    }

    /**
     * 判断字符串是否包含子串（忽略大小写）
     */
    public static boolean containsIgnoreCase(String str, String search) {
        if (str == null || search == null) return false;
        return str.toLowerCase().contains(search.toLowerCase());
    }

    /**
     * 判断集合、Map 是否为空（附带功能）
     */
    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> m) {
        return m == null || m.isEmpty();
    }

    /**
     * 生成指定长度的随机数字字符串（只包含 0-9,由DIGITS定义）
     * 例如：length=6  ->  "493028"
     */
    public static String randomDigits(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length 必须 > 0");
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(DIGITS.length);
            sb.append(DIGITS[index]);
        }
        return sb.toString();
    }

    /**
     * 生成指定长度的随机数字字符串（d 自定义想要生成的字符范围）
     * 例如：length=2  ->  "AB" d="AB" ->只包含A-B,
     */
    public static String randomDigits(int length,String d) {
        if (length <= 0||isEmpty(d)){
            throw new IllegalArgumentException("长度和生成的字符范围必须必须 > 0");
        }
        char[] digits = d.toCharArray();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(digits.length);
            sb.append(digits[index]);
        }
        return sb.toString();
    }

    /**
     * 判断邮箱格式
     * @param email
     * @return 是否正确
     */
    public static boolean isEmail(String email) {
        if (email == null) return false;

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    /**
     * 密码格式验证
     * @param password
     * @return
     */
    public static boolean isStrongPassword(String password) {
        if (password == null) return false;
        // 至少 8 位，必须包含大小写字母、数字
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,20}$";
        return password.matches(regex);
    }

    /**
     * 全角转半角（SBC -> DBC）
     */
    public static String toHalfWidth(String input) {
        if (input == null) return null;

        StringBuilder sb = new StringBuilder(input.length());
        for (char c : input.toCharArray()) {
            // 全角空格（Unicode 12288）转换成半角空格（32）
            if (c == 12288) {
                sb.append((char) 32);
            }
            // 其他全角字符（Unicode范围65281~65374）
            else if (c >= 65281 && c <= 65374) {
                sb.append((char) (c - 65248));
            }
            // 非全角字符保持不变
            else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}


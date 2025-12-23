package io.github.kevvy.chat_java.context;

import io.github.kevvy.chat_java.entity.dto.TokenUserInfoDto;

public class UserContext {


    private static final ThreadLocal<TokenUserInfoDto> USER_HOLDER = new ThreadLocal<>();

    /** 放入当前登录用户 */
    public static void set(TokenUserInfoDto user) {
        USER_HOLDER.set(user);
    }

    /** 获取当前登录用户 */
    public static TokenUserInfoDto get() {
        return USER_HOLDER.get();
    }

    /** 清理，防止线程复用造成脏数据 */
    public static void remove() {
        USER_HOLDER.remove();
    }
}

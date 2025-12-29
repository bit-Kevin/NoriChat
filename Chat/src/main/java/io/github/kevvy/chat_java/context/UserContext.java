package io.github.kevvy.chat_java.context;

import io.github.kevvy.chat_java.entity.dto.TokenUserInfoDTO;

public class UserContext {


    private static final ThreadLocal<TokenUserInfoDTO> USER_HOLDER = new ThreadLocal<>();

    /** 放入当前登录用户 */
    public static void set(TokenUserInfoDTO user) {
        USER_HOLDER.set(user);
    }

    /** 获取当前登录用户 */
    public static TokenUserInfoDTO get() {
        return USER_HOLDER.get();
    }

    /** 清理，防止线程复用造成脏数据 */
    public static void remove() {
        USER_HOLDER.remove();
    }
}

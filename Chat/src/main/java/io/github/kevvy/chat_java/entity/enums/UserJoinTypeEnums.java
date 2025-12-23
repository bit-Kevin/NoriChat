package io.github.kevvy.chat_java.entity.enums;

import io.github.kevvy.chat_java.utils.StringUtil;

public enum UserJoinTypeEnums {

    not_verify(0, "不需要验证"),
    need_verify(1, "需要管理员或者用户验证"),

    ;
    private Integer code;
    private String msg;

    UserJoinTypeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public UserJoinTypeEnums getStatus(String code) {
        try {
            if (StringUtil.isEmpty(code)) {
                return null;
            }
            return UserJoinTypeEnums.valueOf(code);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public UserJoinTypeEnums getStatus(Integer code) {
        for (UserJoinTypeEnums item : UserJoinTypeEnums.values()) {
            if (code.equals(item.code)) {
                return item;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}

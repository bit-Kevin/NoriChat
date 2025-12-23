package io.github.kevvy.chat_java.entity.enums;

import io.github.kevvy.chat_java.utils.StringUtil;

/**
 * 当前联系人记录属于好友还是群组
 */
public enum UserContactTypeEnums {

    code_0(0, "好友"),
    code_1(1, "群组"),

    ;
    private Integer code;
    private String msg;

    UserContactTypeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public UserContactTypeEnums getStatus(String code) {
        try {
            if (StringUtil.isEmpty(code)) {
                return null;
            }
            return UserContactTypeEnums.valueOf(code);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public UserContactTypeEnums getStatus(Integer code) {
        for (UserContactTypeEnums item : UserContactTypeEnums.values()) {
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

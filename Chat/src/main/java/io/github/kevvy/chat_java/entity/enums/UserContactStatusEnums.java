package io.github.kevvy.chat_java.entity.enums;

import io.github.kevvy.chat_java.utils.StringUtil;

/**
 * 联系人状态枚举
 */
public enum UserContactStatusEnums {
    /**
     * 状态 0：非好友 1：好友 2：已删除好友 3：被好友删除 4：已拉黑好友 5: 被好友拉黑
     */
    not_friend(0, "非好友"),
    friend(1, "好友"),
    del(2, "已删除好友"),
    dle_by(3, "被好友删除"),
    blacklist(4, "已拉黑好友"),
    blacklist_by(5, "被好友拉黑"),
    ;
    private Integer code;
    private String msg;

    UserContactStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public UserContactStatusEnums getStatus(String code) {
        try {
            if (StringUtil.isEmpty(code)) {
                return null;
            }
            return UserContactStatusEnums.valueOf(code);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public UserContactStatusEnums getStatus(Integer code) {
        for (UserContactStatusEnums item : UserContactStatusEnums.values()) {
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

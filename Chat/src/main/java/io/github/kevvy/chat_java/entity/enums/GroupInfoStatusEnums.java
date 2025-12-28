package io.github.kevvy.chat_java.entity.enums;

import io.github.kevvy.chat_java.utils.StringUtil;

public enum GroupInfoStatusEnums {

    code_0(0, "正常"), code_1(1, "解散"),

    ;
    private Integer code;
    private String msg;

    GroupInfoStatusEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public GroupInfoStatusEnums getStatus(String code) {
        try {
            if (StringUtil.isEmpty(code)) {
                return null;
            }
            return GroupInfoStatusEnums.valueOf(code);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public GroupInfoStatusEnums getStatus(Integer code) {
        for (GroupInfoStatusEnums item : GroupInfoStatusEnums.values()) {
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


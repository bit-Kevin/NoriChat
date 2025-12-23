package io.github.kevvy.chat_java.entity.enums;

import lombok.Data;

/**
 * 错误码枚举
 */
public enum ResponseCodeEnums {
    code_200(200, "请求成功"),
    code_404(404, "页面不存在"),
    code_500(500, "服务器错误，请联系管理员"),
    code_600(600, "请求参数已经存在"),
    code_601(601, "请求参数错误"),
    code_900(900, "登录超时"),

    ;

    private Integer code;
    private String msg;

    ResponseCodeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

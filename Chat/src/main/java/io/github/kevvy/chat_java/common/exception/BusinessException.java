package io.github.kevvy.chat_java.common.exception;

import io.github.kevvy.chat_java.entity.enums.ResponseCodeEnums;

public class BusinessException extends RuntimeException {

    private Integer code = 400; // 默认业务异常码

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ResponseCodeEnums responseCodeEnums) {
        super(responseCodeEnums.getMsg());
        this.code= responseCodeEnums.getCode();
    }

    public int getCode() {
        return code;
    }
}

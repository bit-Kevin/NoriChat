package io.github.kevvy.chat_java.common.exception;

public class BusinessException extends RuntimeException {

    private int code = 400; // 默认业务异常码

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

package io.github.kevvy.chat_java.common;

import lombok.Data;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用返回结果模板（可扩展版）
 * 支持普通返回、分页返回、附加信息返回
 */
@Data
public class Result<T> implements Serializable {

    private int code;            // 状态码
    private String message;      // 提示信息
    private T data;              // 实际返回数据
    private Map<String, Object> extra; // 扩展字段，可动态存放分页等信息

    public Result() {
        this.extra = new HashMap<>();
    }

    /**  成功（不带数据） */
    public static <T> Result<T> success() {
        Result<T> r = new Result<>();
        r.setCode(200);
        r.setMessage("success");
        return r;
    }

    /**  成功（带数据） */
    public static <T> Result<T> success(T data) {
        Result<T> r = new Result<>();
        r.setCode(200);
        r.setMessage("success");
        r.setData(data);
        return r;
    }

    /**  成功（自定义消息） */
    public static <T> Result<T> success(String msg, T data) {
        Result<T> r = new Result<>();
        r.setCode(200);
        r.setMessage(msg);
        r.setData(data);
        return r;
    }

    /**  失败 */
    public static <T> Result<T> error(int code, String msg) {
        Result<T> r = new Result<>();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    /** ⚙️ 添加扩展信息（链式调用） */
    public Result<T> addExtra(String key, Object value) {
        this.extra.put(key, value);
        return this;
    }
}




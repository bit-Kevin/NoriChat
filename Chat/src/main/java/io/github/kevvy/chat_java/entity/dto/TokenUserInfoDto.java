package io.github.kevvy.chat_java.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 实现这个接口是为了能够序列化成字节数据，用于网络传输
 */
@Data
public class TokenUserInfoDto implements Serializable {
    //确保从字节数据反序列化时版本一致
    private static final long serialVersionUID = 202402010001L;

    private String token;
    private String userID;

    private String nickName;

    private boolean admin;


}

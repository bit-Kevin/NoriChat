package io.github.kevvy.chat_java.entity.dto;

import lombok.Data;
import io.github.kevvy.chat_java.entity.constants.Constants;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SysSettingDTO implements Serializable {
    //确保从字节数据反序列化时版本一致
    @Serial
    private static final long serialVersionUID = 202402010001L;

    private Integer MaxGroupCount =5;
    private Integer MaxGroupMemberCount =500;
    private Integer MaxImageSize =2;
    private Integer MaxVideoSize=5;
    private Integer MaxFileSize=5;
    private  String RobotId = Constants.ROBOT_ID;
    private  String RobotName = "Nori";
    private  String RobotWelcome = "欢迎使用NoriChat！";
}

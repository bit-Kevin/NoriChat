package io.github.kevvy.chat_java .entity ;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.kevvy.chat_java.entity.enums.UserContactTypeEnums;
import lombok.Data;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;

/**
* 联系人
* @TableName user_contact
*/
@Data
public class UserContact implements Serializable {

    /**
    * 用户ID
    */
    private String userId;
    /**
    * 联系人ID或者群组ID
    */
    private String contactId;
    /**
    * 联系人类型 0：好友 1：群组
    */
    private Integer contactType;
    /**
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
    * 状态 0：非好友 1：好友 2：已删除好友 3：被好友删除 4：已拉黑好友 5: 被好友拉黑
    */
    private Integer status;
    /**
    * 最后更新时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdateTime;

}

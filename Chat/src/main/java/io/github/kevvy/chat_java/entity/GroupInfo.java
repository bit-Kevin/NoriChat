package io.github.kevvy.chat_java.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

import java.time.LocalDateTime;

/**
 *
 * @TableName group_info
 */
@Data
public class GroupInfo implements Serializable {

    /**
     * 群ID
     */
    private String groupId;
    /**
     * 群组名
     */
    private String groupName;
    /**
     * 群主id
     */
    private String groupOwnerId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 群公告
     */
    private String groupNotice;
    /**
     * 0：直接加入 1：管理员同意后加入
     */
    private Integer joinType;
    /**
     * 状态：1：正常 0：解散
     */
    private Integer status;
    /**
     * 群头像地址
     */
    private String imgPath;

}

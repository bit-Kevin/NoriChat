package io.github.kevvy.chat_java.entity.dto.query;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserContactQuery {
    /**
     * 精确查询
     */
    private String userId;
    // 联系人 ID或者群组ID
    private String contactId;
    // 联系人类型 0：好友 1：群组
    private Integer contactType;
    //状态 0：非好友 1：好友 2：已删除好友 3：被好友删除 4：已拉黑好友 5: 被好友拉黑
    private Integer status;


    /**
     * 时间范围
     */
    private LocalDateTime createTimeStart;
    private LocalDateTime createTimeEnd;

    private LocalDateTime lastUpdateTimeStart;
    private LocalDateTime lastUpdateTimeEnd;

    /**
     * 排序（后端控制，别让前端随便传）
     */
    private String orderBy;


}

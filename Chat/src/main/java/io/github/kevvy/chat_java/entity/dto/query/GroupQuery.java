package io.github.kevvy.chat_java.entity.dto.query;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GroupQuery {


    /**
     * 精确查询
     */
    private String groupId;
    private String groupOwnerId;
    private Integer joinType;
    private Integer status;

    /**
     * 模糊查询
     */
    private String groupName;

    /**
     * 时间范围
     */
    private LocalDateTime createTimeStart;
    private LocalDateTime createTimeEnd;

    /**
     * 排序（后端控制，别让前端随便传）
     */
    private String orderBy;


}

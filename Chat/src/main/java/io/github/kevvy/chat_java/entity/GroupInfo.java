package io.github.kevvy.chat_java .entity ;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;

/**
* 
* @TableName group_info
*/
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
    * 群ID
    */
    private void setGroupId(String groupId){
    this.groupId = groupId;
    }

    /**
    * 群组名
    */
    private void setGroupName(String groupName){
    this.groupName = groupName;
    }

    /**
    * 群主id
    */
    private void setGroupOwnerId(String groupOwnerId){
    this.groupOwnerId = groupOwnerId;
    }

    /**
    * 创建时间
    */
    private void setCreateTime(LocalDateTime createTime){
    this.createTime = createTime;
    }

    /**
    * 群公告
    */
    private void setGroupNotice(String groupNotice){
    this.groupNotice = groupNotice;
    }

    /**
    * 0：直接加入 1：管理员同意后加入
    */
    private void setJoinType(Integer joinType){
    this.joinType = joinType;
    }

    /**
    * 状态：1：正常 0：解散
    */
    private void setStatus(Integer status){
    this.status = status;
    }


    /**
    * 群ID
    */
    private String getGroupId(){
    return this.groupId;
    }

    /**
    * 群组名
    */
    private String getGroupName(){
    return this.groupName;
    }

    /**
    * 群主id
    */
    private String getGroupOwnerId(){
    return this.groupOwnerId;
    }

    /**
    * 创建时间
    */
    private LocalDateTime getCreateTime(){
    return this.createTime;
    }

    /**
    * 群公告
    */
    private String getGroupNotice(){
    return this.groupNotice;
    }

    /**
    * 0：直接加入 1：管理员同意后加入
    */
    private Integer getJoinType(){
    return this.joinType;
    }

    /**
    * 状态：1：正常 0：解散
    */
    private Integer getStatus(){
    return this.status;
    }

}

package io.github.kevvy.chat_java .entity ;

import java.io.Serializable;

import java.util.Date;

/**
* 联系人
* @TableName user_contact
*/
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
    private Date createTime;
    /**
    * 状态 0：非好友 1：好友 2：已删除好友 3：被好友删除 4：已拉黑好友 5: 被好友拉黑
    */
    private Integer status;
    /**
    * 最后更新时间
    */
    private Date lastUpdateTime;

    /**
    * 用户ID
    */
    private void setUserId(String userId){
    this.userId = userId;
    }

    /**
    * 联系人ID或者群组ID
    */
    private void setContactId(String contactId){
    this.contactId = contactId;
    }

    /**
    * 联系人类型 0：好友 1：群组
    */
    private void setContactType(Integer contactType){
    this.contactType = contactType;
    }

    /**
    * 创建时间
    */
    private void setCreateTime(Date createTime){
    this.createTime = createTime;
    }

    /**
    * 状态 0：非好友 1：好友 2：已删除好友 3：被好友删除 4：已拉黑好友 5: 被好友拉黑
    */
    private void setStatus(Integer status){
    this.status = status;
    }

    /**
    * 最后更新时间
    */
    private void setLastUpdateTime(Date lastUpdateTime){
    this.lastUpdateTime = lastUpdateTime;
    }


    /**
    * 用户ID
    */
    private String getUserId(){
    return this.userId;
    }

    /**
    * 联系人ID或者群组ID
    */
    private String getContactId(){
    return this.contactId;
    }

    /**
    * 联系人类型 0：好友 1：群组
    */
    private Integer getContactType(){
    return this.contactType;
    }

    /**
    * 创建时间
    */
    private Date getCreateTime(){
    return this.createTime;
    }

    /**
    * 状态 0：非好友 1：好友 2：已删除好友 3：被好友删除 4：已拉黑好友 5: 被好友拉黑
    */
    private Integer getStatus(){
    return this.status;
    }

    /**
    * 最后更新时间
    */
    private Date getLastUpdateTime(){
    return this.lastUpdateTime;
    }

}

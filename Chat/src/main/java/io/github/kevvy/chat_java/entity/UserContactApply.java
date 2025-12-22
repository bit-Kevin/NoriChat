package io.github.kevvy.chat_java .entity ;


import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

/**
* 联系人申请
* @TableName user_contact_apply
*/
public class UserContactApply implements Serializable {

    /**
    * 自增ID
    */
    private Integer applyId;
    /**
    * 申请人id
    */
    @Length(max= 12,message="编码长度不能超过12")
    private String applyUserId;
    /**
    * 接收人ID
    */
    @Length(max= 12,message="编码长度不能超过12")
    private String receiveUserId;
    /**
    * 联系人类型 0：好友 1：群组
    */
    private Integer contactType;
    /**
    * 联系人群组ID
    */
    @Length(max= 12,message="编码长度不能超过12")
    private String contactId;
    /**
    * 最后申请时间
    */
    private Long lastApplyTime;
    /**
    * 状态：待处理 1：已同意 2：已拒绝 3：已拉黑
    */
    private Integer status;
    /**
    * 申请信息
    */
    @Length(max= 100,message="编码长度不能超过100")
    private String applyInfo;

    /**
    * 自增ID
    */
    private void setApplyId(Integer applyId){
    this.applyId = applyId;
    }

    /**
    * 申请人id
    */
    private void setApplyUserId(String applyUserId){
    this.applyUserId = applyUserId;
    }

    /**
    * 接收人ID
    */
    private void setReceiveUserId(String receiveUserId){
    this.receiveUserId = receiveUserId;
    }

    /**
    * 联系人类型 0：好友 1：群组
    */
    private void setContactType(Integer contactType){
    this.contactType = contactType;
    }

    /**
    * 联系人群组ID
    */
    private void setContactId(String contactId){
    this.contactId = contactId;
    }

    /**
    * 最后申请时间
    */
    private void setLastApplyTime(Long lastApplyTime){
    this.lastApplyTime = lastApplyTime;
    }

    /**
    * 状态：待处理 1：已同意 2：已拒绝 3：已拉黑
    */
    private void setStatus(Integer status){
    this.status = status;
    }

    /**
    * 申请信息
    */
    private void setApplyInfo(String applyInfo){
    this.applyInfo = applyInfo;
    }


    /**
    * 自增ID
    */
    private Integer getApplyId(){
    return this.applyId;
    }

    /**
    * 申请人id
    */
    private String getApplyUserId(){
    return this.applyUserId;
    }

    /**
    * 接收人ID
    */
    private String getReceiveUserId(){
    return this.receiveUserId;
    }

    /**
    * 联系人类型 0：好友 1：群组
    */
    private Integer getContactType(){
    return this.contactType;
    }

    /**
    * 联系人群组ID
    */
    private String getContactId(){
    return this.contactId;
    }

    /**
    * 最后申请时间
    */
    private Long getLastApplyTime(){
    return this.lastApplyTime;
    }

    /**
    * 状态：待处理 1：已同意 2：已拒绝 3：已拉黑
    */
    private Integer getStatus(){
    return this.status;
    }

    /**
    * 申请信息
    */
    private String getApplyInfo(){
    return this.applyInfo;
    }

}

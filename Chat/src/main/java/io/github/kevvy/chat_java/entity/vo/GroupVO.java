package io.github.kevvy.chat_java.entity.vo;

import io.github.kevvy.chat_java.entity.GroupInfo;
import io.github.kevvy.chat_java.entity.UserContact;
import io.github.kevvy.chat_java.entity.dto.UserContactDTO;
import lombok.Data;

import java.util.List;

//VO = view Object
@Data
public class GroupVO{

// 群信息
private GroupInfo groupInfo;
//联系人列表
private List<UserContact> userContactList;
}

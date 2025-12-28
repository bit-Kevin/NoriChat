package io.github.kevvy.chat_java.controller;


import io.github.kevvy.chat_java.annotation.GlobalInterceptor;
import io.github.kevvy.chat_java.common.Result;
import io.github.kevvy.chat_java.common.exception.BusinessException;
import io.github.kevvy.chat_java.context.UserContext;
import io.github.kevvy.chat_java.entity.GroupInfo;
import io.github.kevvy.chat_java.entity.UserContact;
import io.github.kevvy.chat_java.entity.dto.TokenUserInfoDto;
import io.github.kevvy.chat_java.entity.dto.query.GroupQuery;
import io.github.kevvy.chat_java.entity.dto.query.UserContactQuery;
import io.github.kevvy.chat_java.entity.enums.GroupInfoStatusEnums;
import io.github.kevvy.chat_java.entity.enums.UserContactStatusEnums;
import io.github.kevvy.chat_java.service.GroupService;
import io.github.kevvy.chat_java.service.UserContactService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor // 自动生成构造方法并注入依赖不用@autowired
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupInfoService;
    private final UserContactService userContactService;

    @RequestMapping("saveGroup")
    @GlobalInterceptor
    public Result<Void> saveGroup(HttpServletRequest httpServletRequest, @RequestBody GroupInfo groupInfo) {
        //只关心成功，因为失败抛出的异常会被全局异常处理捕获
        groupInfoService.saveGroup(groupInfo);
        return Result.success();
    }

    @GetMapping("/loadMyGroup")
    @GlobalInterceptor
    public Result<List<GroupInfo>> getGroupListByUserID(){
        TokenUserInfoDto userInfoDto = UserContext.get();
        GroupQuery query = new GroupQuery();
        query.setGroupOwnerId(userInfoDto.getUserID());
        query.setStatus(0);
        List<GroupInfo> groupInfos = groupInfoService.findListGroupByQuery(query);
        return Result.success(groupInfos);
    }

    @GetMapping("/getGroupInfo")
    @GlobalInterceptor
    public Result<GroupInfo> getGroupInfo(String groupId){
        //验证请求权限，是否群组内的成员
        GroupInfo groupInfo = getGroupDetail(groupId);
        return Result.success(groupInfo);
    }

    /**
     * 因为调用多个service 不好写入任何一个service ，且多个请求复用。
     * @param groupId 群聊 id
     * @return GroupInfo
     */
    private GroupInfo getGroupDetail(String groupId){
        TokenUserInfoDto userInfoDto = UserContext.get();
        UserContactQuery query = new UserContactQuery();
        query.setUserId(userInfoDto.getUserID());
        query.setContactId(groupId);
        query.setContactType(1);//群组
        UserContact userContact = userContactService.findUserContactByQuery(query);
        if (null==userContact||!userContact.getStatus().equals(UserContactStatusEnums.friend.getCode())){
            throw new BusinessException("你不在群聊，或者群聊已经解散");
        }
        GroupQuery groupQuery = new GroupQuery();
        groupQuery.setGroupId(groupId);
        groupQuery.setStatus(0);
        GroupInfo groupInfo = groupInfoService.findGroupByQuery(groupQuery);
        if (null==groupInfo||groupInfo.getStatus().equals(GroupInfoStatusEnums.code_1.getCode())){
            throw  new BusinessException("群聊不存在或者已解散");
        }
        return groupInfo;
    }
}

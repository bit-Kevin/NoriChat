package io.github.kevvy.chat_java.controller;


import io.github.kevvy.chat_java.annotation.GlobalInterceptor;
import io.github.kevvy.chat_java.common.Result;
import io.github.kevvy.chat_java.context.UserContext;
import io.github.kevvy.chat_java.entity.GroupInfo;
import io.github.kevvy.chat_java.entity.dto.TokenUserInfoDto;
import io.github.kevvy.chat_java.entity.dto.query.GroupQuery;
import io.github.kevvy.chat_java.service.GroupService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor // 自动生成构造方法并注入依赖不用@autowired
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupInfoService;

    @RequestMapping("saveGroup")
    @GlobalInterceptor
    public Result<Void> saveGroup(HttpServletRequest httpServletRequest, @RequestBody GroupInfo groupInfo) {
        //只关心成功，因为失败抛出的异常会被全局异常处理捕获
        groupInfoService.saveGroup(groupInfo);
        return Result.success();
    }

    @RequestMapping("/loadMyGroup")
    @GlobalInterceptor
    public Result<List<GroupInfo>> getGroupListByUserID(){
        TokenUserInfoDto userInfoDto = UserContext.get();
        GroupQuery query = new GroupQuery();
        query.setGroupOwnerId(userInfoDto.getUserID());
        query.setStatus(0);
        List<GroupInfo> groupInfos = groupInfoService.findGroupByQuery(query);
        return Result.success(groupInfos);
    }
}

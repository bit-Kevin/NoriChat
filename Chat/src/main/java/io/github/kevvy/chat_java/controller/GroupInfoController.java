package io.github.kevvy.chat_java.controller;


import io.github.kevvy.chat_java.annotation.GlobalInterceptor;
import io.github.kevvy.chat_java.common.Result;
import io.github.kevvy.chat_java.entity.GroupInfo;
import io.github.kevvy.chat_java.service.GroupInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // 自动生成构造方法并注入依赖不用@autowired
@RequestMapping("/group")
public class GroupInfoController {
    private GroupInfoService groupInfoService;

    @RequestMapping("saveGroup")
    @GlobalInterceptor
    public Result<Void> saveGroup(HttpServletRequest httpServletRequest, @RequestBody GroupInfo groupInfo){
        return Result.success();
    }
}

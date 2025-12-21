package io.github.kevvy.chat_java.controller;


import io.github.kevvy.chat_java.common.Result;
import io.github.kevvy.chat_java.entity.GroupInfo;
import io.github.kevvy.chat_java.service.GroupInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // 自动生成构造方法并注入依赖不用@autowired
@RequestMapping("/groupInfo")
public class GroupInfoController {
    private GroupInfoService groupInfoService;

    @RequestMapping("saveGroup")
    public Result<Void> saveGroup(@RequestBody GroupInfo groupInfo){
        //todo
        return Result.success();
    }
}

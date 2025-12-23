package io.github.kevvy.chat_java.controller;

import io.github.kevvy.chat_java.common.Result;
import io.github.kevvy.chat_java.entity.dto.SysSettingDto;
import io.github.kevvy.chat_java.entity.dto.TokenUserInfoDto;
import io.github.kevvy.chat_java.entity.User;
import io.github.kevvy.chat_java.redis.RedisComponent;
import io.github.kevvy.chat_java.service.UserService;
import io.github.kevvy.chat_java.service.impl.MailCodeService;
import io.github.kevvy.chat_java.utils.JwtUtil;
import io.github.kevvy.chat_java.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor // 自动生成构造方法并注入依赖不用@autowired
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final MailCodeService mailCodeService;
    private final RedisComponent redisComponent;

    // 发送验证码
    @PostMapping("/code/send")
    public Result<Void> send(@RequestBody Map<String, String> body) {
        if (!StringUtil.isEmail(body.get("email"))) return Result.error(400, "邮箱格式错误");
        String err = mailCodeService.sendRegisterCode(body.get("email"));
        return err == null ? Result.success() : Result.error(400, err);
    }

    // 校验验证码
    @PostMapping("/code/verify")
    public Result<Void> verify(@RequestBody Map<String, String> body) {
        boolean ok = mailCodeService.checkCode(body.get("email"), body.get("code"));
        return ok ? Result.success() : Result.error(400, "验证码错误或已过期");
    }

    // 注册
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        //验证检验码前面已经完成
        //校验密码格式
        if (StringUtil.isStrongPassword(user.getPassword())) return Result.error(400, "密码格式错误");
        userService.register(user);
        return Result.success("注册成功");
    }

    // 登录
    @PostMapping("/login")
    public Result<TokenUserInfoDto> login(@RequestBody User user) {
        //TODO 后续添加邮箱验证码
        return Result.success("登录成功",userService.login(user));
    }

    //获取系统设置
    @GetMapping("/getSysSetting")
    public Result<SysSettingDto> getSysSetting(){
        return Result.success(redisComponent.getSysSetting());
    }
}

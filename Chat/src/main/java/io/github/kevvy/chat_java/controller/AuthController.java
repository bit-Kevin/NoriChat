package io.github.kevvy.chat_java.controller;

import io.github.kevvy.chat_java.common.Result;
import io.github.kevvy.chat_java.entity.User;
import io.github.kevvy.chat_java.service.UserService;
import io.github.kevvy.chat_java.service.impl.MailCodeService;
import io.github.kevvy.chat_java.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor // 自动生成构造方法并注入依赖不用@autowired
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final MailCodeService mailCodeService;

    // 发送验证码
    @PostMapping("/code/send")
    public Result<Void> send(@RequestBody Map<String, String> body) {
        String err= mailCodeService.sendRegisterCode(body.get("email"));
        return err==null?Result.success():Result.error(400,err);
    }

    // 校验验证码
    @PostMapping("/code/verify")
    public Result<Boolean> verify(@RequestBody Map<String, String> body) {
        boolean ok = mailCodeService.checkCode(body.get("email"), body.get("code"));
        return ok ? Result.success(true) : Result.error(400, "验证码错误或已过期");
    }
    // 注册
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        boolean success = userService.register(user);
        return success ? Result.success("注册成功") : Result.error(400, "用户名已存在");
    }

    // 登录
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        User loginUser = userService.login(user.getUserId(), user.getPassword());
        if (loginUser != null) {
            String token = jwtUtil.generateToken(loginUser.getUserId());
            return Result.success(token,loginUser);
        }
        return Result.error(401, "用户名或密码错误");
    }
}

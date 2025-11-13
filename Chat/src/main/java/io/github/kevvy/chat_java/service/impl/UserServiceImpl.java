package io.github.kevvy.chat_java.service.impl;

import io.github.kevvy.chat_java.common.exception.BusinessException;
import io.github.kevvy.chat_java.entity.config.APPConfig;
import io.github.kevvy.chat_java.entity.dto.TokenUserInfoDto;
import io.github.kevvy.chat_java.entity.User;
import io.github.kevvy.chat_java.mappers.UserMapper;
import io.github.kevvy.chat_java.redis.RedisComponent;
import io.github.kevvy.chat_java.service.UserService;
import io.github.kevvy.chat_java.utils.JwtUtil;
import io.github.kevvy.chat_java.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor // ✅ 自动生成构造方法并注入依赖
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    private final APPConfig appConfig;

    private final RedisComponent redisComponent;

    @Override
    public boolean register(User user) {
        User exist = userMapper.findByEmail(user.getEmail());
        if (exist != null) {
            return false; // 用户已存在
        }
        //创建用户id
        user.setUserId("US" + StringUtil.randomDigits(8) + StringUtil.randomDigits(2, "RANDOM"));
        user.setStatus(0);
        user.setCreateTime(LocalDateTime.now());
        user.setJoinType(1);
        //添加机器人好友
        userMapper.insertUser(user);
        return true;
    }

    @Override
    public TokenUserInfoDto login(User u) {
        if (!StringUtil.isEmail(u.getEmail())) throw new BusinessException(400, "邮箱格式错误");
        if (StringUtil.isStrongPassword(u.getPassword())) throw new BusinessException(400, "密码格式错误");
        User user = userMapper.findByEmail(u.getEmail());
        if (user == null || !user.getPassword().equals(u.getPassword()))
            throw new BusinessException(400, "邮箱或者密码错误");
        if (user.getStatus() == 1) throw new BusinessException(400, "账号已禁用");
        if (redisComponent.isUserOnline(user.getUserId()))throw new BusinessException(400, "账号已在别的客户端登录");
        //TODO 查询联系人和群组
        TokenUserInfoDto dto = getTokenUserInfoDto(user);
        //将初始化信息存入到redis
        redisComponent.saveTokenUserInfoDto(dto);
        return dto;

    }

    /**
     * 用户初始化信息
     *
     * @param user
     * @return
     */
    private TokenUserInfoDto getTokenUserInfoDto(User user) {
        TokenUserInfoDto tokenUserInfoDto = new TokenUserInfoDto();
        tokenUserInfoDto.setToken(jwtUtil.generateToken(user.getUserId()));
        tokenUserInfoDto.setNickName(user.getNickName());
        tokenUserInfoDto.setUserID(user.getUserId());
        if (user.getEmail().equals(appConfig.getAdminEmail())) {
            tokenUserInfoDto.setAdmin(true);
        } else {
            tokenUserInfoDto.setAdmin(false);
        }
        return tokenUserInfoDto;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    @Override
    public User getUserById(String id) {
        return userMapper.findById(id);
    }

    @Override
    public int createUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int deleteUser(String id) {
        return userMapper.deleteUser(id);
    }
}

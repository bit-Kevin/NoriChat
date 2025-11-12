package io.github.kevvy.chat_java.service.impl;

import io.github.kevvy.chat_java.entity.User;
import io.github.kevvy.chat_java.mappers.UserMapper;
import io.github.kevvy.chat_java.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // ✅ 自动生成构造方法并注入依赖
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;


    // 通过构造函数注入 mapper
//    public UserServiceImpl(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }

    @Override
    public boolean register(User user) {
        User exist = userMapper.findById(user.getUserId());
        if (exist != null) {
            return false; // 用户已存在
        }
        userMapper.insertUser(user);
        return true;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findById(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
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

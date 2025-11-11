package io.github.kevvy.chat_java.service.impl;

import io.github.kevvy.chat_java.entity.User;
import io.github.kevvy.chat_java.mappers.UserMapper;
import io.github.kevvy.chat_java.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    // 通过构造函数注入 mapper
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
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

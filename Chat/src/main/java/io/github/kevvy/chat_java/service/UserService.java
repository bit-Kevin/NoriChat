package io.github.kevvy.chat_java.service;

import io.github.kevvy.chat_java.entity.dto.TokenUserInfoDto;
import io.github.kevvy.chat_java.entity.User;
import java.util.List;

public interface UserService {

    void register(User user);
    TokenUserInfoDto login(User user);
    List<User> getAllUsers();
    User getUserById(String id);
    int createUser(User user);
    int deleteUser(String id);
    boolean verify(String email,String code);
}

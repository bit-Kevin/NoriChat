package io.github.kevvy.chat_java.service;

import io.github.kevvy.chat_java.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(String id);
    int createUser(User user);
    int deleteUser(String id);
}

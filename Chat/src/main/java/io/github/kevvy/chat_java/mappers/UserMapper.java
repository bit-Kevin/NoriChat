package io.github.kevvy.chat_java.mappers;

import io.github.kevvy.chat_java.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 20350
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2025-11-11 21:21:35
 * @Entity io.github.kevvy.chat_java.entity.User
 */
@Mapper
public interface UserMapper {

    Integer countAll();

    List<User> findAll();

    User findById(String id);

    User findByEmail(String id);

    int insertUser(User user);

    int deleteById(String id);
}






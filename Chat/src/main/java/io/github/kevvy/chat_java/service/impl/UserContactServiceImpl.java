package io.github.kevvy.chat_java.service.impl;

import io.github.kevvy.chat_java.entity.UserContact;
import io.github.kevvy.chat_java.entity.dto.UserContactDTO;
import io.github.kevvy.chat_java.entity.dto.query.UserContactQuery;
import io.github.kevvy.chat_java.mappers.UserContactMapper;
import io.github.kevvy.chat_java.service.UserContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // 自动生成构造方法并注入依赖
public class UserContactServiceImpl implements UserContactService {
    private final UserContactMapper userContactMapper;
    @Override
    public UserContact findUserContactByQuery(UserContactQuery query) {
        return userContactMapper.findByQuery(query);
    }

    @Override
    public List<UserContact> findListUserContactByQuery(UserContactQuery query) {
        return  userContactMapper.findListByQuery(query);
    }

    @Override
    public UserContactDTO findByGroupId(String GroupId) {
        return null;
    }

}

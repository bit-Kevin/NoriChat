package io.github.kevvy.chat_java.service;

import io.github.kevvy.chat_java.entity.UserContact;
import io.github.kevvy.chat_java.entity.dto.UserContactDTO;
import io.github.kevvy.chat_java.entity.dto.query.UserContactQuery;

import java.util.List;

public interface UserContactService {
   UserContact  findUserContactByQuery(UserContactQuery query);
   List<UserContact> findListUserContactByQuery(UserContactQuery query);
   UserContactDTO findByGroupId(String GroupId);
}

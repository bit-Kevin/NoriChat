package io.github.kevvy.chat_java.service;

import io.github.kevvy.chat_java.entity.GroupInfo;
import io.github.kevvy.chat_java.entity.dto.query.GroupQuery;

import java.util.List;

public interface GroupService {
    void saveGroup(GroupInfo groupInfo);
    List<GroupInfo> findListGroupByQuery(GroupQuery groupQuery);
    GroupInfo findGroupByQuery(GroupQuery groupQuery);

}

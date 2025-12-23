package io.github.kevvy.chat_java.service.impl;

import io.github.kevvy.chat_java.common.exception.BusinessException;
import io.github.kevvy.chat_java.entity.GroupInfo;
import io.github.kevvy.chat_java.entity.UserContact;
import io.github.kevvy.chat_java.entity.dto.SysSettingDto;
import io.github.kevvy.chat_java.entity.dto.query.GroupQuery;
import io.github.kevvy.chat_java.entity.enums.ResponseCodeEnums;
import io.github.kevvy.chat_java.entity.enums.UserContactTypeEnums;
import io.github.kevvy.chat_java.entity.enums.UserJoinTypeEnums;
import io.github.kevvy.chat_java.mappers.GroupInfoMapper;
import io.github.kevvy.chat_java.mappers.UserContactMapper;
import io.github.kevvy.chat_java.redis.RedisComponent;
import io.github.kevvy.chat_java.redis.RedisUtil;
import io.github.kevvy.chat_java.service.GroupService;
import io.github.kevvy.chat_java.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor // 自动生成构造方法并注入依赖
public class GroupServiceImpl implements GroupService {
    private final UserContactMapper userContactMapper;

    private final GroupInfoMapper groupInfoMapper;
    private final RedisUtil redisUtil;
    private final RedisComponent redisComponent;

    @Override
    @Transactional(rollbackFor = Exception.class)//数据库事物
    // 默认 RuntimeException会回滚，人为改成所有异常都会触发回滚
    public void saveGroup(GroupInfo groupInfo) {
        //校验数据
        if (StringUtil.isEmpty(groupInfo.getGroupName())) {
            throw new BusinessException(601, "群组名称为空");
        }
        if (groupInfo.getJoinType() == null) {
            throw new BusinessException(601, "加入类型为空");
        }
        if (StringUtil.isEmpty(groupInfo.getGroupId())) {
            //新建
            //查询群主已创建是否已经达到最大数
            int count = groupInfoMapper.maxCountByOwnerID(groupInfo.getGroupOwnerId());
            SysSettingDto sysSetting = redisComponent.getSysSetting();
            if (count >= sysSetting.getMaxGroupCount()) {
                throw new BusinessException(601, "最多创建" + sysSetting.getMaxGroupCount() + "个群聊");
            }
            //单纯为了 id好看
            groupInfo.setGroupId("GP" + StringUtil.randomDigits(8) + StringUtil.randomDigits(2, "RANDOM"));
            groupInfo.setStatus(0);
            groupInfo.setCreateTime(LocalDateTime.now());
            if (groupInfo.getJoinType() == null) {
                groupInfo.setJoinType(UserJoinTypeEnums.need_verify.getCode());
            }
            if (groupInfoMapper.insert(groupInfo)!=1) {
                throw new BusinessException(ResponseCodeEnums.code_601);
            }
            // 添加联系人
            UserContact userContact = new UserContact();
            userContact.setUserId(groupInfo.getGroupOwnerId());
            userContact.setContactType(UserContactTypeEnums.code_1.getCode());
            userContact.setCreateTime(LocalDateTime.now());
            userContact.setLastUpdateTime(LocalDateTime.now());
            userContact.setContactId(groupInfo.getGroupId());
            userContact.setStatus(0);
            if (userContactMapper.insert(userContact)!=1){
                throw new BusinessException(ResponseCodeEnums.code_601);
            }
            //todo 创建会话

        } else {
            //更新
            GroupInfo info = groupInfoMapper.selectById(groupInfo.getGroupId());
            //群的建立者不是当前用户
            if (!info.getGroupOwnerId().equals(groupInfo.getGroupOwnerId())){
                throw new BusinessException(600,"不是群组所有者，无法修改");
            }
            if (groupInfoMapper.updateById(groupInfo)!=1){
                throw new BusinessException(ResponseCodeEnums.code_601);
            }
            //todo 更新后续相关信息 组员发送消息/可选
            UserContact userContact = userContactMapper.selectByPk(groupInfo.getGroupOwnerId(), groupInfo.getGroupId());
            userContact.setLastUpdateTime(LocalDateTime.now());
            if (userContactMapper.updateById(userContact)!=1){
                throw new BusinessException(ResponseCodeEnums.code_601);
            }
        }
    }

    @Override
    public List<GroupInfo> findGroupByQuery(GroupQuery groupQuery) {
        return groupInfoMapper.findListByQuery(groupQuery);
    }

}

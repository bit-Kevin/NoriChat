package io.github.kevvy.chat_java.service.impl;

import io.github.kevvy.chat_java.common.exception.BusinessException;
import io.github.kevvy.chat_java.entity.GroupInfo;
import io.github.kevvy.chat_java.entity.dto.SysSettingDto;
import io.github.kevvy.chat_java.entity.enums.ResponseCodeEnums;
import io.github.kevvy.chat_java.mappers.GroupInfoMapper;
import io.github.kevvy.chat_java.redis.RedisComponent;
import io.github.kevvy.chat_java.redis.RedisUtil;
import io.github.kevvy.chat_java.service.GroupService;
import io.github.kevvy.chat_java.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor // 自动生成构造方法并注入依赖
public class GroupServiceImpl implements GroupService {
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
            groupInfoMapper.insert(groupInfo);
            //todo 添加联系人 建立枚举
        } else {
            //更新
        }
    }

}

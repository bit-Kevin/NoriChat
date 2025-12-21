package io.github.kevvy.chat_java.mappers;

import io.github.kevvy.chat_java.entity.UserContactApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserContactApplyMapper {

    int insert(UserContactApply row);

    //    @Param("applyId") 是用来“给方法参数起名字，让 MyBatis 在 XML 里能准确找到它”的。
    int updateStatus(@Param("applyId") Integer applyId,
                     @Param("status") Integer status);

    UserContactApply selectById(@Param("applyId") Integer applyId);

    /**
     * 查某个接收人的待处理申请（好友/群都可）
     */
    List<UserContactApply> listPendingByReceiver(@Param("receiveUserId") String receiveUserId,
                                                 @Param("contactType") Integer contactType);

    /**
     * 用唯一索引那三个字段查（防重复申请/查最近一次）
     */
    UserContactApply selectByUniqueKey(@Param("applyUserId") String applyUserId,
                                       @Param("receiveUserId") String receiveUserId,
                                       @Param("contactId") String contactId);

    int deleteById(@Param("applyId") Integer applyId);
}

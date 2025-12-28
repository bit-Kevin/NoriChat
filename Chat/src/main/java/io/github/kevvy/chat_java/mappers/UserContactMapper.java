package io.github.kevvy.chat_java.mappers;

import io.github.kevvy.chat_java.entity.UserContact;
import io.github.kevvy.chat_java.entity.dto.query.UserContactQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserContactMapper {

    int insert(UserContact row);

    //    @Param("userId") 是用来“给方法参数起名字，让 MyBatis 在 XML 里能准确找到它”的。也可以传实体类
    int updateStatus(@Param("userId") String userId,
                     @Param("contactId") String contactId,
                     @Param("status") Integer status);

    int updateById(UserContact row);

    UserContact selectByPk(@Param("userId") String userId,
                           @Param("contactId") String contactId);

    List<UserContact> listByUserId(@Param("userId") String userId,
                                   @Param("contactType") Integer contactType);

    int deleteByPk(@Param("userId") String userId,
                   @Param("contactId") String contactId);

    UserContact findByQuery(UserContactQuery query);
    List<UserContact> findListByQuery(UserContactQuery query);
}

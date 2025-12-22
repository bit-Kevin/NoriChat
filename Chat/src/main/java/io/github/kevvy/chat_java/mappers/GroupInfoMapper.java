package io.github.kevvy.chat_java.mappers;

import io.github.kevvy.chat_java.entity.GroupInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupInfoMapper {

    int insert(GroupInfo row);

    int updateById(GroupInfo row);

//    @Param("groupId") 是用来“给方法参数起名字，让 MyBatis 在 XML 里能准确找到它”的。
    int updateNotice(@Param("groupId") String groupId,
                     @Param("groupNotice") String groupNotice);

    int updateStatus(@Param("groupId") String groupId,
                     @Param("status") Integer status);

    int updateImgPath(@Param("groupId") String groupId,
                      @Param("imgPath") Integer imgPath);

    GroupInfo selectById(@Param("groupId") String groupId);

    List<GroupInfo> listByOwnerId(@Param("ownerId") String ownerId);
    int maxCountByOwnerID(@Param("ownerId") String ownerId);

    int deleteById(@Param("groupId") String groupId);
}

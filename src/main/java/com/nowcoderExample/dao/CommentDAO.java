package com.nowcoderExample.dao;

import com.nowcoderExample.model.Comment;
import org.apache.ibatis.annotations.*;
//import org.apache.ibatis.annotations.*;
import java.util.List;

//import static com.nowcoderExample.dao.NewsDAO.SELECT_FIELDS;

/**
 * Created by Administrator on 2016/8/10 0010.
 */
/*@Mapper
public interface CommentDAO {
    String TABLE_NAME = "comment";
    String INSERT_FIELDS = " userId, content, create_date, entity_id, entity_type, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME,"(",INSERT_FIELDS,
    ") values(#{userId},#{content},#{createDate},#{entityId},#{entityType},#{status})"})
    int addComment(Comment comment);

    @Select({"select ",SELECT_FIELDS," from ", TABLE_NAME,
            " where entity_id=#{entityId} and entity_type=#{entityType} order by id desc"})
    List<Comment> selectByEntity(@Param("entityId") int entityId, @Param("entityType")int entityType);

    @Select({"select count(id) from ", TABLE_NAME, " where entity_type=#{entityType} and entity_id=#{entityId}"})
    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);
}
*/
@Mapper
public interface CommentDAO {
    String TABLE_NAME = " comment ";
    String INSERT_FIELDS = " user_id, content, created_date, entity_id, entity_type, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{userId},#{content},#{createdDate},#{entityId},#{entityType},#{status})"})
    int addComment(Comment comment);

    @Update({"update ",TABLE_NAME," set status=#{status} where entity_type=#{entityType} and entity_id=#{entityId} order by id desc"})
    void UpdateStatus(@Param("entity")int entity,@Param("entityType")int entityType,@Param("status")int status);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where entity_type=#{entityType} and entity_id=#{entityId} order by id desc "})
    List<Comment> selectByEntity(@Param("entityId") int entityId, @Param("entityType") int entityType);

    @Select({"select count(id) from ", TABLE_NAME, " where entity_type=#{entityType} and entity_id=#{entityId}"})
    int getCommentCount(@Param("entityId") int entityId, @Param("entityType") int entityType);
}
package com.example.schoolmarket.dao;

import com.example.schoolmarket.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentDao {
    @Select("select * from comment ")
    List<Comment> GetAllComment();

    @Select("select * from comment where product_id=#{product_id}")
    List<Comment> GetCommentByProductId(Integer product_id);

    @Insert("insert into comment(content,product_id,user_id, user_name, datetime) values(#{content},#{product_id},#{user_id},#{user_name}, #{datetime} )")
    int AddComment(Comment comment);

    @Delete("DELETE FROM comment WHERE id = #{id}")
    int DeleteCommentById(Integer id);
}

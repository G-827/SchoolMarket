package com.example.schoolmarket.service;

import com.example.schoolmarket.dao.CommentDao;
import com.example.schoolmarket.dao.CommentDao;
import com.example.schoolmarket.entity.Comment;
import com.example.schoolmarket.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentDao commentDao;
    @Override
    public List<Comment> GetAllComment() {

        try {
            List<Comment> comments = commentDao.GetAllComment();
            return  comments;
        }
        catch (Exception e)
        {
            throw e;
//            return null;
        }
    }

    @Override
    public List<Comment> GetCommentByProductId(Integer good_id) {

        try {
            List<Comment> comments = commentDao.GetCommentByProductId(good_id);
            return  comments;
        }
        catch (Exception e)
        {
            throw e;
//            return null;
        }
    }

    @Override
    public String AddComment(Comment comment) {
        try {

            int i = commentDao.AddComment(comment);
            return "添加成功" + i + "条数据";
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public String DeleteCommentById(Integer id) {
        try {
            int i = commentDao.DeleteCommentById(id);
            return "成功删除" +i + "条数据";
        }
        catch (Exception e)
        {
            throw e;
        }

    }
}

package com.example.schoolmarket.service;

import com.example.schoolmarket.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> GetAllComment();
    List<Comment> GetCommentByProductId(Integer productid);
    String AddComment(Comment good);
    String DeleteCommentById(Integer id);

}

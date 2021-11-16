package com.example.schoolmarket.controller;

import com.example.schoolmarket.entity.Comment;
import com.example.schoolmarket.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("comment/getallcomment")
    @ResponseBody
    public List<Comment> getCommentList()
    {
        return commentService.GetAllComment();
    }

    @GetMapping("comment/getcommentbyproductid")
    @ResponseBody
    public  List<Comment> getCommentByProductId(Integer id){ return commentService.GetCommentByProductId(id);}

    @PostMapping("comment/addcomment")
    @ResponseBody
    public String addComment(Comment comment)
    {
        return commentService.AddComment(comment);
    }

    @DeleteMapping("comment/deletecommentbyid")
    @ResponseBody
    public String deleteCommentById(Integer id)
    {
        return commentService.DeleteCommentById(id);
    }

}

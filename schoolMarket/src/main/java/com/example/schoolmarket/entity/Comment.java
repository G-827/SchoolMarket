package com.example.schoolmarket.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

@Entity
@Table(name = "comment")

@Getter
@Setter

public class Comment {
    private Integer id;
    private String content;
    private Integer user_id;
    private String user_name;
    private Integer product_id;
    private String datetime;

}

package com.example.schoolmarket.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity
@Table(name = "product")

@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productname;
    private String price ;
    private String type ;
    private String img_url ;
    private String detail ;
    private String owner_id ;
    private String owner_name;
    private String datetime;
    private Integer state;

    public Product() {
    }


}

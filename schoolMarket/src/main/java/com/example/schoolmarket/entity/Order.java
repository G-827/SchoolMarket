package com.example.schoolmarket.entity;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "orderlist")

@Getter
@Setter
public class Order {
    private Integer orderid;
    private String datetime;
    private Integer buyerid;
    private String buyername;
    private Integer sellerid;
    private String sellername;
    private Integer productid;
    private String productname;
    private String price;
    private String img_url;

}

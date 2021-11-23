package com.example.schoolmarket.dao;

import com.example.schoolmarket.entity.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderDao {
    @Select("select * from orderlist ORDER by datetime desc")
    List<Order> GetAllOrder();

    //用户买的所有订单，用户是买家
    @Select("SELECT * FROM orderlist WHERE buyerid=#{user_id} ORDER by datetime desc")
    List<Order> GetOrderByUserId_buy(Integer user_id);

    //用户卖的所有订单，用户是卖家
    @Select("SELECT * FROM orderlist WHERE sellerid=#{user_id} ORDER by datetime desc")
    List<Order> GetOrderByUserId_sell(Integer user_id);

    //用户相关的所有订单
    @Select("SELECT * FROM orderlist WHERE sellerid=#{user_id} OR buyerid=#{user_id} ORDER by datetime desc")
    List<Order> GetOrderByUserId_all(Integer user_id);

    @Insert("INSERT into " +
            "orderlist (datetime,buyerid,buyername,sellerid,sellername,productid,productname,price,img_url) " +
            "values (#{datetime},#{buyerid},#{buyername},#{sellerid},#{sellername},#{productid},#{productname},#{price},#{img_url})")
    int AddOrder(Order order);

    @Delete("DELETE FROM orderlist WHERE orderid = #{id}")
    int DeleteOrderById(Integer id);
}


package com.example.schoolmarket.service;

import com.example.schoolmarket.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<Order> GetAllOrder();
    List<Order> GetOrderByUserId_buy(Integer user_id);
    List<Order> GetOrderByUserId_sell(Integer user_id);
    List<Order> GetOrderByUserId_all(Integer user_id);
    String AddOrder(Order good);
    String DeleteOrderById(Integer id);
}

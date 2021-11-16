package com.example.schoolmarket.controller;

import com.example.schoolmarket.entity.Order;
import com.example.schoolmarket.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;
    @GetMapping("order/getallorder")
    @ResponseBody
    public List<Order> getOrderList() {  return orderService.GetAllOrder(); }

    @GetMapping("order/getorderbyuserid/buy")
    @ResponseBody
    public List<Order> getOrderByUserId_buy(Integer id)
    {
        return orderService.GetOrderByUserId_buy(id);
    }

    @GetMapping("order/getorderbyuserid/sell")
    @ResponseBody
    public List<Order> getOrderByUserId_sell(Integer id)
    {
        return orderService.GetOrderByUserId_sell(id);
    }

    @GetMapping("order/getorderbyuserid/all")
    @ResponseBody
    public List<Order> getOrderByUserId_all(Integer id)
    {
        return orderService.GetOrderByUserId_all(id);
    }

    @PostMapping("order/addorder")
    @ResponseBody
    public String addOrder(Order order)
    {
        return orderService.AddOrder(order);
    }

    @DeleteMapping("order/deleteorderbyid")
    @ResponseBody
    public String deleteOrderById(Integer id)
    {
        return orderService.DeleteOrderById(id);
    }

}

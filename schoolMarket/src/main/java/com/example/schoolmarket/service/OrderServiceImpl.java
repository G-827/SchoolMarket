package com.example.schoolmarket.service;

import com.example.schoolmarket.dao.OrderDao;
import com.example.schoolmarket.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderDao orderDao;
    @Override
    public List<Order> GetAllOrder() {
        try {
            List<Order> orders = orderDao.GetAllOrder();
            return  orders;
        }
        catch (Exception e)
        {
            throw e;
//            return null;
        }
    }

    @Override
    public List<Order> GetOrderByUserId_buy(Integer user_id) {
        try{
            return orderDao.GetOrderByUserId_buy(user_id);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List<Order> GetOrderByUserId_sell(Integer user_id) {
        try{
            return orderDao.GetOrderByUserId_sell(user_id);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List<Order> GetOrderByUserId_all(Integer user_id) {
        try{
            return orderDao.GetOrderByUserId_all(user_id);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public String AddOrder(Order order) {
        try {

            int i = orderDao.AddOrder(order);
            return "添加成功" + i + "条数据";
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public String DeleteOrderById(Integer id) {
        try {
            int i = orderDao.DeleteOrderById(id);
            return "成功删除" +i + "条数据";
        }
        catch (Exception e)
        {
            throw e;
        }

    }
}

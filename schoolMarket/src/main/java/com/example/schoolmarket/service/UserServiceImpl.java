package com.example.schoolmarket.service;

import com.example.schoolmarket.entity.User;
import com.example.schoolmarket.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User Login(String username, String password) {
        try{
            User users = userDao.Login(username,password);
            return users;
        }
        catch (Exception e){
            throw e;
        }
    }
    @Override
    public List<User> GetAllUser() {

        try {
            List<User> users = userDao.GetAllUser();
            return  users;
        }
        catch (Exception e)
        {
            throw e;
//            return null;
        }
    }

    @Override
    public User GetUserById(Integer id) {
        try{
            User thisuser = userDao.GetUserById(id);
            return thisuser;
        }
        catch (Exception e)
        {
            throw e;
        }
        //return null;
    }

    @Override
    public String AddUser(User user) {
        try {

            int i = userDao.AddUser(user);
            return "添加成功" + i + "条数据";
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public String DeleteUserById(Integer id) {
        try {
            int i = userDao.DeleteUserById(id);
            return "成功删除" +i + "条数据";
        }
        catch (Exception e)
        {
            throw e;
        }

    }

    @Override
    public String UpdateUser(User user) {
        try {
            int i = userDao.UpdateUser(user);
            return "成功更新" +i + "条数据";
        }
        catch (Exception e)
        {
            throw e;
        }

    }

}

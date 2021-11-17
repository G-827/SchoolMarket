package com.example.schoolmarket.service;

import com.example.schoolmarket.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User Login(String username, String userpassword);
    List<User> GetAllUser();
    User GetUserById(Integer id);
    String AddUser(User user);
    String DeleteUserById(Integer id);
    String UpdateUser(User user);

}

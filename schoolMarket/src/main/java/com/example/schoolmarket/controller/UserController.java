package com.example.schoolmarket.controller;

import com.example.schoolmarket.dao.UserDao;
import com.example.schoolmarket.entity.User;
import com.example.schoolmarket.service.UserServiceImpl;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.PostUpdate;
import javax.servlet.http.HttpServletRequest;
import java.awt.print.Book;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/login")
    public User login(User user){
        User login = userService.Login(user.getUsername(),user.getPassword());
        //System.out.println(login);
        return login;
    }

    @GetMapping("user/getalluser")
    @ResponseBody
    public List<User> getUserList()
    {
        return userService.GetAllUser();
    }

    @GetMapping("user/getuserbyid")
    @ResponseBody
    public User getUserById(Integer id){ return userService.GetUserById(id); }

    @PostMapping("user/adduser")
    @ResponseBody
    public String addUser(User user)
    {
        return userService.AddUser(user);
    }

    @DeleteMapping("user/deleteuserbyid")
    @ResponseBody
    public String deleteUserById(Integer id)
    {
        return userService.DeleteUserById(id);
    }

    @PutMapping("user/updateuser")
    @ResponseBody
    //@UpdateProvider(type = UserDao.class, method = "UpdateUser")
    public String updateUser(User user){
        return userService.UpdateUser(user);
    }
}

package com.example.schoolmarket.dao;

import com.example.schoolmarket.entity.User;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface UserDao {

    //public User login(@Param("username") String username, @Param("password") String password);

    @Select("select * from user order by id desc")
    List<User> GetAllUser();

    @Select("SELECT * FROM user WHERE id = #{id}")
    User GetUserById(Integer id);

    @Select("SELECT * FROM user WHERE username=#{username} AND password=#{password}")
    User Login(String username, String password);

    @Insert("insert into user (username,password,faculty,address,qqnumber,vxnumber) values(#{username},#{password},#{faculty},#{address},#{qqnumber},#{vxnumber})")
    int AddUser(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    int DeleteUserById(Integer id);

    @Update("UPDATE user " +
            "SET username = #{username} , faculty = #{faculty} , address = #{address} , qqnumber = #{qqnumber} , vxnumber=#{vxnumber} " +
            "WHERE id = #{id}")
    int UpdateUser(User user);
}

package com.example.schoolmarket.entity;

import javax.persistence.*;

@Entity
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String faculty;
    private String address;
    private String qqnumber;
    private String vxnumber;

    @Override
    public String toString() {
            return "User{" +
                    "id=" + id +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", faculty='" + faculty + '\'' +
                    ", address='" + address + '\'' +
                    ", qqnumber='" + qqnumber + '\'' +
                    ", vxnumber='" + vxnumber + '\'' +
                    '}';
    }

    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQqnumber() {
        return qqnumber;
    }

    public void setQqnumber(String qqnumber) {
        this.qqnumber = qqnumber;
    }

    public String getVxnumber() {
        return vxnumber;
    }

    public void setVxnumber(String vxnumber) {
        this.vxnumber = vxnumber;
    }

    public User() {
        }
    }



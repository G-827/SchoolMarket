package com.example.schoolmarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan("com.example.schoolmarket")
public class SchoolMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolMarketApplication.class, args);
    }


}

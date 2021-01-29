package com.example.userlogindemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.userlogindemo.repository")
public class UserlogindemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserlogindemoApplication.class, args);
    }

}

package com.example.userlogindemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author nsxconquer
 * @Date 2021/1/28 9:44 PM
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

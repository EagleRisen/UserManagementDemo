package com.example.userlogindemo.service.impl;

import com.example.userlogindemo.entity.User;
import com.example.userlogindemo.repository.UserMapper;
import com.example.userlogindemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author nsxconquer
 * @Date 2021/1/28 10:08 PM
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Integer insertOne(User user) {
        return userMapper.insertOne(user);
    }
}

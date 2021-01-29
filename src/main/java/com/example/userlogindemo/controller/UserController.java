package com.example.userlogindemo.controller;

import com.example.userlogindemo.base.BaseEnums;
import com.example.userlogindemo.base.Result;
import com.example.userlogindemo.entity.User;
import com.example.userlogindemo.service.UserService;
import com.example.userlogindemo.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author nsxconquer
 * @Date 2021/1/28 10:33 PM
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/InsertOne")
    public Result InsertOne(@RequestBody User user) {
        return ResultUtils.successWithData(userService.insertOne(user),
                BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }
}

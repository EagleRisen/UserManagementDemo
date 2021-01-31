package com.example.userlogindemo.controller;

import com.example.userlogindemo.base.BaseEnums;
import com.example.userlogindemo.base.Result;
import com.example.userlogindemo.entity.PageRequest;
import com.example.userlogindemo.entity.PageResult;
import com.example.userlogindemo.entity.User;
import com.example.userlogindemo.service.UserService;
import com.example.userlogindemo.util.ResultUtils;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    /**
     * 插入一条数据
     * @param user
     * @return
     */
    @PostMapping("/insertOne")
    public Result InsertOne(@RequestBody User user) {
        return ResultUtils.successWithData(userService.insertOne(user),
                BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    @PostMapping("/batchInsert")
    public Result InsertMany(@RequestBody List<User> userList) {
        return ResultUtils.successWithData(userService.batchInsert(userList),
                BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    //查询数据
    @GetMapping("/selectOne")
    public Result selectOne(@RequestParam("id") String id) {
        return ResultUtils.successWithData(userService.getUserById(Long.parseLong(id)),
                BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    @GetMapping("/selectAll")
    public Result selectAll() {
        List<User> allUser = userService.getAllUser();
        return ResultUtils.successWithData(allUser, BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    @DeleteMapping("/deleteByUsername")
    public Result deleteByUsername(@RequestParam("username") String username) {
        return ResultUtils.successWithData(userService.deleteByUsername(username),
                BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    @PutMapping("/updateUser")
    public Result updateUserById(@RequestBody User user) {
        User oldUser = userService.getUserById(user.getId());
        if (oldUser != null) {
            return ResultUtils.successWithData(userService.updateById(user),
                    BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
        }else {
            return ResultUtils.failure();
        }
    }

    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        PageResult page = userService.findPage(pageRequest);
        System.out.println("当前第" + page.getPageNum() + "页，共" + page.getTotalSize() + "条，共" + page.getTotalPages() + "页");
        return ResultUtils.successWithData(page.getContent(),
                BaseEnums.SUCCESS.code(), BaseEnums.SUCCESS.desc());
    }

    @GetMapping("/login")
    public String login() {
        System.out.println(PropertiesUtil.class.getClassLoader().getResource("").getPath());
        return "登录界面";
    }

    @PostMapping("/loginCheck")
    public Result login(@RequestBody User user, HttpServletResponse response) {
        return userService.loginCheck(user, response);
    }

    @PostMapping("/loginWithRedis")
    public Result loginWithRedis(@RequestBody User user) {
        Result result = userService.loginWithRedis(user);
        return result;
    }
}

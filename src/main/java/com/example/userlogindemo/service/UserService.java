package com.example.userlogindemo.service;

import com.example.userlogindemo.base.Result;
import com.example.userlogindemo.entity.PageRequest;
import com.example.userlogindemo.entity.PageResult;
import com.example.userlogindemo.entity.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author nsxconquer
 * @Date 2021/1/28 10:08 PM
 * @Version 1.0
 */
public interface UserService {
    public Integer insertOne(User user);

    public Integer batchInsert(List<User> userList);

    public User getUserById(Long id);

    public List<User> getAllUser();

    public Integer deleteByUsername(String username);

    public Integer updateById(User user);

    /**
     * 分页查询接口
     * @param pageRequest
     * @return
     */
    PageResult findPage(PageRequest pageRequest);

    public Result loginCheck(User user, HttpServletResponse response);

    public Result loginWithRedis(User user);
}

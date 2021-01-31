package com.example.userlogindemo.service.impl;

import com.example.userlogindemo.base.Result;
import com.example.userlogindemo.constants.Constants;
import com.example.userlogindemo.entity.PageRequest;
import com.example.userlogindemo.entity.PageResult;
import com.example.userlogindemo.entity.User;
import com.example.userlogindemo.repository.UserMapper;
import com.example.userlogindemo.service.UserService;
import com.example.userlogindemo.util.PageUtils;
import com.example.userlogindemo.util.RedisUtil;
import com.example.userlogindemo.util.ResultUtils;
import com.example.userlogindemo.util.TokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Integer insertOne(User user) {
        return userMapper.insertOne(user);
    }

    @Override
    public Integer batchInsert(List<User> userList) {
        return userMapper.batchInsert(userList);
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getUserList();
    }

    @Override
    public Integer deleteByUsername(String username) {
        return userMapper.deleteByUsername(username);
    }

    @Override
    public Integer updateById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(getPageInfo(pageRequest));
    }

    @Override
    public Result loginCheck(User user, HttpServletResponse response) {
        User user1 = userMapper.getUserByUsername(user.getUsername());
        if (user1 == null) {
            return ResultUtils.failure("用户不存在");
        }
        if (!user1.getPassword().equals(user.getPassword())) {
            return ResultUtils.failure("密码输入错误");
        }

        String token = tokenUtil.generateToken(user1);
        System.out.println("token:" + token);
        Cookie cookie = new Cookie("token", token);
        //设置cookie作用域
        cookie.setPath("/");
        response.addCookie(cookie);
        System.out.println("cookie:" + cookie);
        return ResultUtils.success("登录成功");
    }

    @Override
    public Result loginWithRedis(User user) {
        User user1 = userMapper.getUserByUsername(user.getUsername());
        if (user1 ==  null) {
            return ResultUtils.failure("用户不存在");
        }

        if (!user1.getPassword().equals(user.getPassword())) {
            return ResultUtils.failure("密码输入错误");
        }

        //将原有的token值全部干掉，防止重复登录
        String oldToken = (String) redisUtil.get(user1.getUsername());
        if (oldToken != null) {
            StringBuilder pattern = new StringBuilder("*").append(oldToken).append("*");
            Set<String> keySet = redisUtil.scan(pattern.toString());
            redisUtil.del(keySet.stream().toArray(String[]::new));
        }

        //生成新的token
        String token = tokenUtil.generateToken(user1);
        user1.setToken(token);
        redisUtil.tokenToRedis(user1);
        return ResultUtils.successWithData(user1);
    }

    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<User> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectByPage();
        return new PageInfo<User>(users);
    }
}

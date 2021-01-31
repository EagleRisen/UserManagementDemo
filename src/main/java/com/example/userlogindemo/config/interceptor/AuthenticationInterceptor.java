package com.example.userlogindemo.config.interceptor;

import com.example.userlogindemo.constants.Constants;
import com.example.userlogindemo.util.RedisUtil;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (StringUtil.isEmpty(token)) {
            System.out.println("不存在token");
            response.sendRedirect("/user/login");
            return false;
        }

        String username = "";
        username = (String) redisUtil.get(token);
        System.out.println("redis中用户信息值为" + username);
        if (username != null && !username.trim().equals("")) {
            System.out.println("token值匹配成功！");
            Long tokenBirthTime = Long.valueOf((String) redisUtil.get(username + token));
            Long diff = System.currentTimeMillis() - tokenBirthTime;
            if (diff > Constants.TOKEN_RESET_TIME) {
                redisUtil.expire(username, Constants.TOKEN_EXPIRE_TIME);
                redisUtil.expire(token, Constants.TOKEN_EXPIRE_TIME);
                System.out.println("token有效时间更新成功");
                Long newBirthTime = System.currentTimeMillis();
                redisUtil.set(username + token, newBirthTime.toString());
            }

            return true;
        }
        response.sendRedirect("/user/login");
        return false;
    }
}

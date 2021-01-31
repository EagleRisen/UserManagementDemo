package com.example.userlogindemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.userlogindemo.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
public class TokenUtil {

    /**
     * 生成Token
     * @param user
     * @return
     */
    public String generateToken(User user) {
        Date start = new Date();
        long endTime = System.currentTimeMillis() + 60 * 60 * 1000;
        Date end = new Date(endTime);
        String token = "";
        token = JWT.create()
                .withAudience(Long.toString(user.getId()))
                .withAudience(user.getUsername())
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public static String get(String token, String key) {
        List<String> list = JWT.decode(token).getAudience();
        String userId = list.get(0);
        return userId;
    }

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    /**
     * 获取Token
     * @param request
     * @return
     */
    public String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals("token")) {
                    return c.getValue();
                }
            }
        }

        return null;
    }
}

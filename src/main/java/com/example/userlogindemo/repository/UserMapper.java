package com.example.userlogindemo.repository;

import com.example.userlogindemo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author nsxconquer
 * @Date 2021/1/28 9:49 PM
 * @Version 1.0
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(username, password) values(#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public Integer insertOne(User user);
}

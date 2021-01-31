package com.example.userlogindemo.repository;

import com.example.userlogindemo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Insert("<script>"  +
            "INSERT INTO user(username, password) VALUES" +
            "<foreach collection=\"userList\" item=\"element\" index=\"index\"  separator=\",\">" +
            "(#{element.username},#{element.password})" +
            "</foreach>" +
            "</script>")
    public Integer batchInsert(@Param("userList") List<User> userList);

    @Select("SELECT * FROM user")
    @Results(id = "user", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password")
    })
    public List<User> getUserList();

    @ResultMap("user")
    @Select("SELECT * FROM user WHERE id = #{id}")
    public User getUserById(@Param("id") Long id);

    @ResultMap("user")
    @Select("select * from user where username = #{username}")
    public User getUserByUsername(String username);

    @Delete("delete from user where username = #{username}")
    public Integer deleteByUsername(String username);

    @Update("update user set username = #{username}, password = #{password} where id = #{id}")
    public Integer updateById(User user);

    @ResultMap("user")
    @Select("select * from user")
    public List<User> selectByPage();
}

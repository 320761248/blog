package com.zxz.dao;

import com.zxz.po.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where email=#{username}")
    public User selectUserByUsername(String username);

    @Insert("insert into user(user_name,email,password) values(#{username},#{email},#{password})")
    public int addUser(String username,String email,String password);

    @Select("select * from user where user_id=#{user_id}")
    public User selectUserById(Integer user_id);

}

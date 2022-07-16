package com.edu.zut.mapper;

import com.edu.zut.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User row);

    User selectByPrimaryKey(Integer uid);

    List<User> selectAll();

    int updateByPrimaryKey(User row);

    @Select("select * from tab_user where uname=#{uname}")
    User selectByUname(@Param("uname")String uname);

    @Select("select * from tab_user where uname=#{uname} and upwd=#{upwd}")
    User checkLogin(@Param("uname") String uname, @Param("upwd") String upwd);
}
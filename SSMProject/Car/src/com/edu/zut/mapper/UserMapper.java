package com.edu.zut.mapper;

import com.edu.zut.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    User selectByPrimaryKey(String userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User login(@Param("name") String userName, @Param("pwd") String userPwd);

    int updatePwd(@Param("name") String userName, @Param("pwd") String userPwd,@Param("id") String id);

    @Select("select user_id from tab_user order by user_id desc limit 1")
    String getTheLastId();
}
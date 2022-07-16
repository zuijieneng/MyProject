package com.edu.zut.service;

import com.edu.zut.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserService {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    User selectByPrimaryKey(String userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User login(String userName,String userPwd);

    int updatePwd(@Param("name") String userName, @Param("pwd") String userPwd,@Param("id") String id);

}

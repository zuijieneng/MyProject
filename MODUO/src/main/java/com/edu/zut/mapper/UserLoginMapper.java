package com.edu.zut.mapper;

import com.edu.zut.entity.UserLogin;
import org.springframework.data.repository.CrudRepository;

public interface UserLoginMapper extends CrudRepository<UserLogin,Integer> {
    UserLogin findByUphone(String uphone);
    public UserLogin findUserLoginByUid(String uid);
}

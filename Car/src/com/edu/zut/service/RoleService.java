package com.edu.zut.service;

import com.edu.zut.entity.Role;

import java.util.List;

public interface RoleService {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    Role selectByPrimaryKey(String roleId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
}

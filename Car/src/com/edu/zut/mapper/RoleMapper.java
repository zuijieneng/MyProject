package com.edu.zut.mapper;

import com.edu.zut.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    Role selectByPrimaryKey(String roleId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    @Select("select role_id from tab_role order by role_id desc limit 1")
    String getTheLastId();

    @Select("SELECT DISTINCT(role_name) from tab_role")
    List<String> getAllNames();

    @Select("select role_id from tab_role where role_name=#{roleName} ")
    String getId(String roleName);
}
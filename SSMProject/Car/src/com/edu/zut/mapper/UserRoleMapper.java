package com.edu.zut.mapper;

import com.edu.zut.entity.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("roleId") String roleId);

    @Delete("delete from tab_user_role where user_id=#{userId}")
    int deleteByUid(@Param("userId") String userId);

    int insert(UserRole record);

    List<UserRole> selectAll();


    @Select("select * from tab_user_role where user_id=#{userId} and role_id=#{roleId}")
    UserRole selectOne(@Param("userId") String userId, @Param("roleId") String roleId);
    @Select("select * from tab_user_role where user_id=#{userId}")
    UserRole selectByUserId(@Param("userId") String userId);
}
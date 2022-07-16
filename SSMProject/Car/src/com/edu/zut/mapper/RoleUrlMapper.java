package com.edu.zut.mapper;

import com.edu.zut.entity.RoleUrl;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleUrlMapper {
    int deleteByPrimaryKey(@Param("roleId") String roleId, @Param("urlId") String urlId);

    int insert(RoleUrl record);

    List<RoleUrl> selectAll();

    @Select("select url_id from tab_role_url where role_id=#{roleId}")
    List<String> geturlIdByRoleId(String roleId);
}
package com.edu.zut.service;

import com.edu.zut.entity.RoleUrl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleUrlService {
    int deleteByPrimaryKey(@Param("roleId") String roleId, @Param("urlId") String urlId);

    int insert(RoleUrl record);

    List<RoleUrl> selectAll();
}

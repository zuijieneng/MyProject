package com.edu.zut.service.impl;

import com.edu.zut.entity.RoleUrl;
import com.edu.zut.mapper.RoleUrlMapper;
import com.edu.zut.service.RoleUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleUrlServiceImpl implements RoleUrlService {
    @Autowired
    RoleUrlMapper mapper;
    @Override
    public int deleteByPrimaryKey(String roleId, String urlId) {
        return mapper.deleteByPrimaryKey(roleId,urlId);
    }

    @Override
    public int insert(RoleUrl record) {
        return mapper.insert(record);
    }

    @Override
    public List<RoleUrl> selectAll() {
        return mapper.selectAll();
    }
}

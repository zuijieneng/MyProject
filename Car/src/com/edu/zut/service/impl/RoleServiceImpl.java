package com.edu.zut.service.impl;

import com.edu.zut.entity.Operation;
import com.edu.zut.entity.Role;
import com.edu.zut.mapper.OperationMapper;
import com.edu.zut.mapper.RoleMapper;
import com.edu.zut.mapper.RoleUrlMapper;
import com.edu.zut.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper mapper;
    @Autowired
    private RoleUrlMapper roleUrlMapper;
    @Autowired
    private OperationMapper operationMapper;
    @Override
    public int deleteByPrimaryKey(String roleId) {
        return mapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public int insert(Role record) {
        int index=mapper.getTheLastId().indexOf("_");
        String id=mapper.getTheLastId().substring(index+1);
        String roleId="role_0"+(Integer.parseInt(id)+1);
        record.setRoleId(roleId);
        return mapper.insert(record);
    }
    //检索一个角色，找出角色的所有权限
    @Override
    public Role selectByPrimaryKey(String roleId) {
        List<Operation> list=new ArrayList<>();
        List<String> urlIds = roleUrlMapper.geturlIdByRoleId(roleId);
        for (String urlId:urlIds) {
            Operation operation = operationMapper.selectByPrimaryKey(urlId);
            list.add(operation);
        }
        Role role = mapper.selectByPrimaryKey(roleId);
        role.setOperation(list);
        return role;
    }
    //检索所有角色，要找出角色所有权限
    @Override
    public List<Role> selectAll() {
        List<Role> roles = mapper.selectAll();
        for (Role role:roles) {
            List<Operation> list=new ArrayList<>();
            List<String> urlIds = roleUrlMapper.geturlIdByRoleId(role.getRoleId());
            for (String urlId:urlIds) {
                Operation operation = operationMapper.selectByPrimaryKey(urlId);
                list.add(operation);
            }
            role.setOperation(list);
        }
        return roles;
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return mapper.updateByPrimaryKey(record);
    }
}

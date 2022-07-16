package com.edu.zut.service.impl;

import com.edu.zut.entity.Operation;
import com.edu.zut.exception.OperationException;
import com.edu.zut.mapper.OperationMapper;
import com.edu.zut.mapper.RoleMapper;
import com.edu.zut.mapper.RoleUrlMapper;
import com.edu.zut.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OperationServiceImpl implements OperationService {
    @Autowired
    private OperationMapper mapper;
    @Autowired
    private RoleUrlMapper roleUrlMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public int deleteByPrimaryKey(String urlId) {
        return mapper.deleteByPrimaryKey(urlId);
    }

    @Override
    public int insert(Operation record) {
        //自增
        String urlId="url_"+ UUID.randomUUID().toString().replace("-","").substring(0,12);
        record.setUrlId(urlId);
        if(mapper.selectByName(record.getUrlPath())!=null){
            throw new OperationException("已经存在该角色！");
        }
        return mapper.insert(record);
    }

    @Override
    public Operation selectByPrimaryKey(String urlId) {
        Operation operation = mapper.selectByPrimaryKey(urlId);
        return operation;
    }

    @Override
    public Operation selectByName(String urlPath) {
        return mapper.selectByName(urlPath);
    }

    @Override
    public List<Operation> selectAll() {
        List<Operation> list=mapper.selectAll();
        return list;
    }

    @Override
    public int updateByPrimaryKey(Operation record) {
        return mapper.updateByPrimaryKey(record);
    }
}

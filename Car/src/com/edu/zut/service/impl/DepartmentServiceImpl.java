package com.edu.zut.service.impl;

import com.edu.zut.entity.Department;
import com.edu.zut.exception.DepartmentException;
import com.edu.zut.mapper.DepartmentMapper;
import com.edu.zut.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper mapper;
    @Override
    public int deleteByPrimaryKey(String departmentId) {
        return mapper.deleteByPrimaryKey(departmentId);
    }

    @Override
    public int insert(Department record) {
        if(record.getDepartmentName().equals("")||record.getDepartmentName()==null){
            throw new DepartmentException("部门名称不能为空！");
        }
        String departId=mapper.getTheLastId();
        Integer index=departId.indexOf("_");
        Integer id=Integer.valueOf(departId.substring(index+1)); //_后的数字
        String deId="customer_00"+(id+1); //变成customer_(id+1)
        record.setDepartmentId(deId);
        return mapper.insert(record);
    }

    @Override
    public Department selectByPrimaryKey(String departmentId) {
        return mapper.selectByPrimaryKey(departmentId);
    }

    @Override
    public List<Department> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Department record) {
        return mapper.updateByPrimaryKey(record);
    }




}

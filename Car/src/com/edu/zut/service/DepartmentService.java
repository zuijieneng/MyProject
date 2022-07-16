package com.edu.zut.service;

import com.edu.zut.entity.Department;

import java.util.List;

public interface DepartmentService {
    int deleteByPrimaryKey(String departmentId);

    int insert(Department record);

    Department selectByPrimaryKey(String departmentId);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

}

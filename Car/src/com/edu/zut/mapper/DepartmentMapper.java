package com.edu.zut.mapper;

import com.edu.zut.entity.Department;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String departmentId);

    int insert(Department record);

    Department selectByPrimaryKey(String departmentId);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);
    @Select("select department_id from tab_department order by department_id desc limit 1")
    String  getTheLastId();

    @Select("select department_name from tab_department")
    List<String> getDepartMentsName();

    @Select("select department_id from tab_department where department_name=#{departmentName}")
    String getIdByName(String departmentName);

}
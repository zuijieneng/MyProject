package com.edu.zut.mapper;

import com.edu.zut.entity.Customer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerMapper {
    int deleteByPrimaryKey(String customerId);

    int insert(Customer record);

    Customer selectByPrimaryKey(String customerId);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);

    @Select("select * from tab_customer order by customer_id desc limit 1")
    Customer getTheLast();
    //批量删除
    int deleteByCid(@Param("cid") String cid);

    //获取所有用户的id
    @Select("select customer_id from tab_customer")
    List<String> getAllIds();



}
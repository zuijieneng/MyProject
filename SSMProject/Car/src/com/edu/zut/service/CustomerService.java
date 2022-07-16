package com.edu.zut.service;

import com.edu.zut.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerService {
    int deleteByPrimaryKey(String customerId);

    int insert(Customer record);

    Customer selectByPrimaryKey(String customerId);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);

    int deleteByCid(@Param("cid") String cid);

}

package com.edu.zut.service.impl;

import com.edu.zut.entity.Customer;
import com.edu.zut.mapper.CustomerMapper;
import com.edu.zut.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper mapper;
    @Override
    public int deleteByPrimaryKey(String customerId) {
        return mapper.deleteByPrimaryKey(customerId);
    }

    @Override
    public int insert(Customer record) {
        //实现主键自增的效果
        String thelastcustomerId=mapper.getTheLast().getCustomerId();
        Integer index=thelastcustomerId.indexOf("_");
        Integer id=Integer.valueOf(thelastcustomerId.substring(index+1)); //_后的数字
        String customerId="customer_"+(id+1); //变成customer_(id+1)
        record.setCustomerId(customerId);
        //添加进数据库
        return mapper.insert(record);
    }

    @Override
    public Customer selectByPrimaryKey(String customerId) {
        return mapper.selectByPrimaryKey(customerId);
    }

    @Override
    public List<Customer> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Customer record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByCid(String cid) {
        return mapper.deleteByCid(cid);
    }
}

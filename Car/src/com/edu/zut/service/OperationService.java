package com.edu.zut.service;

import com.edu.zut.entity.Operation;

import java.util.List;

public interface OperationService {
    int deleteByPrimaryKey(String urlId);

    int insert(Operation record);

    Operation selectByPrimaryKey(String urlId);

    Operation selectByName(String urlPath);


    List<Operation> selectAll();

    int updateByPrimaryKey(Operation record);
}

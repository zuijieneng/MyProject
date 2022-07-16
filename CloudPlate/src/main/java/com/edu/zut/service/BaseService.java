package com.edu.zut.service;

import java.util.List;

public interface BaseService<T,PK> {
    int deleteByPrimaryKey(PK key);

    int insert(T row);

    T selectByPrimaryKey(PK key);

    List<T> selectAll();

    int updateByPrimaryKey(T row);

}

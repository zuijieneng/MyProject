package com.edu.zut.service.common;

import java.util.List;

public interface IService<T,K> {
    public List<T> selectAll();
    public T selectById(K id);
    public int save(T entity);
    public int update(T entity);
    public int deleteById(K id);
}

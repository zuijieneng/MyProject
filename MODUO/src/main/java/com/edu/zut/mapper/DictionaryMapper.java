package com.edu.zut.mapper;

import com.edu.zut.entity.Dictionary;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DictionaryMapper extends CrudRepository<Dictionary,Integer> {
    public Dictionary findByDname(String dname);
    public List<Dictionary> findAllByDtype(int dtype);
}

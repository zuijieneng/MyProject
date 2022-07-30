package com.edu.zut.service.impl;

import com.edu.zut.entity.Dictionary;
import com.edu.zut.mapper.DictionaryMapper;
import com.edu.zut.service.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Resource
    private DictionaryMapper dictionaryMapper;


    @Override
    public Dictionary findByDname(String dname) {
        return dictionaryMapper.findByDname(dname);
    }

    @Override
    public List<Dictionary> findAllByDtype(int dtype) {
        return dictionaryMapper.findAllByDtype(dtype);
    }
}

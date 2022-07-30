package com.edu.zut.service;

import com.edu.zut.entity.Dictionary;

import java.util.List;

public interface DictionaryService  {
    public Dictionary findByDname(String dname);
    public List<Dictionary> findAllByDtype(int dtype);
}

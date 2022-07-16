package com.edu.zut.service.impl;
import com.edu.zut.entity.MyFile;
import com.edu.zut.mapper.FileMapper;
import com.edu.zut.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public int deleteByPrimaryKey(Integer key) {
        return fileMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(MyFile row) {
        return fileMapper.insert(row);
    }

    @Override
    public MyFile selectByPrimaryKey(Integer key) {
        return fileMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<MyFile> selectAll() {
        return fileMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(MyFile row) {
        return fileMapper.updateByPrimaryKey(row);
    }

    @Override
    public MyFile selectByFmd5(String md5) {
        return fileMapper.selectByFmd5(md5);
    }
}

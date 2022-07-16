package com.edu.zut.service;

import com.edu.zut.entity.MyFile;

public interface FileService extends BaseService<MyFile,Integer> {
    MyFile selectByFmd5(String md5);
}

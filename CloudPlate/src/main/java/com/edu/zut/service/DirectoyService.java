package com.edu.zut.service;


import com.edu.zut.entity.Directory;
import com.edu.zut.entity.R;

import java.util.List;

public interface DirectoyService extends BaseService<Directory,Integer> {

    R<String> update(Directory row);

    List<Directory> selectAllChild(String dfu, int uid);
    int deleteMany( String dids); //批量删除
}

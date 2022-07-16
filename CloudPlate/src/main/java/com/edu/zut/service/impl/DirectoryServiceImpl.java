package com.edu.zut.service.impl;

import com.edu.zut.entity.Directory;
import com.edu.zut.entity.R;
import com.edu.zut.mapper.DirectoryMapper;
import com.edu.zut.service.DirectoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DirectoryServiceImpl implements DirectoyService {
    @Autowired
    private DirectoryMapper mapper;

    @Override
    public int deleteByPrimaryKey(Integer key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(Directory directory) {
        List<Directory> directories = mapper.selectAllChild(directory.getDfu(), directory.getUid());
        for (Directory dir:directories) {
            if(dir.getDname().equals(directory.getDname())){
                return 0;//文件夹已经存在，插入失败！
            }
        }
        return mapper.insert(directory);
    }

    @Override
    public Directory selectByPrimaryKey(Integer key) {
        return selectByPrimaryKey(key);
    }

    @Override
    public List<Directory> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Directory row) {
        return mapper.updateByPrimaryKey(row);
    }

    @Override
    public R<String> update(Directory row) {
        //如果修改文件夹的名称
        List<Directory> directories = mapper.selectAllChild(row.getDfu(), row.getUid());
        for (Directory dir:directories) {
            if(dir.getDname().equals(row.getDname())){
                return R.error("文件夹名字重复！");
            }
        }
        return R.success("修改成功！");
    }

    @Override
    public List<Directory> selectAllChild(String dfu, int uid) {
        return mapper.selectAllChild(dfu,uid);
    }

    @Override
    public int deleteMany(String dids) {
        return mapper.deleteMany(dids);
    }
}

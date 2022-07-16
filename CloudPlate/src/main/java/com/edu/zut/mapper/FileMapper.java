package com.edu.zut.mapper;

import com.edu.zut.entity.MyFile;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FileMapper {
    int deleteByPrimaryKey(Integer fid);

    int insert(MyFile row);

    MyFile selectByPrimaryKey(Integer fid);

    List<MyFile> selectAll();

   int updateByPrimaryKey(MyFile row);

    @Select("select * from tab_my_file where fmd5=#{fmd5}")
    MyFile selectByFmd5(@Param("fmd5") String fmd5);
}
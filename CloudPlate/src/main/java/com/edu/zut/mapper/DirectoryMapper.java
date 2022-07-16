package com.edu.zut.mapper;

import com.edu.zut.entity.Directory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DirectoryMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(Directory row);

    Directory selectByPrimaryKey(Integer did);

    List<Directory> selectAll();

    int updateByPrimaryKey(Directory row);

    @Select("select * from tab_my_directory where dfu=#{dfu}  and uid=#{uid}")
    List<Directory> selectAllChild(@Param("dfu") String dfu, @Param("uid") int uid);

    int deleteMany(@Param("dids") String dids); //批量删除
}
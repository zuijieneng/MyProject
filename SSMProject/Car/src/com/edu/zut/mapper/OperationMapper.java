package com.edu.zut.mapper;

import com.edu.zut.entity.Operation;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OperationMapper {
    int deleteByPrimaryKey(String urlId);

    int insert(Operation record);

    Operation selectByPrimaryKey(String urlId);

    Operation selectByName(String urlPath);

    List<Operation> selectAll();

    int updateByPrimaryKey(Operation record);

    @Select("SELECT DISTINCT(url_name) FROM tab_url")
    List<String> getAllOperationNames();

    @Select("SELECT url_id FROM tab_url WHERE url_name=#{urlName}")
    String getId(String urlName);
}
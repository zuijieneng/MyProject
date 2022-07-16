package com.edu.zut.mapper;

import com.edu.zut.entity.Dictionary;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DictionaryMapper {
    int deleteByPrimaryKey(Integer dictionaryId);

    int insert(Dictionary record);

    Dictionary selectByPrimaryKey(Integer dictionaryId);

    List<Dictionary> selectAll();

    int updateByPrimaryKey(Dictionary record);

    @Select("SELECT dictionary_name FROM tab_dictionary WHERE dictionary_type=1")
    List<String> getProvinceNames(); //获取所有省的名字

    @Select("SELECT dictionary_id  FROM tab_dictionary WHERE dictionary_name=#{proName}")
    int getDicIdByName(String proName); //知道某个省份的province_id

    @Select("SELECT dictionary_name FROM tab_dictionary WHERE dictionary_type=2 and province_id=#{provinceId}")
    List<String> getCityNames(int provinceId); //获取省对应的市名

}
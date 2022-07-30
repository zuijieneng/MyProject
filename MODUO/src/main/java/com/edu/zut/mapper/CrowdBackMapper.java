package com.edu.zut.mapper;

import com.edu.zut.entity.CrowdBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CrowdBackMapper extends JpaRepository<CrowdBack,String> {
    @Query(value = "select * from crowd_back where pbid=?",nativeQuery = true)
    public List<CrowdBack> listByPbid(String pbid);


    //为了设置主键方便
    @Query(value = "select pcid from  project_crowd",nativeQuery = true)
    public List<String> getmessidlist();
}

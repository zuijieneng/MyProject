package com.edu.zut.mapper;

import com.edu.zut.entity.ProjectBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectBasicMapper extends JpaRepository<ProjectBasic,String> {
    @Query(value = "select pbid from project_basic",nativeQuery = true)
    public List<String> getmessidlist();
    @Query(value = "select * from project_basic where uid=?",nativeQuery = true)
    public List<ProjectBasic> getProsByUid(String uid);
}

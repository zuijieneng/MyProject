package com.edu.zut.mapper;

import com.edu.zut.entity.ProjectCrowd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectCrowdMapper extends JpaRepository<ProjectCrowd,String> {
    public ProjectCrowd findProjectCrowdByPbid(String pbid);

    @Query(value = "select pcid from  project_crowd",nativeQuery = true)
    public List<String> getmessidlist();

}

package com.edu.zut.mapper;

import com.edu.zut.entity.CareProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CareProjectMapper extends JpaRepository<CareProject,String> {
    @Query(value = "select count(*) from user_project_care where pbid=?",nativeQuery = true)
    public Integer getCountByPbid(String pbid);
}

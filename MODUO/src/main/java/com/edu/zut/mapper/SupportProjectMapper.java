package com.edu.zut.mapper;

import com.edu.zut.entity.SupportProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SupportProjectMapper extends JpaRepository<SupportProject,String> {
    @Query(value = "select count(*) from project_user where pbid=?",nativeQuery = true)
    public Integer getCountByPbid(String pbid);
}

package com.edu.zut.mapper;

import com.edu.zut.entity.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TeamUserMapper extends JpaRepository<TeamUser,Integer> {
    @Query(value = "insert into team_user values(?,?)",nativeQuery = true)
    @Modifying
    @Transactional
    public int insert(String pbid,String uid);
}

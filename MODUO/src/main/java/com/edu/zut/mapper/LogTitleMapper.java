package com.edu.zut.mapper;

import com.edu.zut.entity.LogTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LogTitleMapper extends JpaRepository<LogTitle,Object> {
    @Query(value = "insert into log_title values(?,?)",nativeQuery = true)
    @Modifying
    @Transactional
    public int insert(String uloid,String tid);


    //获取动态下的所有话题的id
    @Query(value = "select tid from log_title where uloid=?",nativeQuery = true)
    public List<String> getTopicIdByUloid(String uloid);

    //获取话题下的所有动态id
    @Query(value = "select uloid from log_title where tid=?",nativeQuery = true)
    public List<String> getLouidsByTid(String tid);

}

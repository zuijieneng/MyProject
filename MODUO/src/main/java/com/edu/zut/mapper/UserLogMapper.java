package com.edu.zut.mapper;

import com.edu.zut.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserLogMapper extends JpaRepository<UserLog,String> {
    @Query(value = "select * from user_log where uid=?",nativeQuery = true)
    public List<UserLog> findUserLogsByByUid(String uid);

    //获取所有主键排序
    @Query(value = "select uloid from user_log",nativeQuery = true)
    public List<String> getLogslist();

    //获取某个话题下所有用户的主键
    @Query(value = "SELECT uid from user_log WHERE uloid IN (SELECT uloid FROM log_title WHERE tid = ?)",nativeQuery = true)
    public List<String> listUidsByTid(String tid);



}

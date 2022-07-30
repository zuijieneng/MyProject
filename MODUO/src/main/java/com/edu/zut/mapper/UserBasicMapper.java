package com.edu.zut.mapper;

import com.edu.zut.entity.UserBasic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserBasicMapper extends CrudRepository<UserBasic,String> {
    //某个动态下的用户，一楼
    @Query(value = "SELECT * from user_basic WHERE uid IN (SELECT uid FROM message WHERE m_owner_id=? and target_uid is null)",nativeQuery = true)
    public List<UserBasic> getAllUserByUloid(String m_owner_id);

    //获取支持者列表
    @Query(value = "select * from user_basic where  uid in (select uid from project_user where pbid =?)",nativeQuery = true)
    public List<UserBasic> supportList(String pbid);

    @Query(value = "select uid from user_basic",nativeQuery = true)
    public List<String> listUids();

    @Query(value = "insert into user_user_care values (?,?)",nativeQuery = true)
    @Modifying
    @Transactional
    public int notice(String uzhu_id,String ufen_id);

    //获取一个项目团队的所有成员
    @Query(value = "select * from user_basic where uid in (select uid from team_user where pbid=?)",nativeQuery = true)
    public List<UserBasic> teamUsersByPbid(String pbid);
}

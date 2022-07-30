package com.edu.zut.mapper;

import com.edu.zut.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageMapper extends JpaRepository<Message,String> {
    //获取所有主键排序
    @Query(value = "select messageid from message",nativeQuery = true)
    public List<String> getmessidlist();
    //某个用户的某个的动态下的所有留言,即一楼评论
    @Query(value = "select * from message where uid=? and m_owner_id=? and target_uid is null ",nativeQuery = true)
    public List<Message> getMessagesByUidAndMOwnerId(String uid,String m_owner_id);
    //某个用户下的所有留言，即留言目标是某个用户
    @Query(value = "select * from message where target_uid=? and m_owner_id=?",nativeQuery = true)
    public List<Message> getAllByUid(String target_uid,String m_owner_id);
    //某动态下的所有留言,即一楼评论
    @Query(value = "select * from message where  m_owner_id=? and target_uid is null ",nativeQuery = true)
    public List<Message> getMessagesByMOwnerId(String m_owner_id);

}

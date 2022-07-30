package com.edu.zut.mapper;

import com.edu.zut.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicMapper extends JpaRepository<Topic,String> {
    //获取所有主键排序
    @Query(value = "select tid from title",nativeQuery = true)
    public List<String> gettidslist();

    //前20个热门话题，排序，由于既需要分页又需要排序，因此这里的排序单独写
    @Query(value = "select * from  title order by tuser_count desc limit 20",nativeQuery = true)
    public List<Topic> getHotTopic();



}

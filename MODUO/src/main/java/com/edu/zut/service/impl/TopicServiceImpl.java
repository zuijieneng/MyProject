package com.edu.zut.service.impl;

import com.edu.zut.entity.Message;
import com.edu.zut.entity.Topic;
import com.edu.zut.entity.UserBasic;
import com.edu.zut.entity.UserLog;
import com.edu.zut.entity.dto.TopicDto;
import com.edu.zut.entity.dto.UserBasicDto;
import com.edu.zut.mapper.*;
import com.edu.zut.service.TopicService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {
    @Resource
    private TopicMapper topicMapper;
    @Resource
    private LogTitleMapper logTitleMapper;
    @Resource
    private UserBasicMapper userBasicMapper;
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private UserLogMapper userLogMapper;

    @Override
    public TopicDto getOne(String tid) {
        //话题扩展类
        TopicDto topicDto=new TopicDto();
        Topic topic = topicMapper.findById(tid).get();
        BeanUtils.copyProperties(topic,topicDto);
        //根据话题id获取关系表中所有动态id
        List<String> louidsByTid = logTitleMapper.getLouidsByTid(tid);
        List<UserLog> userLogList=new ArrayList<>();
        louidsByTid=louidsByTid.stream().map((item)->{
            //获取该动态并添加到集合中
            UserLog userLog=userLogMapper.findById(item).get();
            userLogList.add(userLog);
            //查看该动态下的所有用户
            List<UserBasic> userBasics = userBasicMapper.getAllUserByUloid(item);
            //遍历每个用户，获取每个用户下的留言，copy只扩展类，将所有扩展类存入集合中
            List<UserBasicDto> userBasicDtoList=new ArrayList<>();
            userBasics=userBasics.stream().map((item2)->{
                //获取到该动态下所有留言,即一楼的评论
                List<Message> messageList = messageMapper.getMessagesByMOwnerId(item);
                UserBasicDto userDto=new UserBasicDto();
                userDto.setMymessageList(messageList);
                List<Message> allByUid = messageMapper.getAllByUid(item2.getUid(),item);
                BeanUtils.copyProperties(item2,userDto);  //存储基本的用户信息
                userDto.setMessageList(allByUid); //存储了用户下的留言集合
                userBasicDtoList.add(userDto); //存储在扩展类集合中
                return item2;
            }).collect(Collectors.toList());
            //扩展类集合存储到动态扩展类集合中,此时已经有了楼中楼,即二楼评论
            topicDto.setUserBasicDtoList(userBasicDtoList);
            //复制，属性名必须一致
//            topicDto.setMessageList(messageList);
            return item;
        }).collect(Collectors.toList());
        topicDto.setUserLogList(userLogList);
        return topicDto;
    }
}

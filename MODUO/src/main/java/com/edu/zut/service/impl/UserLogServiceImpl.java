package com.edu.zut.service.impl;

import com.edu.zut.entity.*;
import com.edu.zut.entity.dto.UserBasicDto;
import com.edu.zut.entity.dto.UserLogDto;
import com.edu.zut.mapper.*;
import com.edu.zut.service.UserLogService;
import com.edu.zut.util.IdHandlerUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class UserLogServiceImpl implements UserLogService {
    @Resource
    private UserBasicMapper userBasicMapper;
    @Resource
    private UserLogMapper userLogMapper;
    @Resource
    private TopicMapper topicMapper;
    @Resource
    private LogTitleMapper logTitleMapper;
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private ZanXuMapper zanXuMapper;
    //发布动态与话题
    @Override
    public UserLogDto saveLogAndTopic(UserLogDto userLogDto) {
        //将所有属性复制给父类动态类
        UserLog userLog=new UserLog();
        BeanUtils.copyProperties(userLogDto, userLog);
        //为动态设置主键
        List<String> logslist = userLogMapper.getLogslist();
        if(logslist.size()==0){
            userLog.setUloid("log_1");
        }else {
            Integer theLastId = IdHandlerUtil.getTheLastId(logslist);
            userLog.setUloid("log_"+theLastId);
        }
        userLog.setUlogTime(new Date());
        userLogMapper.save(userLog);
        userLogDto.setUloid(userLog.getUloid());
        //设置话题
        List<Topic> topics = userLogDto.getTopicList();
        if(topics.size()!=0){ //说明引用了话题或者创建了话题
            topics= topics.stream().map((item)->{
                if(item.getTid()==null||item.getTid().equals("")){ //说明该话题不存在，那么就是创建话题
                    //设置话题时间
                    item.setTtime(new Date());
                    //设置话题的帖子个数
                    item.setTlogCount(1);
                    if (!item.getTcontent().equals("")||item.getTcontent()!=null){
                        //设置主键
                        List<String> gettidslist = topicMapper.gettidslist();
                        //设置ID
                        if (gettidslist.size()!= 0) {
                            Integer theLastId = IdHandlerUtil.getTheLastId(gettidslist);
                            item.setTid("title_"+theLastId);
                        }else {
                            item.setTid("title_"+(int)(Math.random()*10)); //每个动态一次性最多添加10个话题
                        }
                        try { ///如果是新创建的话题，那么就添加到数据库中,这里会因为空指针抛出一个异常
                            Topic topic = topicMapper.findById(item.getTid()).get();
                        }catch (NoSuchElementException e){
                            System.out.println(e.getMessage());
                        }finally { //如果是已经有的，只会更新，如果没有就会创建，不管怎么样都要执行这步
                            topicMapper.save(item);
                        }
                    }
                }else { //否则就是引用话题而不是创建话题，那么就设置话题的动态个数+1，并且将动态话题存储在关系表中
                    //当前用户的id
                    String uid=userLog.getUid();
                    //查找关系表中该话题下的所有用户id，查看该用户是否在动态话题关系表中，如果在不改变讨论人数，如果不在，修改话题讨论人数
                    List<String> list = userLogMapper.listUidsByTid(item.getTid());
                    //如果不存在，讨论人数+1
                    if(!list.contains(uid)){
                        Topic topic = topicMapper.findById(item.getTid()).get();
                        topic.setTuserCount(topic.getTuserCount()+1);
                        topicMapper.save(topic);
                    }
                    //发动态的人数+1
                    Topic topic = topicMapper.findById(item.getTid()).get();
                    topic.setTlogCount(topic.getTlogCount()+1);
                    topicMapper.save(topic);
                }
                //无论是存在改话题还是不存在，只要话题存在，就往关系表添加数据
                logTitleMapper.insert(userLog.getUloid(),item.getTid());
                return item;

            }).collect(Collectors.toList());
        }
        userLogDto.setTopicList(topics);
        return userLogDto;
    }

    //查看个人动态
    @Override
    public R<List<UserLogDto>> findWithMessageUnderUser(String uid) {
        //获取扩展类
        List<UserLogDto> userLogDtoList=new ArrayList<>();
        //获取所有动态
        List<UserLog> userLogList = userLogMapper.findUserLogsByByUid(uid);
        //查找每个动态下的所有留言
        userLogList=userLogList.stream().map((item)->{
            //计算评论数目
            AtomicReference<Integer> comments= new AtomicReference<>(0);
            //扩展类
            UserLogDto userLogDto=new UserLogDto();
            //父类属性复制给扩展类
            BeanUtils.copyProperties(item,userLogDto);
            //查看动态的点赞
            userLogDto.setLikeCount(zanXuMapper.getZanCount(item.getUloid()));
            //查看该动态下的所有用户
            List<UserBasic> userBasics = userBasicMapper.getAllUserByUloid(item.getUloid());
//            userLogDto.setUserBasicList(userBasics);
            //遍历每个用户，获取每个用户下的留言，copy只扩展类，将所有扩展类存入集合中
            List<UserBasicDto> userBasicDtoList=new ArrayList<>();
            userBasics=userBasics.stream().map((item2)->{
                //用户扩展类，包含自己的评论和自己楼下的所有评论
                UserBasicDto userDto=new UserBasicDto();
                //获取到该动态下所有留言,即一楼的评论,自己的评论
                List<Message> messageList = messageMapper.getMessagesByUidAndMOwnerId(item2.getUid(), item.getUloid());
                userDto.setMymessageList(messageList);
                //获取二楼的评论,被评论
                List<Message> allByUid = messageMapper.getAllByUid(item2.getUid(),item.getUloid());
                BeanUtils.copyProperties(item2,userDto);  //存储基本的用户信息
                userDto.setMessageList(allByUid); //存储了用户下的留言集合
                userBasicDtoList.add(userDto); //存储在扩展类集合中
                comments.updateAndGet(v -> v + allByUid.size()+messageList.size());
                return item2;
            }).collect(Collectors.toList());
            //扩展类集合存储到动态扩展类集合中,此时已经有了楼中楼,即二楼评论
            userLogDto.setUserBasicDtoList(userBasicDtoList);
            //该动态下的留言数,这里计算量
            userLogDto.setCommentCount(comments.get());
//            //复制，属性名必须一致
//            userLogDto.setMessageList(messageList);
            //获取关系表中的所有动态话题的数据
            List<String> byUloid = logTitleMapper.getTopicIdByUloid(item.getUloid());
            //为了获取动态下的所有话题
            List<Topic> topicList=new ArrayList<>();
            //遍历检索话题表
            byUloid = byUloid.stream().map((titem) -> {
                Topic topic = topicMapper.findById(titem).get();
                topicList.add(topic);
                return titem;
            }).collect(Collectors.toList());
            //复制给扩展类
            userLogDto.setTopicList(topicList);
            userLogDtoList.add(userLogDto);
            return item;
        }).collect(Collectors.toList());
        if(userLogDtoList.size()==0) return R.error("暂时没有动态");
        return R.success(userLogDtoList);
    }
}

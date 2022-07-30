package com.edu.zut.service.impl;

import com.edu.zut.entity.Message;
import com.edu.zut.entity.R;
import com.edu.zut.mapper.LogTitleMapper;
import com.edu.zut.mapper.MessageMapper;
import com.edu.zut.mapper.TopicMapper;
import com.edu.zut.service.MessageService;
import com.edu.zut.util.IdHandlerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private LogTitleMapper logTitleMapper;
    @Resource
    private TopicMapper topicMapper;

    @Override
    public R<Message> sendOnlyWithLog(String uid, Message message) {
        if(message.getMOwnerId()==null||message.getMOwnerId().equals("")) return R.error("必须填写所属对象");
        if (message.getMessageContent().equals("")||message.getMessageContent()==null) return R.error("请输入内容!");
        //操作留言表
        message.setUid(uid);
        message.setMessageTime(new Date());
        message.setTargetUid(null);
        //获取留言ID
        List<String> getmessidlist = messageMapper.getmessidlist();
        if (getmessidlist.size()== 0) {
            message.setMessageid("messageid_1");
        }else {
            //根据工具类获取了最后一个ID的后缀：比如message_12是最后一个，那么thLastId=13
            Integer theLastId= IdHandlerUtil.getTheLastId(getmessidlist);
            message.setMessageid("messageid_"+theLastId);
        }
        messageMapper.save(message);  //使用save只会更新不会添加
        return R.success(message);
    }

    @Override
    public R<Message> sendToUser(String uid, Message message) {
        System.out.println(message.toString());
        if(message.getMOwnerId()==null||message.getMOwnerId().equals("")) return R.error("必须填写所属对象");
        if (message.getMessageContent().equals("")||message.getMessageContent()==null) return R.error("请输入内容!");
        //操作留言表
        message.setUid(uid);
        message.setMessageTime(new Date());
        //获取留言ID
        List<String> getmessidlist = messageMapper.getmessidlist();
        if (getmessidlist.size()== 0) {
            message.setMessageid("messageid_1");
        }else {
            //根据工具类获取了最后一个ID的后缀：比如message_12是最后一个，那么thLastId=13
            Integer theLastId= IdHandlerUtil.getTheLastId(getmessidlist);
            message.setMessageid("messageid_"+theLastId);
        }
        messageMapper.save(message);  //使用save只会更新不会添加
        return R.success(message);
    }

//    @Override
//    public R<List<Message>> listById(String uloid, String uid, String project) {
//        if(uloid!=null||!uloid.equals("")){ //如果是请求动态下的一楼评论
//            List<Message> logsMess = messageMapper.getAllByLogId(uloid);
//        }
//    }
}

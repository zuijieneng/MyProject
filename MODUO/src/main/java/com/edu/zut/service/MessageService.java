package com.edu.zut.service;

import com.edu.zut.entity.Message;
import com.edu.zut.entity.R;

public interface MessageService {
    public R<Message> sendOnlyWithLog(String uid, Message message);
    public R<Message> sendToUser(String uid, Message message);
    //获取动态或用户或项目下的留言
//    public R<List<Message>> listById(String uloid,String uid,String project);
}

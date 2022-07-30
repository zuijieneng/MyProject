package com.edu.zut.service;

import com.edu.zut.entity.R;
import com.edu.zut.entity.dto.UserLogDto;

import java.util.List;

public interface UserLogService  {
    public UserLogDto saveLogAndTopic(UserLogDto userLogDto);
    public R<List<UserLogDto>> findWithMessageUnderUser(String uid);

}

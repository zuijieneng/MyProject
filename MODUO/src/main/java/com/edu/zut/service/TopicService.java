package com.edu.zut.service;

import com.edu.zut.entity.dto.TopicDto;

public interface TopicService {
    public TopicDto getOne(String tid); //获取话题以及话题的所有相关信息，包括话题的讨论人数和动态人数
}

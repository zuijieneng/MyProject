package com.edu.zut.controller;

import com.edu.zut.entity.R;
import com.edu.zut.entity.Topic;
import com.edu.zut.entity.UserBasic;
import com.edu.zut.entity.dto.TopicDto;
import com.edu.zut.mapper.TopicMapper;
import com.edu.zut.service.TopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/topic")
@Api(tags = "话题")
public class TopicController {
    @Resource
    private TopicMapper topicMapper;
    @Resource
    private TopicService topicService;


    @GetMapping("/list")
    @ApiOperation("获取前20个热门话题")
    public R<Page<Topic>> list(int page,int pageSize){
        Page<Topic> all = topicMapper.findAll(PageRequest.of(page, pageSize));
        if(all.getSize()==0) return R.error("暂时没有话题");
        return R.success(all);
    }

    /**
     * 根据id获取话题详情，比如发布动态的时候引用话题，这里省略不写
     */
    @GetMapping("/detail")
    @ApiOperation("查看某个话题详情")
    public R<TopicDto> detail(String tid){
        TopicDto one = topicService.getOne(tid);
        return R.success(one);
    }




}

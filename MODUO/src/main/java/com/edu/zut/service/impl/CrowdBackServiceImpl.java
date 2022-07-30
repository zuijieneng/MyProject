package com.edu.zut.service.impl;

import com.edu.zut.entity.CrowdBack;
import com.edu.zut.mapper.CrowdBackMapper;
import com.edu.zut.service.CrowdBackService;
import com.edu.zut.util.IdHandlerUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CrowdBackServiceImpl implements CrowdBackService {
    @Resource
    private CrowdBackMapper crowdBackMapper;

    @Override
    public CrowdBack saveWithProject(CrowdBack back) {
        //设置主键,获取回报档最后一个等级
        if(crowdBackMapper.findAll().size()==0){
            //设置ID
            back.setCid("back_1");
            //设置等级
            back.setClevel("回报档1");
        }else {
            List<String> getmessidlist = crowdBackMapper.getmessidlist();
            Integer theLastId = IdHandlerUtil.getTheLastId(getmessidlist);
            //设置ID
            back.setCid("back_"+theLastId);
            //设置等级
            back.setClevel("回报档"+theLastId);
        }
        return crowdBackMapper.save(back);
    }
}

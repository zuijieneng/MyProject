package com.edu.zut.service.impl;

import com.edu.zut.entity.ProjectCrowd;
import com.edu.zut.mapper.ProjectCrowdMapper;
import com.edu.zut.service.ProjectCrowdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProjectCrowdServiceImpl implements ProjectCrowdService {
    @Resource
    private ProjectCrowdMapper projectCrowdMapper;

    @Override
    public ProjectCrowd saveWithNothing(ProjectCrowd crowd) {
        crowd.setPcrowdStart(new Date()); //设置起始时间
        //设置主键ID
        List<String> getmessidlist = projectCrowdMapper.getmessidlist();
        if(getmessidlist.size()==0){
            crowd.setPcid("crowd_1");
        }else {
            ProjectCrowd oneByPbid = projectCrowdMapper.findProjectCrowdByPbid(crowd.getPbid());
            crowd.setPcid(oneByPbid.getPcid());
        }
        crowd = projectCrowdMapper.save(crowd);
        return crowd;
    }
}

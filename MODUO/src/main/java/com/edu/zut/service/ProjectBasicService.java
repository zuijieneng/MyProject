package com.edu.zut.service;

import com.edu.zut.entity.ProjectBasic;
import com.edu.zut.entity.dto.ProjectBasicDto;

import java.util.List;

public interface ProjectBasicService {

    //发现项目，包括点击分类进行分类，点击分页进行分页，原生态处理....
    public List<ProjectBasicDto> selectAll(int page, int pageSize, Integer ptype, String pdevelopType, String sort);

    //查看项目详情
    public ProjectBasicDto detailOne(String pbid);

    //保存基础信息
    public ProjectBasic saveWithNothing(ProjectBasic basic);

}

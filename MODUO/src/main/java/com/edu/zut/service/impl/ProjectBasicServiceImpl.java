package com.edu.zut.service.impl;

import com.edu.zut.entity.*;
import com.edu.zut.entity.dto.ProjectBasicDto;
import com.edu.zut.entity.dto.UserBasicDto;
import com.edu.zut.exception.PageException;
import com.edu.zut.mapper.*;
import com.edu.zut.service.ProjectBasicService;
import com.edu.zut.util.IdHandlerUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectBasicServiceImpl implements ProjectBasicService {
    @Resource
    private ProjectBasicMapper projectBasicMapper;
    @Resource
    private ProjectCrowdMapper projectCrowdMapper;
    @Resource
    private EntityManager entityManager;
    @Resource
    private CareProjectMapper careProjectMapper;
    @Resource
    private ProjectCrowdServiceImpl projectCrowdService;
    @Resource
    private DictionaryMapper dictionaryMapper;
    @Resource
    private SupportProjectMapper supportProjectMapper;
    @Resource
    private ImgMp4Mapper imgMp4Mapper;
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private UserBasicMapper userBasicMapper;

    @Override
    public List<ProjectBasicDto> selectAll(int page,int pageSize,Integer ptype, String pdevelopType, String sort) {
        String creiter="";
        if(pdevelopType.equals("")||pdevelopType==null) return null;
        //判断是否是众筹
        if(pdevelopType.equals("全部")){ //所有并且审核通过的
            creiter+="select * from project_basic where pstatus=1 ";
        }else if(pdevelopType.contains("众筹")){ //是众筹并且审核通过的
            creiter+="select * from project_basic where pbid in (select pbid from project_crowd) and pdevelop_type='众筹' and pstatus=1 ";
        }else { //其他的类型并且审核通过的
            creiter+="select * from project_basic where pstatus=1 and pdevelop_type="+pdevelopType;
        }
        List<ProjectBasicDto> projectBasicDtos=new ArrayList<>();
        //其他的条件：判段传来的参数是否为空
        if(ptype==72){
            creiter+="";
        }else if(!ptype.equals("")||ptype!=null){
            creiter+= " and ptype="+ptype;  //如果类型存在，那么就继续拼凑字符串
        }else if(!pdevelopType.equals("")||pdevelopType!=null){
            creiter+= " and pdevelop_type ="+pdevelopType;
        }
        //基本条件：上来就判断sort是哪个字段，拼接SQL（无论其他的条件为空与否，sort参数类型不可能为空）
        if(sort.equals("最新上线")){ //最新上线
            creiter+=" order by ptime desc";
        }else if(sort.equals("金额最高")) { //金额最高
            creiter+= " order by pcrowd_money desc";
        }else if(sort.equals("评论最多")){ //评论最高
            //select * from project_basic where pbid not in (select pbid from project_crowd) and pbid in (SELECT m_owner_id  FROM message GROUP BY m_owner_id ORDER BY count(*) desc)
            creiter+= " and pbid in (SELECT m_owner_id FROM message GROUP BY m_owner_id ORDER BY count(*) desc)";
        }
        //分页
//        SELECT * FROM message limit 0,4;  第一页
//        SELECT * FROM message limit 4,4;  第二页
//        SELECT * FROM message limit 8,4;  第三页
        if(page==0&&pageSize!=0){
            creiter+=" limit 0,"+pageSize;
        }else if(page!=0&&pageSize!=0){
            creiter+=" limit "+(page-1)*pageSize+","+pageSize;
        }
        int size = projectBasicMapper.findAll().size();
        int pageCount=size/pageSize+size%pageSize>0?1:0;
        if(page>pageCount){
            throw new PageException("超过了总页数！");
        }
        System.out.println("最后的查询SQL为====="+creiter);
        //条件查询
        Query query=entityManager.createNativeQuery(creiter);
        //获取非众筹项目的基本信息
        List<ProjectBasic> projectBasics = new ArrayList<>();
        List<ProjectBasic> projectBasicList = query.getResultList();
        //由于不能强制转换，因此每个对象必须单独的处理数据，否则后续无法使用对象的get、set方法
        try{ //解析时异常的捕捉
            for (Object row:projectBasicList) {
                Object[] cells=(Object[])row;
                ProjectBasic projectBasic=new ProjectBasic(String.valueOf(cells[0]),String.valueOf(cells[1]),Integer.parseInt(String.valueOf(cells[2])),String.valueOf(cells[3]),String.valueOf(cells[4]),String.valueOf(cells[5]),String.valueOf(cells[6]),new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(cells[7])),Integer.parseInt(String.valueOf(cells[8])));
                projectBasics.add(projectBasic);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("所有的非众筹基本信息=="+projectBasics);
        //遍历复制到扩展类中
        projectBasics.stream().map((item)->{
            //为每个众筹项目复制基本信息
            ProjectBasicDto dto=new ProjectBasicDto();
            //设置类型
            Dictionary dictionary = dictionaryMapper.findById(item.getPtype()).get();
            dto.setType(dictionary.getDname());
            //设置关注、支持个数
            dto.setSupportCount(supportProjectMapper.getCountByPbid(item.getPbid()));
            dto.setCareCount(careProjectMapper.getCountByPbid(item.getPbid()));
            //查看数据库是否是众筹项目
            ProjectCrowd crowd = projectCrowdMapper.findProjectCrowdByPbid(item.getPbid());
            if(crowd!=null){ //说明是众筹项目
                //复制众筹项目
                dto.setProjectCrowd(crowd);
            }else { //说明不是众筹项目
                dto.setProjectCrowd(null);
            }
            BeanUtils.copyProperties(item,dto);
            projectBasicDtos.add(dto);
            return item;
        }).collect(Collectors.toList());
//        //获取众筹项目
//        List<ProjectCrowd> projectCrowds = projectCrowdMapper.findAll();
//        //遍历众筹项目
//        projectCrowds.stream().map((item)->{
//            //为每个众筹项目复制基本信息
//            ProjectBasicDto dto=new ProjectBasicDto();
//            //复制众筹项目
//            dto.setProjectCrowd(item);
//            //复制基本信息
//            ProjectBasic projectBasic = projectBasicMapper.findById(item.getPbid()).get();
//            BeanUtils.copyProperties(pdevelopType,dto);
//            projectBasicDtos.add(dto);
//            return item;
//        }).collect(Collectors.toList());
        return projectBasicDtos;
    }

    /*
    1：如果是预热、众筹、众筹成功的项目，关注表+基本信息+众筹表
    2：如果是创意，关注表+基本信息表
    */
    @Override
    public ProjectBasicDto detailOne(String pbid) {
        //根据id查找
        ProjectBasic projectBasic = projectBasicMapper.findById(pbid).get();
        //无论什么情况都需要基本表+关注表，需要基本信息+关注人数+图片+评论列表楼中楼
        ProjectBasicDto dto=new ProjectBasicDto();
        BeanUtils.copyProperties(projectBasic,dto); //基本信息
        Integer careCount = careProjectMapper.getCountByPbid(pbid); //关注的人数
        dto.setCareCount(careCount==null?0:careCount);
        dto.setPicList(imgMp4Mapper.getList(pbid)); //图片列表
        List<UserBasic> userBasics = userBasicMapper.getAllUserByUloid(pbid); //项目下留言的所有用户
        List<UserBasicDto> userBasicDtos=new ArrayList<>(); //扩展类存放用户信息以及用户的评论和被评论
        userBasics=userBasics.stream().map((item)->{
            UserBasicDto userBasicDto=new UserBasicDto();
            BeanUtils.copyProperties(item,userBasicDto);
            List<Message> mymessageList = messageMapper.getMessagesByUidAndMOwnerId(item.getUid(), pbid);
            List<Message> secondeMessgaeList = messageMapper.getAllByUid(item.getUid(), pbid);
            userBasicDto.setMymessageList(mymessageList);
            userBasicDto.setMessageList(secondeMessgaeList);
            userBasicDtos.add(userBasicDto);
            return item;
        }).collect(Collectors.toList()); //获取评论
        dto.setCommentList(userBasicDtos); //设置了评论列表
        dto.setCommentCount(userBasicDtos.size()); //评论人数
        //获取项目类型
        Dictionary dictionary = dictionaryMapper.findById(projectBasic.getPtype()).get();
        dto.setType(dictionary.getDname());
        //判断该项目是哪一种状态，分别需要展示什么数据
        String pdevelopType=projectBasic.getPdevelopType();
        //如果不是创意要多操作一步，获取结束时间等crowd表的内容+支持的人数
        if (!pdevelopType.equals("创意")){
            //获取众筹表信息
            ProjectCrowd crowd = projectCrowdMapper.findProjectCrowdByPbid(pbid);
            dto.setProjectCrowd(crowd);
            //获取支持人数
            Integer supportCount = supportProjectMapper.getCountByPbid(pbid);
            dto.setSupportCount(supportCount==null?0:supportCount);
            //获取支持者列表
            List<UserBasic> supportList = userBasicMapper.supportList(pbid);
            dto.setSupprotList(supportList);
        }
        return dto;
    }

    @Override
    public ProjectBasic saveWithNothing(ProjectBasic basic) {
        //设置主键
        if(projectBasicMapper.findAll().size()==0){
            basic.setPbid("project_1");
        }else {
            Integer theLastId = IdHandlerUtil.getTheLastId(projectBasicMapper.getmessidlist());
            basic.setPbid("project_"+theLastId);
        }
        basic.setPtime(new Date());
        projectBasicMapper.save(basic);
        if(basic.getPdevelopType().equals("众筹")){
            //设置主键ID
            List<String> getmessidlist = projectCrowdMapper.getmessidlist();
            ProjectCrowd crowd = new ProjectCrowd(null, basic.getPbid(), 0f, new Date(), null);
            if(getmessidlist.size()==0){
                crowd.setPcid("crowd_1");
            }else {
                Integer theLastId = IdHandlerUtil.getTheLastId(getmessidlist);
                crowd.setPcid("crowd_"+theLastId);
            }
            crowd.setPbid(basic.getPbid());
            projectCrowdMapper.save(crowd);
        }
        return basic;
    }
}

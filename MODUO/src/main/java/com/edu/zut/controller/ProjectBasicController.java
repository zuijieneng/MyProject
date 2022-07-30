package com.edu.zut.controller;

import com.edu.zut.entity.*;
import com.edu.zut.entity.dto.ProjectBasicDto;
import com.edu.zut.entity.dto.ProjectTeamDto;
import com.edu.zut.mapper.*;
import com.edu.zut.service.CrowdBackService;
import com.edu.zut.service.ProjectBasicService;
import com.edu.zut.service.ProjectCrowdService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
@Api(tags = "项目的基本操作")
public class ProjectBasicController {

    @Resource
    private ProjectBasicService service;
    @Resource
    private ProjectBasicMapper mapper;
    @Resource
    private DictionaryMapper dictionaryMapper;
    @Resource
    private CareProjectMapper careProjectMapper;
    @Resource
    private SupportProjectMapper supportProjectMapper;
    @Resource
    private CrowdBackMapper crowdBackMapper;
    @Resource
    private CrowdBackService crowdBackService;
    @Resource
    private ImgMp4Mapper imgMp4Mapper;
    @Resource
    private ProjectCrowdService projectCrowdService;
    @Resource
    private TeamUserMapper teamUserMapper;
    @Resource
    private ProjectTeamMapper projectTeamMapper;
    @Resource
    private UserBasicMapper userBasicMapper;

    @Value("${project.path}") //图片存放路径
    private String path;

    //获取项目类型
    @ApiOperation("获取项目类型")
    @GetMapping("/category")
    public R<List<Dictionary>> getCategory(){
        List<Dictionary> dictionaries = dictionaryMapper.findAllByDtype(6);
        return R.success(dictionaries);
    }

    //发现项目
    @ApiOperation("发现项目")
    @GetMapping("/listNormal")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "第几页",required = true),
            @ApiImplicitParam(name = "pageSize",value = "每页多少个",required = true),
            @ApiImplicitParam(name = "ptype",value = "项目类型编码:72是全部",required = true),
            @ApiImplicitParam(name = "pdevelopType",value = "项目状态分类：全部、预热、创意、众筹、众筹成功",required = true),
            @ApiImplicitParam(name = "sort",value = "排序类型:最新上限，金额最高，评论最多",required = true),
    })
    public R<List<ProjectBasicDto>> getlist(int page,int pageSize,Integer ptype,String pdevelopType,String sort){
        List<ProjectBasicDto> projectBasicDtos =null;
        try{
            projectBasicDtos = service.selectAll(page,pageSize,ptype, pdevelopType, sort);
        }catch (NullPointerException e){
            return R.error("暂时没有发现项目");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.success(projectBasicDtos);
    }

    @ApiOperation("查找自己的的所有项目")
    @GetMapping("/list")
    public R<List<ProjectBasic>> listR(HttpSession session){
        UserBasic user =(UserBasic) session.getAttribute("user");
        List<ProjectBasic> prosByUid = mapper.getProsByUid(user.getUid());
        return prosByUid.size()!=0?R.success(prosByUid):R.error("暂未发起项目");
    }

    //查看项目详情
    @ApiOperation("查看项目详情")
    @GetMapping("/detail")
    public R<ProjectBasicDto> detail(String pbid){
        ProjectBasicDto dto = service.detailOne(pbid);
        return R.success(dto);
    }

    //关注项目
    @GetMapping("/care")
    @ApiOperation("关注项目")
    public R<String> care(String pbid, HttpSession session){
        UserBasic user=(UserBasic)session.getAttribute("user");
        careProjectMapper.save(new CareProject(pbid,user.getUid()));
        return R.success("关注成功");
    }

    //支持项目
    /**
     * 这里没有设计到金额问题，支持多少钱
     * @param pbid
     * @param session
     * @return
     */
    @GetMapping("/support")
    @ApiOperation("支持项目")
    public R<String> support(String pbid, HttpSession session){
        UserBasic user=(UserBasic)session.getAttribute("user");
        supportProjectMapper.save(new SupportProject(pbid,user.getUid()));
        return R.success("支持成功");
    }

    //项目回报列表
    @GetMapping
    @ApiOperation("项目回报列表")
    @ApiImplicitParam(name = "pbid",value = "项目编号",required = true)
    public R<List<CrowdBack>> list(String pbid){
        List<CrowdBack> crowdBacks = crowdBackMapper.listByPbid(pbid);
        return R.success(crowdBacks);
    }


    /**
     * 上传图片
     * @param left
     * @param right
     * @param session
     * @return
     */
    //项目图片上传,titleimglist
    @PostMapping(value = "uploadtitle",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    @ApiOperation("基础信息的图片上传")
    public R<String> uploadPicLog(@ApiParam(value = "项目图片" ,required = true) @RequestParam("left")MultipartFile left,@ApiParam(value = "项目图片" ,required = true) @RequestParam("right")MultipartFile right, HttpSession session)  {
        File fileMdi=new File(path);
        if(!fileMdi.exists()){
            fileMdi.mkdir();
        }
        List<ImgMp4> logimglists=new ArrayList<>();
        String photoRightName="projectapp"+System.currentTimeMillis()+left.getOriginalFilename();
        String photoLeftName="projectapp"+System.currentTimeMillis()+right.getOriginalFilename();
        ImgMp4 imgMp4L=new ImgMp4(null,path+"\\"+photoLeftName,null);
        ImgMp4 imgMp4R=new ImgMp4(null,path+"\\"+photoRightName,null);
        logimglists.add(imgMp4L);
        logimglists.add(imgMp4R);
        imgMp4Mapper.save(imgMp4L);
        imgMp4Mapper.save(imgMp4R);
        try {
            left.transferTo(new File(path,photoLeftName));
            right.transferTo(new File(path,photoRightName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setAttribute("titleimglist",logimglists);
        return R.success("上传成功");
    }

    //项目图片上传,titleimglist
    @PostMapping(value = "uploadback",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    @ApiOperation("上传回报档的照片")
    public R<String> uploadPicBack(@ApiParam(value = "回报档图片" ,required = true) @RequestParam("files")List<MultipartFile> files, HttpSession session) throws IOException {
        File fileMdi=new File(path);
        if(!fileMdi.exists()){
            fileMdi.mkdir();
        }
        List<ImgMp4> logimglists=new ArrayList<>();
        files.stream().map((item)->{
            String photoName="crowdback"+System.currentTimeMillis()+item.getOriginalFilename();
            ImgMp4 imgMp4=new ImgMp4(null,path+"\\"+photoName,null);
            System.out.println("img4==="+imgMp4.toString());
            imgMp4Mapper.save(imgMp4);
            logimglists.add(imgMp4);
            try {
                item.transferTo(new File(path,photoName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return item;
        }).collect(Collectors.toList());
        session.setAttribute("crowdbackimgs",logimglists);
        return R.success("上传成功");
    }

    /**
     * 发起项目的所有流程如下
     * @param basic
     * @return
     */
    @PostMapping("/saveBasic")
    @ApiOperation("添加基础信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pcontent",required = true),
            @ApiImplicitParam(name = "pdevelopType",required = true),
            @ApiImplicitParam(name = "pname",required = true),
            @ApiImplicitParam(name = "pshowFirst",required = true),
            @ApiImplicitParam(name = "ptype",required = true)
    })
    public R<String> save(@ApiIgnore ProjectBasic basic,HttpSession session){
        UserBasic user =(UserBasic) session.getAttribute("user");
        //基本校验
        if(basic.getPname().equals("")||basic.getPname()==null) return R.error("参数校验失败");
        if(basic.getPshowFirst().equals("")||basic.getPshowFirst()==null) return R.error("未填写项目简介");
        if(basic.getPtype()==null||basic.getPtype()==0) return R.error("请为你的项目选择一个合适的类别");
        if(basic.getPdevelopType()==null||basic.getPdevelopType().equals("")) return R.error("设置项目状态：创意、众筹");
        //设置项目的发起者
        basic.setUid(user.getUid());
        //保存并校验其他的信息
        ProjectBasic projectBasic = service.saveWithNothing(basic);
        //如果上传了图片设置每张图片的所属对象
        List<ImgMp4> imgs =(List<ImgMp4>) session.getAttribute("titleimglist");
        if(imgs!=null){
            imgs.stream().map((item)->{
                item.setImOwnerId(projectBasic.getPbid());
                imgMp4Mapper.save(item);
                return item;
            }).collect(Collectors.toList());
        }
        session.setAttribute("basic",projectBasic);
        //添加基础信息，项目初稿算是完成，这时候需要向团队表添加成员
        teamUserMapper.save(new TeamUser(projectBasic.getPbid(),user.getUid()));
        return R.success("保存成功");
    }

    //发起项目
    @PostMapping("/saveCrowd")
    @ApiOperation("添加众筹目标")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pcrowdMoney",value = "众筹金额",required = true)
    })
    public R<String> save(@ApiIgnore ProjectCrowd crowd, HttpSession session){
        ProjectBasic basic =(ProjectBasic) session.getAttribute("basic");
        if(basic==null) return R.error("请回到基础信息页面创建项目标题");
        if(!basic.getPdevelopType().equals("众筹")) {
            session.removeAttribute("basic");
            return R.error("该项目是创意类型，不能众筹，填错表单了你");
        };
        crowd.setPbid(basic.getPbid()); //设置pbid
        return projectCrowdService.saveWithNothing(crowd)==null?R.error("保存失败"):R.success("保存成功");
    }

    //发起项目
    @PostMapping("/saveCrowdBack")
    @ApiOperation("添加设置回报")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ctitle",value = "回报档标题",required = true),
            @ApiImplicitParam(name = "cmoney",value = "回报档金额",required = true),
            @ApiImplicitParam(name = "cdescribe",value = "回报档描述",required = true),
            @ApiImplicitParam(name = "cpostWay",value = "邮费方式",required = true),
            @ApiImplicitParam(name = "csendDate",value = "回报发放日期",required = true),
            @ApiImplicitParam(name = "csendWay",value = "回报发放方式",required = true),
    })
    public R<String> saveBack(@ApiIgnore CrowdBack back,HttpSession session){
        ProjectBasic basic =(ProjectBasic) session.getAttribute("basic");
        if(basic==null) return R.error("请回到基础信息页面创建项目标题");
        if(!basic.getPdevelopType().equals("众筹")) return R.error("该项目是创意类型，不能众筹，填错表单了你");
        back.setPbid(basic.getPbid());
        CrowdBack crowdBack = crowdBackService.saveWithProject(back);
        List<ImgMp4> imgs =(List<ImgMp4>) session.getAttribute("crowdbackimgs");
        if(imgs!=null){
            imgs.stream().map((item)->{
                item.setImOwnerId(crowdBack.getCid());
                imgMp4Mapper.save(item);
                return item;
            }).collect(Collectors.toList());
        }
        return R.success("保存成功");
    }

    //上传详情编辑的图片
    @PostMapping(value = "uploaddetail",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    @ApiOperation("添加详情编辑")
    public R<String> uploadPicLog(@ApiParam(value = "详情图片" ,required = true) @RequestParam("files")List<MultipartFile> files, HttpSession session) throws IOException {
        ProjectBasic basic =(ProjectBasic) session.getAttribute("basic");
        if(basic==null) return R.error("请回到基础信息页面创建项目标题");
        File fileMdi=new File(path);
        if(!fileMdi.exists()){
            fileMdi.mkdir();
        }
        files.stream().map((item)->{
            String photoName="projectdetail"+System.currentTimeMillis()+item.getOriginalFilename();
            ImgMp4 imgMp4=new ImgMp4(null,path+"\\"+photoName,basic.getPbid());
            imgMp4Mapper.save(imgMp4);
            try {
                item.transferTo(new File(path,photoName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return item;
        }).collect(Collectors.toList());
        return R.success("保存成功");
    }

    //获取本项目中的团队成员
    @GetMapping("/team")
    @ApiOperation("获取团队成员")
    public R<List<UserBasic>> teamUers(HttpSession session){
        ProjectBasic basic =(ProjectBasic) session.getAttribute("basic");
        if(basic==null) return R.error("请回到基础信息页面创建项目标题");
        //获取一个项目团对的所有成员
        List<UserBasic> userBasics = userBasicMapper.teamUsersByPbid(basic.getPbid());
        return R.success(userBasics);
    }
    //发起项目
    @PostMapping("/saveTeam")
    @ApiOperation("添加团队设置")
    public R<String> save(ProjectTeamDto team, HttpSession session){
        ProjectBasic basic =(ProjectBasic) session.getAttribute("basic");
        if(basic==null) return R.error("请回到基础信息页面创建项目标题");
        team.setPbid(basic.getPbid());
        List<String> now = team.getUserBasicList();
        if(now.size()!=0){
            now.stream().map((item)->{
                try {
                    UserBasic userBasic = userBasicMapper.findById(item).get();
                }catch (NoSuchElementException no){
                    return R.error("该成员不存在");
                }
                teamUserMapper.insert(basic.getPbid(),item);
                return item;
            }).collect(Collectors.toList());
        }
        ProjectTeam projectTeam=new ProjectTeam(team.getPbid(), team.getPhone(), team.getPqq(), team.getPtemail(), team.getPworker());
        System.out.println("projeTeam----"+projectTeam.toString());
        ProjectTeam save = projectTeamMapper.save(projectTeam);
        session.removeAttribute("basic");
        return save!=null?R.success("保存成功"):R.error("保存失败");
    }

    //发起项目
//    @PostMapping("/saveCrowdBack")
//    @ApiOperation("添加资质认证")
//    public R<String> save(CrowdBack ,HttpSession session){
//
//    }
}

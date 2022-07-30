package com.edu.zut.controller;

import com.edu.zut.entity.*;
import com.edu.zut.entity.dto.UserLogDto;
import com.edu.zut.mapper.*;
import com.edu.zut.service.UserLogService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/log")
@Api(tags ="发布动态")
public class UserLogController {
    @Resource
    private UserLogService userLogService;
    @Resource
    private UserLogMapper userLogMapper;
    @Resource
    private TopicMapper topicMapper;
    @Value("${log.path}")
    private String path; //图片存放路径
    @Resource
    private ImgMp4Mapper imgMp4Mapper; //动态的图片，留言的图片
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private LogTitleMapper logTitleMapper;
    @Resource
    private ZanXuMapper zanXuMapper;

    @GetMapping("/list")
    @ApiOperation("您的所有动态")
    public R<List<UserLogDto>>  list(HttpSession session){
        //获取用户id
        UserBasic user = (UserBasic)session.getAttribute("user");
        Iterator<UserLog> iterator = userLogMapper.findAll().iterator();
        List<UserLog> list=new ArrayList<>();
        iterator.forEachRemaining(list::add);
        //
        return userLogService.findWithMessageUnderUser(user.getUid());
//        if(userLogDto==null) return R.error("暂时没有动态噢！");
//        return R.success(userLogDto);
    }

    @PostMapping(value = "upload",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    @ApiOperation("上传动态图片或视频")
    public R<String> uploadPicLog(@ApiParam(value = "动态图片" ,required = true) @RequestParam("files")List<MultipartFile> files, HttpSession session) throws IOException {
        File fileMdi=new File(path);
        if(!fileMdi.exists()){
            fileMdi.mkdir();
        }
        List<ImgMp4> logimglists=new ArrayList<>();
        files.stream().map((item)->{
            String photoName="userlog"+System.currentTimeMillis()+item.getOriginalFilename();
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
        session.setAttribute("logimglists",logimglists);
        return R.success("上传成功");
    }

    @PostMapping(value = "/add")
    @ApiOperation("用户发布动态，其中包括创建话题，引用话题，如果是引用话题请填写tid")
    public R<String> add(@RequestBody UserLogDto userLogDto, HttpSession session) {
        UserBasic user = (UserBasic)session.getAttribute("user");
        userLogDto.setUid(user.getUid()); //设置uid
        UserLogDto stringR = userLogService.saveLogAndTopic(userLogDto); //添加动态并生成话题,此时已经有了ID
        List<ImgMp4> imgs = (List<ImgMp4>)session.getAttribute("logimglists");//为每个图片设置所属的动态id
        if(imgs!=null){
            imgs=imgs.stream().map((item)->{
                System.out.println("所属对象=="+stringR.getUloid());
                item.setImOwnerId(stringR.getUloid());
                imgMp4Mapper.save(item);
                return item;
            }).collect(Collectors.toList());
        }
        //销毁session，防止重复使用
        session.removeAttribute("logimglists");
        return  stringR!=null?R.success("发布成功"):R.error("发布失败");
    }

    @GetMapping("/zanxv")
    @ApiOperation("点赞或踩动态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zx",value = "赞1踩-1",required = true),
            @ApiImplicitParam(name = "zxOwnerId",value = "被赞的编号",required = true)
    })
    public R<String> zanxv(ZanXu zx,HttpSession session){
        UserBasic user =(UserBasic) session.getAttribute("user");
        zanXuMapper.dianZx(user.getUid(),zx.getZxOwnerId(),zx.getZx());
        return R.success(zx.getZx()==1?"点赞成功":"点嘘成功");
    }
}

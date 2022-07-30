package com.edu.zut.controller;

import com.edu.zut.entity.ImgMp4;
import com.edu.zut.entity.Message;
import com.edu.zut.entity.R;
import com.edu.zut.entity.UserBasic;
import com.edu.zut.mapper.ImgMp4Mapper;
import com.edu.zut.mapper.MessageMapper;
import com.edu.zut.service.MessageService;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message")
@Api(tags = "发送留言")
public class MeassageController  {
    @Resource
    private MessageMapper messageMapper;
    @Resource
    private MessageService messageService;
    @Value("${message.path}")
    private String path;
    @Resource
    private ImgMp4Mapper imgMp4Mapper;

    /**
     * 情况一：用户对某个动态直接留言
     * 情况二：用户对某个动态下的用户留言
     * 情况三：用户对项目留言
     * 情况四：用户对某个项目下的用户留言
     * 综上所述：最多需要三个id才能显示所有留言，包括楼中楼
     * 情况一和情况二可以合并，但是这里为了方便理解，多写了一部分
     */

    @PostMapping(value = "upload",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    @ApiOperation("评论图片")
    public R<String> uploadPicLog(@ApiParam(value = "留言图片" ,required = true) @RequestParam("files") List<MultipartFile> files, HttpSession session) throws IOException {
        //获取当前用户的ID
        UserBasic user = (UserBasic)session.getAttribute("user");
        //上传图片ing
        File fileMdi=new File(path);
        if(!fileMdi.exists()){
            fileMdi.mkdir();
        }

        List<ImgMp4> messimglists=new ArrayList<>();
        files=files.stream().map((item)->{
            //设置图片名，存储到数据库中，先存到数据库，至于所属ID，再评论的请求中再赋予，因此第三个参数为null
            String photoName="message"+System.currentTimeMillis()+item.getOriginalFilename();
            ImgMp4 imgMp4=new ImgMp4(null,path+"\\"+photoName,null);
            System.out.println("img4==="+imgMp4.toString());
            imgMp4Mapper.save(imgMp4);
            //添加到集合中，到时候在请求中遍历更新目标对象的ID，一个动态下到底是对动态留言还是对用户留言
            messimglists.add(imgMp4);
            try {
                item.transferTo(new File(path,photoName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return item;
        }).collect(Collectors.toList());
        session.setAttribute("messimglists",messimglists);
        return R.success("上传成功");
    }


    @GetMapping("/send")
    @ApiOperation("用户直接留言，一楼")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "MOwnerId",value = "所属对象ID，项目或动态",required = true),
            @ApiImplicitParam(name = "messageContent",value = "留言内容",required = true)
    })
    public R<Message> sendOne(@ApiIgnore Message message, HttpSession session){
        //获取当前用户的ID
        UserBasic user = (UserBasic)session.getAttribute("user");
        R<Message> stringR = messageService.sendOnlyWithLog(user.getUid(), message);
        //操作图片视频表
        Message data = stringR.getData();
        List<ImgMp4> messimglists = (List<ImgMp4>)session.getAttribute("messimglists");
        if(messimglists!=null){  //只要请求了上传图片
            messimglists.stream().map((item)->{
                //直接设置留言编号
                item.setImOwnerId(data.getMessageid());
                imgMp4Mapper.save(item);
                return item;
            }).collect(Collectors.toList());
        }
        //销毁，防止二次使用
        session.removeAttribute("messimglists");
        return stringR;
    }

    @GetMapping("/sendUser")
    @ApiOperation("用户对用户留言，二楼")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "MOwnerId",value = "哪个项目/动态",required = true),
            @ApiImplicitParam(name = "targetUid",value = "对谁留言",required = true),
            @ApiImplicitParam(name = "messageContent",value = "留言内容",required = true)
    })
    public R<Message> sendUser(@ApiIgnore Message message,HttpSession session){
        //获取当前用户的ID
        UserBasic user = (UserBasic)session.getAttribute("user");
        R<Message> stringR = messageService.sendToUser(user.getUid(), message);
        //操作图片视频表
        Message data = stringR.getData();
        List<ImgMp4> messimglists = (List<ImgMp4>)session.getAttribute("messimglists");
        if(messimglists!=null){  //只要请求了上传图片
            messimglists.stream().map((item)->{
                //直接设置留言编号
                item.setImOwnerId(data.getMessageid());
                imgMp4Mapper.save(item);
                return item;
            }).collect(Collectors.toList());
        }
        //销毁，防止二次使用
        session.removeAttribute("messimglists");
        return stringR;
    }




}

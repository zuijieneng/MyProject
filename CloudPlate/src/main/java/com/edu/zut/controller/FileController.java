package com.edu.zut.controller;

import com.edu.zut.entity.Directory;
import com.edu.zut.entity.MyFile;
import com.edu.zut.entity.R;
import com.edu.zut.entity.User;
import com.edu.zut.exception.FileException;
import com.edu.zut.service.DirectoyService;
import com.edu.zut.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;

@Controller("file")
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private DirectoyService directoryService;

    /**
     * 文件上传
     * @param photo
     * @param directory
     * @param req
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public R<String> upload(@RequestParam("photo")MultipartFile photo,Directory directory, HttpServletRequest req) throws IOException {
        if(photo.getBytes().length==0) throw new FileException("上传失败！");
        //用户uid
        User user =(User) req.getSession().getAttribute("user");
        //内容md5
        String fmd5=DigestUtils.md5DigestAsHex(photo.getBytes());
        //封装起来
        MyFile file=new MyFile(null,directory.getDname(),fmd5,photo.getBytes().length);
        //路径
        String path=req.getServletContext().getRealPath("/imgs");
        //首先判断文件是否存在
        MyFile existFile = fileService.selectByFmd5(fmd5);
        //无论存在不存在，都要directory表，为了提高代码复用性，提前设置属性
        if(existFile==null){//不存在，要先操作File
            fileService.insert(file);
            File upload=new File(path,photo.getOriginalFilename()); //注意这里是直接从这个对象获取名字
            photo.transferTo(upload); //上传
        }
        file=fileService.selectByFmd5(fmd5); //如果不存在，但是上面已经添加过了，因此变成了存在，查询数据库；如果存在，直接查询数据库
        directory.setDname(photo.getOriginalFilename());
        directory.setDtype(1);
        directory.setDtime(new Date());
        directory.setUid(user.getUid());
        directory.setDsize(photo.getBytes().length);
        directory.setDprivate(0);
        directory.setDfid(file.getFid()); //很重要，是下载和上传的关键
        directoryService.insert(directory);
        return R.success("上传成功！");
    }

    /**
     * 下载文件
     * @param dfid
     * @param req
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<byte[]> download(int dfid,HttpServletRequest req) throws IOException {
        MyFile myFile=fileService.selectByPrimaryKey(dfid);
        //获取文件的类型
        String type=req.getSession().getServletContext().getMimeType(myFile.getFurl());
        //获取真实路径
        String realPath=req.getSession().getServletContext().getRealPath("/imgs");
        File yuanFile=new File(realPath,myFile.getFurl());
        byte[] arr= FileUtils.readFileToByteArray(yuanFile);

        //创建heander对象  响应头
        HttpHeaders headers=new HttpHeaders();
        headers.set("Content-Type", type);
        headers.set("Content-Disposition", "attachment;filename="+ URLEncoder.encode(myFile.getFurl(), "utf-8"));
        //获取源文件
        //context.get
        ResponseEntity<byte[]> entity=new ResponseEntity<byte[]>(arr,headers, HttpStatus.OK);
        return entity;
    }
}

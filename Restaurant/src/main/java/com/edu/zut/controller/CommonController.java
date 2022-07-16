package com.edu.zut.controller;

import com.edu.zut.entity.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件的上传与下载的测试
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Value("${restaurant.path}") //引入application.yml中参数的方式
    private String path;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        try {
            file.transferTo(new File(path,file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(file.getOriginalFilename());
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        //输入流，通过输入流读取文件内容
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path+name));

            //输出流，通过输出流将文件写回浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

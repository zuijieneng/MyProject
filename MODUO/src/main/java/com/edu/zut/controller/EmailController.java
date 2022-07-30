//package com.edu.zut.controller;
//
//import com.edu.zut.service.EmailService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.mail.MessagingException;
//
//@Controller
//@RequestMapping("/email")
//@Api(tags ="Email发送邮件")
//public class EmailController {
//    @Autowired
//    private EmailService emailService;
//
//    @GetMapping("/sendText")
//    @ResponseBody
//    @ApiOperation("发送文字")
//    public String sendText(){
//        emailService.sendText();
//        return "发送文字成功！";
//    }
//
//    @PostMapping("/sendPic")
//    @ResponseBody
//    @ApiOperation("发送图片")
//    public String sendPic() throws MessagingException {
//        emailService.sendPic();
//        return "发送图片成功！";
//    }
//}

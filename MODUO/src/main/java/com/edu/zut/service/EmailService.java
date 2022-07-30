package com.edu.zut.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Service
public class EmailService {
    @Resource
    private JavaMailSenderImpl sender;

    public void sendText(){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setSubject("我是邮件标题");
        message.setText("我是邮件内容:SimpleMailMessage");
        message.setSentDate(new Date()); //邮件发送时间
        message.setFrom("15290285123@163.com"); //谁发送
        message.setTo("15290285123@163.com"); //谁接收
        sender.send(message);
    }
    public void sendPic() throws MessagingException {
        MimeMessage mimeMessage=sender.createMimeMessage();
        MimeMessageHelper message=new MimeMessageHelper(mimeMessage,true);
        message.setSubject("我是邮件标题");
        message.setText("<p>我是邮件内容:MimeMessageHelper,我向你发送两张图片</p>" +
                "<p>第一张图片：</p>" +
                "<img src='cid:p01'"+
                "<p>第二张图片：</p>"+
                "<img src='cid:p02'",
                true
                );
        message.addInline("p01",new FileSystemResource("C:\\Users\\hi\\Pictures\\壁纸\\好事发生.jpg"));
        message.addInline("p02",new FileSystemResource("C:\\Users\\hi\\Pictures\\壁纸\\限定冰可乐.png"));
        message.addAttachment("nginx-1.14.2.tar.gz",new File("C:\\Users\\hi\\Downloads\\nginx-1.14.2.tar.gz"));
        message.setSentDate(new Date()); //邮件发送时间
        message.setFrom("15290285123@163.com"); //谁发送
        message.setTo("15290285123@163.com"); //谁接收
        sender.send(mimeMessage);
    }

}

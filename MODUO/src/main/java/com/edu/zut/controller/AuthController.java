package com.edu.zut.controller;

import com.edu.zut.entity.*;
import com.edu.zut.mapper.CompanyAuthMapper;
import com.edu.zut.mapper.PersonAuthMapper;
import com.edu.zut.mapper.UserBasicMapper;
import com.edu.zut.service.CompanyAuthService;
import com.edu.zut.service.DictionaryService;
import com.edu.zut.service.PersonAuthService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/auth")
@Api(tags ="认证")
public class AuthController {
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private PersonAuthService personAuthService;
    @Resource
    private PersonAuthMapper personAuthMapper;
    @Resource
    private UserBasicMapper userBasicMapper;
    @Resource
    private CompanyAuthService companyAuthService;
    @Resource
    private CompanyAuthMapper companyAuthMapper;

    @Value("${auth.path}")
    private String path;

    @ApiOperation("获取所有的证件类型")
    @GetMapping("/paperTypeList")
    public R<List<Dictionary>> getAllPaperType(){
        List<Dictionary> paperTypeList = dictionaryService.findAllByDtype(5);
        return R.success(paperTypeList);
    }


    @ApiOperation("根据证件名称获取该证件在字典表中的证件编号：如：身份证（中国大陆）")
    @GetMapping("/paperid")
    @ApiImplicitParam(name = "dname",value = "证件类型名称",required = true)
    public R<Dictionary> getAllPaperType(String dname){
        Dictionary byDname = dictionaryService.findByDname(dname);
        return R.success(byDname);
    }


    @PostMapping(value = "/person",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    @ApiOperation("添加个人认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "urealName", value = "真实姓名", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "upaperTypeId", value = "证件类型编号", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "upaperId", value = "证件编号", dataTypeClass = Integer.class, required = true),
            @ApiImplicitParam(name = "upaperStart", value = "证件起始时间", dataTypeClass = Date.class, required = true),
            @ApiImplicitParam(name = "upaperEnd", value = "证件结束时间", dataTypeClass = Date.class, required = true),
            @ApiImplicitParam(name = "uaddress", value = "联系地址", dataTypeClass = String.class, required = true),
            @ApiImplicitParam(name = "uemail", value = "用户邮箱", dataTypeClass = String.class, required = true)
    })
    public R<String> personAuth(@ApiIgnore PersonAuth personAuth, @ApiParam(value = "证件照前" ,required = true) @RequestParam("photoFront") MultipartFile front, @ApiParam(value = "证件照后" ,required = true) @RequestParam("photoBack") MultipartFile back, HttpSession session) throws IOException {
        UserBasic user =(UserBasic) session.getAttribute("user");
        int idenx=front.getOriginalFilename().indexOf(".");
        String suffix=front.getOriginalFilename().substring(idenx);
        String frontphotoName="personauthfront"+user.getUid()+suffix;
        String backphotoName="personauthback"+user.getUid()+suffix;
        front.transferTo(new File(path,frontphotoName));
        back.transferTo(new File(path,backphotoName));
        personAuth.setUpaperFrontPhoto(frontphotoName);
        personAuth.setUpaperBackPhoto(backphotoName);
        personAuth.setUid(user.getUid());
        System.out.println("个人认证"+personAuth.toString());
        R<String> save = personAuthService.save(personAuth);
        if(save.getCode()==200){  //说明认证成功
            user.setUtype(1);
            userBasicMapper.save(user);
        }
        return save;
    }



    @PostMapping(value = "/company",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    @ApiOperation("添加企业认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ucompanyName", required = true),
            @ApiImplicitParam(name = "ucompanyId", required = true),
            @ApiImplicitParam(name = "ucompanyPerson", required = true),
            @ApiImplicitParam(name = "ucompanyPersonPaperTypeId", required = true),
            @ApiImplicitParam(name = "ucompanyPersonPaperId", required = true),
            @ApiImplicitParam(name = "ucompanyPersonPaperStart", required = true),
            @ApiImplicitParam(name = "ucompanyPersonPaperEnd", required = true),
            @ApiImplicitParam(name = "ucompanyLicenseStart", required = true),
            @ApiImplicitParam(name = "ucompanyLicenseEnd", required = true),
            @ApiImplicitParam(name = "ucompanyShow", required = true),
            @ApiImplicitParam(name = "ucompanyAddress", required = true),
            @ApiImplicitParam(name = "ucompanyPhone", required = true),
            @ApiImplicitParam(name = "ucompanyEmail", required = true),
            @ApiImplicitParam(name = "ucompanyUrl", required = true),
    })
    public R<String> companyAuth(@ApiIgnore CompanyAuth companyAuth, @ApiParam(value = "证件照前" ,required = true) @RequestParam("photoFront") MultipartFile front, @ApiParam(value = "证件照后" ,required = true) @RequestParam("photoBack") MultipartFile back, @ApiParam(value = "证件照前" ,required = true) @RequestParam("licensePhoto") MultipartFile licensePhoto,HttpSession session) throws IOException {
        System.out.println("企业认证="+companyAuth.toString());
        UserBasic user =(UserBasic) session.getAttribute("user");
        int idenx=front.getOriginalFilename().indexOf(".");
        String suffix=front.getOriginalFilename().substring(idenx);
        String frontphotoName="companyauthfront"+user.getUid()+suffix;
        String backphotoName="companyauthback"+user.getUid()+suffix;
        String licensephotoName="companyauthlicense"+user.getUid()+suffix;
        front.transferTo(new File(path,frontphotoName));
        back.transferTo(new File(path,backphotoName));
        licensePhoto.transferTo(new File(path,licensephotoName));
        companyAuth.setUcompanyLicensePhoto(licensephotoName);
        companyAuth.setUcompanyPersonFrontPhoto(frontphotoName);
        companyAuth.setUcompanyPersonBackPhoto(backphotoName);
        companyAuth.setUid(user.getUid());
        R<String> save = companyAuthService.save(companyAuth);
        if(save.getCode()==200){
            user.setUtype(2);
            userBasicMapper.save(user);
        }
        return save;
    }

    @GetMapping("/listperson")
    @ApiOperation("获取个人认证信息")
    public R<PersonAuth> getOne(HttpSession session){
        UserBasic userBasic=(UserBasic)session.getAttribute("user");
        PersonAuth personAuth = personAuthMapper.findPersonAuthByUid(userBasic.getUid());
        return personAuth!=null?R.success(personAuth):R.error("未进行过任何个人认证操作");
    }

    @GetMapping("/listcompany")
    @ApiOperation("获取公司认证信息")
    public R<CompanyAuth> getOneC(HttpSession session){
        UserBasic userBasic=(UserBasic)session.getAttribute("user");
        CompanyAuth companyAuth = companyAuthMapper.findCompanyAuthByUid(userBasic.getUid());
        return companyAuth!=null?R.success(companyAuth):R.error("未进行过任何个人认证操作");
    }

}

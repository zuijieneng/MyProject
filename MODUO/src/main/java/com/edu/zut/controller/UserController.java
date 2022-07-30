package com.edu.zut.controller;

import com.edu.zut.entity.*;
import com.edu.zut.mapper.*;
import com.edu.zut.service.AccountService;
import com.edu.zut.service.UserBasicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags ="用户操作")
@Slf4j
public class UserController {
    @Resource
    private UserBasicService userBasicService;
    @Resource
    private UserBasicMapper userBasicMapper;
    @Resource
    private UserLoginMapper userLoginMapper;
    @Resource
    private AddressBookMapper addressBookMapper;
    @Resource
    private DictionaryMapper dictionaryMapper;
    @Resource
    private AccountService accountService;
    @Resource
    private AccountMapper accountMapper;


    @GetMapping("/personalMess")
    @ApiOperation("个人信息")
    R<UserBasic> perSonalMessage(HttpSession session){
        UserBasic user = (UserBasic)session.getAttribute("user");
        if (user==null) return R.error("登录之后才能查看个人信息");
        return R.success(user);
    }

    @GetMapping("/listAddresses")
    @ApiOperation("地址列表")
    public R<List<AddressBook>> listAddresses(HttpSession session){
        UserBasic user = (UserBasic)session.getAttribute("user");
        List<AddressBook> list = addressBookMapper.getAllByUid(user.getUid());
//        List<AddressBook> list=new ArrayList<>();
//        iterator.forEachRemaining(list::add); //JDK8新特性，将itreator转为list
        log.info("列出所有地址{}",list);
        if(list.size()==0) return R.error("未添加地址");
        return R.success(list);
    }

    @GetMapping("/saveAddress")
    @ApiOperation("添加收获地址")
    public R<String> save(AddressBook addressBook, HttpSession session){
        UserBasic user = (UserBasic)session.getAttribute("user");
        System.out.println("收获地址="+addressBook.toString());
        Dictionary province = dictionaryMapper.findByDname(addressBook.getUaddressProvince());
        Dictionary city = dictionaryMapper.findByDname(addressBook.getCityText());
        if (addressBook.getUaddressName().equals("")) addressBook.setUaddressName("我的地址");
        addressBook.setUid(user.getUid());
        addressBook.setProvinceId(province.getDid());
        addressBook.setCityId(city.getDid());
        UserLogin userLogin = userLoginMapper.findUserLoginByUid(user.getUid());
        userLogin.setUscore(userLogin.getUscore()+10);
        userLoginMapper.save(userLogin);
        addressBookMapper.save(addressBook);
        return R.success("添加地址成功");
    }


    @GetMapping("/province")
    @ApiOperation("获取所有省份")
    public R<List<Dictionary>> getAllProvince(){
        List<Dictionary> allByDtype = dictionaryMapper.findAllByDtype(1);
        return R.success(allByDtype);
    }


    @GetMapping("/city")
    @ApiOperation("获取所有城市")
    public R<List<Dictionary>> getAllCity(){
        List<Dictionary> allByDtype = dictionaryMapper.findAllByDtype(2);
        return R.success(allByDtype);
    }

    @GetMapping("/listAccount")
    @ApiOperation("列出所有账户")
    public R<List<Account>> listAllAccount(HttpSession session){
        UserBasic user = (UserBasic)session.getAttribute("user");
//        Iterator<Account> iterator = accountMapper.findAll().iterator();
//        List<Account> list=new ArrayList<>();
//        iterator.forEachRemaining(list::add);
        List<Account> list = accountMapper.getAllByUid(user.getUid());
        if(list.size()==0) return R.error("未添加账户");
        return R.success(list);
    }

    @GetMapping("/saveAccount")
    @ApiOperation("添加账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uaccountBankId",value = "银行卡类型编号",required = true),
            @ApiImplicitParam(name = "uaccountTypeId",value = "储蓄卡0，信用卡1",required = true),
            @ApiImplicitParam(name = "uaccountId",value = "卡号",required = true),
            @ApiImplicitParam(name = "uaccountOwner",value = "拥有者姓名",required = true),
            @ApiImplicitParam(name = "uaccountOwnerPersonId",value = "身份证号",required = true),
    })
    public R<String> save(@ApiIgnore Account account, HttpSession session){
        UserBasic user = (UserBasic)session.getAttribute("user");
        account.setUid(user.getUid());
        if (account.getUaccountId().equals("")|account.getUaccountId()==null) return R.error("填写银行卡号");
        if (account.getUaccountOwner().equals("")||account.getUaccountOwner()==null) return R.error("请填写账号拥有者姓名");
        if (!account.getUaccountOwnerPersonId().matches("^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$")) return R.error("银行卡号格式错误");
        accountMapper.save(account);
        UserLogin userLogin = userLoginMapper.findUserLoginByUid(user.getUid());
        userLogin.setUscore(userLogin.getUscore()+30);
        userLoginMapper.save(userLogin);
        return R.success("添加账户成功");
    }

    @GetMapping("/banktype")
    @ApiOperation("银行卡类型")
    public R<List<Dictionary>>  listbankType(){
        List<Dictionary> allByDtype = dictionaryMapper.findAllByDtype(4);
        return R.success(allByDtype);
    }

    @GetMapping("/cardtype")
    @ApiOperation("获取账户类型")
    public R<List<Dictionary>>  listcardType(){
        List<Dictionary> allByDtype = dictionaryMapper.findAllByDtype(3);
        return R.success(allByDtype);
    }

    @GetMapping("/notice")
    @ApiOperation("关注用户")
    public R<String> notice(String uid,HttpSession session){
        UserBasic user =(UserBasic) session.getAttribute("user");
        if (uid.equals(user.getUid())) return R.error("自己不能关注自己");
        int notice = userBasicMapper.notice(uid, user.getUid());
        return notice==1?R.success("关注成功"):R.error("关注失败");
    }



}

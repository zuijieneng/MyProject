package com.edu.zut.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zut.entity.Employee;
import com.edu.zut.entity.common.R;
import com.edu.zut.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录，接收的json对象
     * 1.MD5进行明文加密
     * 2.根据用户名检索数据库
     * @param employee
     * @param req
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest req){
        //明文加密
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        //条件，根据用户名检索数据库
        LambdaQueryWrapper<Employee> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername,employee.getUsername());
        Employee one = employeeService.getOne(wrapper);
        //登录失败
        if(one==null) return R.error("用户名不存在，登录失败！");
        if(!one.getPassword().equals(password)) return R.error("密码不正确，登录失败！");
        if(one.getStatus()==0) return R.error("账号已经被禁用，登录失败！");
        //登录成功
        req.getSession().setAttribute("employee",one.getId());
        return R.success(one); //前端接收到后会封装在Json里，存储在游览器
    }

    /**
     * 员工登出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功！");
    }

    /**
     * 添加员工信息
     * @param employee
     * @param req
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Employee employee,HttpServletRequest req){
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes())); //设置初始密码
        employee.setCreateTime(LocalDateTime.now());  //设置时间为系统的当前时间
        employee.setUpdateTime(LocalDateTime.now());  //设置时间为系统的当前时间
        employee.setCreateUser((Long)req.getSession().getAttribute("employee"));
        employee.setUpdateUser((Long)req.getSession().getAttribute("employee"));
        employeeService.save(employee);
        return R.success("添加员工成功！");
    }
    //GET	http://localhost/employee/page?page=1&pageSize=10&name=1234356

    /**
     * 分页+模糊查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> listByPage(int page,Integer pageSize,String name){
        log.info("分页查询接收的数据page={},pageSize={},name={}",page,pageSize,name);
        //分页构造器
        Page<Employee> pageInfo=new Page<>(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Employee> wrapper=new LambdaQueryWrapper<>();
        wrapper.like(name!=null,Employee::getName,name).orderByDesc(Employee::getUpdateTime);
        //分页+条件一起检索
        employeeService.page(pageInfo,wrapper);
       return R.success(pageInfo);
    }

    /**
     * 功能：1.禁用启用员工修改状态
     * 2.修改员工信息
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> updateEmployeeStatus(@RequestBody Employee employee, HttpServletRequest req){
        //修改之前要修改员工的updateTime和updateUser
        Long emId = (Long) req.getSession().getAttribute("employee");
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(emId);
        employeeService.updateById(employee); //前端js会丢失数据精度，因此String(id)传给了服务端
        return R.success("更新成功！");
    }

    /**
     * 前端修改的时候发起一个axios请求
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getOneById(@PathVariable("id") String id){
        Employee employee = employeeService.getById(id);
        return R.success(employee);
    }

}

package com.edu.zut.service.impl;

import com.edu.zut.entity.Department;
import com.edu.zut.entity.Role;
import com.edu.zut.entity.User;
import com.edu.zut.entity.UserRole;
import com.edu.zut.exception.LoginException;
import com.edu.zut.exception.UpdatePwdException;
import com.edu.zut.mapper.DepartmentMapper;
import com.edu.zut.mapper.RoleMapper;
import com.edu.zut.mapper.UserMapper;
import com.edu.zut.mapper.UserRoleMapper;
import com.edu.zut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Override
    public int deleteByPrimaryKey(String userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    //这里没有用户赋予角色权限的问题，因此，如果后续普通用户没有权限，要考虑到role为空的情况
    @Override
    public int insert(User record) {
        //实现自增主键的效果
        String id=userMapper.getTheLastId();
        int index=id.indexOf("_");
        String userId="user_00"+(Integer.parseInt(id.substring(index + 1))+1);
        record.setUserId(userId);
        //设置部门
        if(record.getDepartmentId()!=null){
            record.setDepartment(departmentMapper.selectByPrimaryKey(record.getDepartmentId()));
        }
        return userMapper.insert(record);
    }

    @Override
    public User selectByPrimaryKey(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> selectAll() {
        List<User> list=userMapper.selectAll();
        for (User user:list) {
            //设置部门
            Department department = departmentMapper.selectByPrimaryKey(user.getDepartmentId());
            user.setDepartment(department);
            System.out.println(user.getRoleId());
            //设置角色以及角色的权限
            UserRole userRole=userRoleMapper.selectByUserId(user.getUserId());
            if(userRole!=null){
                Role role = roleMapper.selectByPrimaryKey(userRole.getRoleId());
                user.setRole(role);
            }
        }
        return list;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        if(record.getUserPhoto()==null||record.getUserPhoto().equals("")){
            User user = userMapper.selectByPrimaryKey(record.getUserId());
            record.setUserPhoto(user.getUserPhoto());
        }
        //查看是否存在这条角色
        UserRole isexits = userRoleMapper.selectOne(record.getUserId(), record.getRoleId());
        UserRole isexits2 = userRoleMapper.selectByUserId(record.getUserId());
        UserRole userRole=new UserRole(record.getUserId(),record.getRoleId());
        //如果该条记录存在，就仅仅给该用赋予角色，角色赋予权限即可
        if(isexits!=null){
            Role role = roleMapper.selectByPrimaryKey(record.getRoleId()); //查找角色，角色的selectByPrimaryKey方法会设置权限
            record.setRole(role); //设置角色
        }else if(isexits2!=null&&record.getRoleId()!=null||!record.getRoleId().equals("")){ //多添加一条用户角色
            userRoleMapper.deleteByUid(record.getUserId());
            userRoleMapper.insert(userRole); //添加至关系表
            Role role = roleMapper.selectByPrimaryKey(record.getRoleId()); //查找角色，角色的selectByPrimaryKey方法会设置权限
            record.setRole(role); //设置角色
        }
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User login(String userName, String userPwd) {
        User login = userMapper.login(userName, userPwd);
        if(login==null){
            throw new LoginException("该用户不存在！");
        }
        return login;
    }

    @Override
    public int updatePwd(String userName, String userPwd,String id) {
        if(userName.equals("")||userPwd.equals("")||userName==null||userPwd==null){
            throw new UpdatePwdException("用户名或者密码不能为空！");
        }
        if(id.equals("")){
            throw new LoginException("请登录之后再修改密码！");
        }
        return userMapper.updatePwd(userName,userPwd,id);
    }
}

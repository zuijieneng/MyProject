package com.edu.zut.interceptor;

import com.edu.zut.entity.Operation;
import com.edu.zut.entity.Role;
import com.edu.zut.entity.User;
import com.edu.zut.entity.UserRole;
import com.edu.zut.exception.LoginException;
import com.edu.zut.exception.PermissionExceptoin;
import com.edu.zut.mapper.OperationMapper;
import com.edu.zut.mapper.UserRoleMapper;
import com.edu.zut.service.OperationService;
import com.edu.zut.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleService roleService;
    @Autowired
    OperationMapper operationMapper;
    @Autowired
    OperationService operationService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String servletPath = request.getServletPath(); //拦截的路径
        System.out.println("MyInterceptor拦截的路径:"+servletPath);
        boolean result=false;
        //当前登录的用户信息
        User user=(User) request.getSession().getAttribute("user");
        //首先判断是否登录，能否访问
        if(user==null){
            throw new LoginException("请登录！");
        }else { //登录过再判断用户角色以及权限
            UserRole userRole=userRoleMapper.selectByUserId(user.getUserId());
            //如果该用户有角色
                //查找角色以及权限，并赋予
                Role role = roleService.selectByPrimaryKey(userRole.getRoleId());
                user.setRole(role);
                //该用户的所有权限
                List<Operation> operation = role.getOperation();
                if(operation!=null){ //如果该用户有角色
                    for (Operation ope:operation) {
                        String token=ope.getUrlPath()+".action"; //拥有的权限
                        if(servletPath.equals(token)){ //只要遍历到该权限，就放行
                            result=true;
                            return true;
                        }
                    }
                }else { //如果该用户没有角色
                    throw new PermissionExceptoin("没有权限访问！");
                }
        }
        if(result==false){
            throw new PermissionExceptoin("没有权限访问！");
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("hhhhhhhhhpost");
    }
}

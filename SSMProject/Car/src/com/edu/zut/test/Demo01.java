package com.edu.zut.test;

import com.edu.zut.entity.*;
import com.edu.zut.mapper.*;
import com.edu.zut.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring_config.xml","classpath:springmvc_config.xml","classpath:mybatis_config.xml"})
public class Demo01 {
	
	@Autowired
	CarMapper mapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	CustomerMapper customerMapper;
	@Autowired
	CarMapper carMapper;
	@Autowired
	CarCheckMapper carCheckMapper;
	@Autowired
	CarOutMapper carOutMapper;
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	OperationMapper operationMapper;
	@Autowired
	RoleMapper roleMapper;
	@Autowired
	RoleService roleService;
	@Autowired
	RoleUrlMapper roleUrlMapper;
	@Test
	public void testpageNumber() {
		String id=UUID.randomUUID().toString().replace("-","").substring(0,30);
		System.out.println(id);
	}

	@Test
	public void user(){
		System.out.println(userMapper.login("呵呵","123456").toString());
	}
	@Test
	public void t1(){
		PageHelper.startPage(1,4);
		List<Customer> customers = customerMapper.selectAll();
		PageInfo<Customer> pageInfo=new PageInfo<>(customers);
		System.out.println(pageInfo);
	}
	@Test
	public void t2(){
		String thelastcustomerId=customerMapper.getTheLast().getCustomerId();
		Integer index=thelastcustomerId.indexOf("_");
		thelastcustomerId=thelastcustomerId.substring(index+1);
		Integer id=Integer.valueOf(thelastcustomerId);
		String customerId="customer_"+(id+1);
		System.out.println(customerId);
	}
	@Test
	public void t3(){
		System.out.println(carOutMapper.getcarOutIdByCarId("car_0012"));
	}
	@Test
	public void t4(){
		String departId=departmentMapper.getTheLastId();
		Integer index=departId.indexOf("_");
		Integer id=Integer.valueOf(departId.substring(index+1)); //_后的数字
		String deId="customer_00"+(id+1); //变成customer_(id+1)
		System.out.println(deId);
	}
	@Test
	public void t5()
	{
		System.out.println(departmentMapper.updateByPrimaryKey(new Department("department_001","客户部","客户维护和客户招揽2")));
	}
	@Test
	public void t8()
	{
//		User user=new User("user_0022","格格","男","123","123456","学无止境.jg","department_003");
//		user.setRoleId("role_01");
		Role role = roleService.selectByPrimaryKey("role_01");
		System.out.println("role----"+role);
		List<Operation> operation = role.getOperation();
		System.out.println(operation);
	}
	@Test
	public void t6(){
		List<String> list = roleUrlMapper.geturlIdByRoleId("role_01");
		System.out.println(list);
	}
	@Test
	public void t7(){
		int index=roleMapper.getTheLastId().indexOf("_");
		String id=roleMapper.getTheLastId().substring(index+1);
		String roleId="role_0"+(Integer.parseInt(id)+1);
		System.out.println(roleId);
	}

}

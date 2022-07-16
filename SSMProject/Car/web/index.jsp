<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>汽车出租系统</title>
<link rel="stylesheet" href="<c:url value='/zTree/css/zTreeStyle/zTreeStyle.css '/>" type="text/css">
<link rel="stylesheet" type="text/css" href="<c:url value='/easyui/themes/default/easyui.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/wu.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/icon.css '/>" />
<script type="text/javascript" src="<c:url value='/js/jquery-1.8.0.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/easyui/locale/easyui-lang-zh_CN.js'/>"></script>
<script type="text/javascript" src="<c:url value='/zTree/js/jquery.ztree.core.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/md5.min.js'/>"></script>

</head>
<body class="easyui-layout">
	<!-- begin of header -->
	<div class="wu-header" data-options="region:'north',border:false,split:true">
    	<div class="wu-header-left">
        	<h1>汽车出租系统</h1>
        </div>
        <div class="wu-header-right">
        	<p><strong class="easyui-tooltip" title="2条未读消息"></strong>${sessionScope.user.userName}，欢迎您！</p>
            <p><a href="#">网站首页</a>|<a href="#">支持论坛</a>|<a href="#">帮助中心</a>|
            <a href="<c:url value='/index.jsp?mainPage=user_update_pwd.jsp&pageName=修改密码'/>">修改密码</a>|
            <a href="<c:url value='/user/logout.action'/>">安全退出</a></p>
        </div>
    </div>
    <!-- end of header -->
    <!-- begin of sidebar -->
	<div class="wu-sidebar" data-options="region:'west',split:true,border:true,title:'导航菜单'"> 
    	<div class="ztree" id="treeMenus" data-options="border:false,fit:true">  </div>
    </div>	
    <!-- end of sidebar -->    

     <!-- 指定欢迎页 -->
    <div class="wu-main" data-options="region:'center',split:true,border:true">
        <div id="wu-tabs" class="easyui-tabs" data-options="border:false,fit:true"> 
            <c:choose>
                 <c:when test="${empty param.mainPage and empty requestScope.mainPage}">
                      <c:url var="mainPage"  value="/welcome.jsp" scope="page"/>
                      <c:url var="titleName"  value="首页" scope="page"/>  
                 </c:when>
                 <c:when test="${not empty param.mainPage}">
                      <c:url var="mainPage"  value="/${param.mainPage}" scope="page"/> 
                      <c:url var="titleName"  value="${param.pageName}" scope="page"/> 
                 </c:when>
                 <c:otherwise>
                      <c:url var="mainPage"  value="/${requestScope.mainPage}" scope="page"/> 
                      <c:url var="titleName"  value="${requestScope.pageName}" scope="page"/> 
                 </c:otherwise>
            </c:choose>
            <div title="${titleName}" data-options="href:'${mainPage}',closable:false,iconCls:'icon-tip',cls:'pd3'"></div>
        </div>
    </div>
    <!-- end of main --> 
    <!-- begin of footer -->
	<div class="wu-footer" data-options="region:'south',border:true,split:true">
    	&copy; 2020  All Rights Reserved
    </div>
    
    <SCRIPT type="text/javascript">
		
		function zTreeOnClick(event, treeId, treeNode) {
	    	if(treeNode.isParent!=undefined&&treeNode.isParent!=true){
	    		addTab(treeNode.name,treeNode.href,treeNode.tabIcon,true);
	    	}
		};
		var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: zTreeOnClick
			}
		};
		var zNodes =[
			{ id:1, pId:0, name:"汽车租赁系统", open:true,isParent:true,icon:"<c:url value='/css/icons/user_add.png'/>"},
			{ id:2, pId:1, name:"客户管理",isParent:false,href:"<c:url value='/customer_manager.jsp'/>",icon:"<c:url value='/css/icons/add.png'/>",tabIcon:'icon-add'},
			{ id:3, pId:1, name:"车辆管理",isParent:false,href:"<c:url value='/car_manager.jsp'/>",icon:"<c:url value='/css/icons/car.png'/>"},
			{ id:4, pId:0, name:"业务管理",open:true,isParent:true,icon:"<c:url value='/css/icons/system.gif'/>"},
			{ id:17, pId:0, name:"系统管理",open:true,isParent:true,icon:"<c:url value='/css/icons/folder.png'/>"},
			{ id:5, pId:4, name:"汽车业务管理",isParent:false,href:"<c:url value='/car_service.jsp'/>",icon:"<c:url value='/css/icons/car_start.png'/>"},
			{ id:6, pId:4, name:"汽车出租管理",isParent:false,href:"<c:url value='/car_out_manager.jsp'/>",icon:"<c:url value='/css/icons/car_start.png'/>"},
			{ id:7, pId:4, name:"汽车入库管理",isParent:false,href:"<c:url value='/car_in_manager.jsp'/>",icon:"<c:url value='/css/icons/car_stop.png'/>"},
			{ id:8, pId:4, name:"检查单管理",isParent:false,href:"<c:url value='/car_check_manager.jsp'/>",icon:"<c:url value='/css/icons/table.png'/>"},
			{ id:9, pId:17, name:"部门管理",isParent:false,href:"<c:url value='/department_manager.jsp'/>",icon:"<c:url value='/css/icons/neighbourhood.png'/>"},
			{ id:10, pId:17, name:"用户管理",isParent:false,href:"<c:url value='/user_manager.jsp'/>",icon:"<c:url value='/css/icons/user.png'/>"},
			{ id:11, pId:17, name:"角色管理",isParent:false,href:"<c:url value='/role_manager.jsp'/>",icon:"<c:url value='/css/icons/group.png'/>"},
			{ id:11, pId:17, name:"操作管理",isParent:false,href:"<c:url value='/url_manager.jsp'/>",icon:"<c:url value='/css/icons/advancedsettings.png'/>"},
			{ id:14, pId:0, name:"数据统计",open:true,isParent:true,icon:"<c:url value='/css/icons/database.png'/>"},
			{ id:15, pId:14, name:"年销售额",isParent:false,href:'customList.jsp',icon:"<c:url value='/css/icons/money.png'/>"},
			{ id:16, pId:14, name:"年客户增长",isParent:false,href:'customList.jsp',icon:"<c:url value='/css/icons/redo.png'/>"}
		];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeMenus"), setting, zNodes);
		});
		/**
		* Name 选项卡初始化
		*/
		$('#wu-tabs').tabs({
			tools:[{
				iconCls:'icon-reload',
				border:false,
				handler:function(){
					$('#wu-datagrid').datagrid('reload');
				}
			}]
		});
		
		/**
		* Name 添加菜单选项
		* Param title 名称
		* Param href 链接
		* Param iconCls 图标样式
		* Param iframe 链接跳转方式（true为iframe，false为href）
		*/	
		function addTab(title, href, iconCls, iframe){
			var tabPanel = $('#wu-tabs');
			if(!tabPanel.tabs('exists',title)){
				var content = '<iframe scrolling="auto" frameborder="0"  src="'+ href +'" style="width:100%;height:100%;"></iframe>';
				if(iframe){
					tabPanel.tabs('add',{
						title:title,
						content:content,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
				else{
					tabPanel.tabs('add',{
						title:title,
						href:href,
						iconCls:iconCls,
						fit:true,
						cls:'pd3',
						closable:true
					});
				}
			}
			else
			{
				tabPanel.tabs('select',title);
			}
		}
		
		/**
		* Name 移除菜单选项
		*/
		function removeTab(){
			var tabPanel = $('#wu-tabs');
			var tab = tabPanel.tabs('getSelected');
			if (tab){
				var index = tabPanel.tabs('getTabIndex', tab);
				tabPanel.tabs('close', index);
			}
		}
		
	</SCRIPT>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.8.0.min.js'/>"></script>
<title>欢迎页</title>

<style type="text/css">
 
</style>
</head>
<body>
       <!-- 注意不要在head中设置样式：此页面要被index.jsp加载  加载时只加载body中的代码
                                                              在index.jsp中引入了main.css文件  样式都写在main.css中即可
       -->
       <c:if test="${empty sessionScope.user}">
           <c:set scope="request" var="message" value="必须登录才能访问！"/>
       </c:if>
       <c:if test="${not empty  requestScope.permissionerror}">
	         <h3 style="color:red;">${requestScope.permissionerror}</h3>
	   </c:if>
	  <div id="divWelcome" style="font-size: 40px">欢迎用户:${sessionScope.user.userName}进入汽车租赁系统！</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<c:url value='/js/jquery-1.8.0.min.js'/>"></script>
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/main.css'/>"/>
	</head>
	<body>
	     <h1>车辆信息详情页面</h1>
		     <table>
		             <tr>
		                <th>车辆编号:</th>
		                <td>${requestScope.car.carId}</td>
		             </tr>
		             <tr>
		                <th>车辆品牌:</th>
		                <td>${car.carLogo}</td>
		             </tr>
		             <tr>   
		                <th>车辆牌照:</th>
		                <td>${car.carNumber}</td>
		             </tr>
		             <tr>
		                <th>车辆座位:</th>
		                <td>${car.carZuo}</td>
		              </tr>
		              <tr>     
		                <th>购车时间:</th>
		                <td><fmt:formatDate value="${car.carTime}" pattern="yyyy-MM-dd"/></td>
		             </tr>
		             <tr>
		                <th>车辆照片:</th>
		                <td><img src="<c:url value='/file/${car.carPhoto}'/>"/></td>
		             </tr>
		             <tr>   
		                <th>车辆颜色:</th>
		                <td>${car.carColor}</td>
		             </tr>
		             <tr>
		                <th>单日价格:</th>
		                <td>${car.carPrice}</td>
		             </tr>
		             <tr>
		                <th>车辆状态:</th>
		                <td>
		                <c:choose>
		                     <c:when test="${car.carStatus eq 1}">
		                                                                                未租
		                     </c:when>
		                     <c:when test="${car.carStatus eq 2}">
		                                                                                出租
		                     </c:when>
		                     <c:when test="${car.carStatus eq 3}">
		                                                                               维修
		                     </c:when>
		                </c:choose>
		                </td>
		             </tr>
		     </table>
		     <div class="div_a"><a href="javascript:history.go(-1);">后退一步</a></div>
		     
	</body>	
</html>
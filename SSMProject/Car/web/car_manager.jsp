<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<c:url value='/js/jquery-1.8.0.min.js'/>"></script>
	<link type="text/css" rel="stylesheet" href="<c:url value='/css/main.css'/>"/>
</head>
	<body>
	     <c:if test="${empty requestScope.pageInfo }">
	         <jsp:forward page="/car/getAllFenye.action?pageNumber=1"/>
	     </c:if>
	     <c:if test="${not empty  requestScope.carmessage}">
	         <h3 style="color:red;">${requestScope.carmessage}</h3>
	     </c:if>
	     <table class="tab_list">
	          <tr>
	               <th>车辆牌照</th>
	               <th>车辆品牌</th>
	               <th>车辆座位</th>
				   <th>单日价格</th>
	               <th>车辆状态</th>
	               <th>车辆操作</th>
	          </tr>
	          <c:forEach items="${requestScope.pageInfo.list}" var="car">
	                <tr>
	                     <td>${car.carNumber}</td>
	                     <td>${car.carLogo}</td>
	                     <td>${car.carZuo}</td>
	                     <td>${car.carPrice}</td>
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
	                     <td>
	                         <a href="<c:url value='/car/getOne/${car.carId}/detail.action'/>">详情</a> | 
	                         <a href="<c:url value='/car/deleteOne/${car.carId}.action?pageNumber=${requestScope.pageInfo.pageNum}'/>">删除</a> |
	                         <a href="/car_update.jsp?id=${car.carId}&carLogo=${car.carLogo}&carNumber=${car.carNumber}&carZuo=${car.carZuo}&pageNumber=${requestScope.pageInfo.pageNum}&carTime=${car.carTime}&carStatus=${car.carStatus}&carPrice=${car.carPrice}&carColor=${car.carColor}">修改</a>
	                     </td>
	                </tr>
	          </c:forEach>	          
	          <tr id="tr_fenye">
	              <th colspan="5">
	                    <c:if test="${pageInfo.pageNum gt 1}">
	                         <a  href="<c:url value='/car/getAllFenye.action?pageNumber=${pageInfo.pageNum-1}'/>">上一页</a>
	                         &nbsp; &nbsp;
	                    </c:if>
	                    <c:forEach begin="1" end="${pageInfo.pages}" var="n">
	                          <c:choose>
	                              <c:when test="${n != pageInfo.pageNum}">
	                                    <a  href="<c:url value='/car/getAllFenye.action?pageNumber=${n}'/>">${n}</a>
	                                     &nbsp; &nbsp;
	                              </c:when>
	                              <c:otherwise>
	                                    [${n}]
	                                    &nbsp; &nbsp;
	                              </c:otherwise>
	                          </c:choose>
	                    </c:forEach>
	                    <c:if test="${pageInfo.pageNum lt pageInfo.pages}">
	                         <a  href="<c:url value='/car/getAllFenye.action?pageNumber=${pageInfo.pageNum+1}'/>">下一页</a>
	                    </c:if>
	              </th>
	          </tr>
	     </table>
	     <div class="div_a">
		     <a href="javascript:addOne();">添加车辆</a>
	     </div>
	     <form action="<c:url value='/car/addOne.action'/>" method="post" enctype="multipart/form-data"> 
		     <table id="tab_add">
		             <tr>
		                <th>车辆品牌:</th>
		                <td><input type="text" name="carLogo" placeHolder="请输入品牌" value="宝马"/></td>
		                <th>车辆牌照:</th>
		                <td><input type="text" name="carNumber" placeHolder="请输入牌照" value="豫A88888"/></td>
		             </tr>
		             <tr>
		                <th>购车时间:</th>
		                <td><input type="date" name="carTime" placeHolder="请输入购车时间"/></td>
		                <th>座位个数:</th>
		                <td><input type="text" name="carZuo" placeHolder="请输入座位" value="2"/></td>
		             </tr>
		             <tr>
		                <th>车辆颜色:</th>
		                <td><input type="text" name="carColor" placeHolder="请输入颜色" value="红"/></td>
		                <th>单日价格:</th>
		                <td><input type="text" name="carPrice" placeHolder="请输入单日价格" value="2222222"/></td>
		             </tr>
		             <tr>
		                <th>车辆照片:</th>
		                <td colspan="3"><input type="file" name="photo"/></td>
		             </tr>
                     <tr>
                         <th>车辆状态:</th>
                         <td colspan="3">未出租<input type="text" name="carStatus" value="1" hidden="hidden"/></td>
                     </tr>
		             <tr>
		                <th colspan="4">
		                     <input  type="reset" value="重填"/>
		                     <input  type="submit" value="添加"/>
		                </th>
		             </tr>
		             
		     </table>
	     </form>
	</body>	
	<script type="text/javascript">
	    $(function(){
	    	$("#tab_add").hide();
	    });
	    function addOne(){
	    	$("#tab_add").toggle();
	    }
	</script>
</html>
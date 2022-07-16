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
	     <h1>车辆信息修改页面</h1>
	     <form action="<c:url value='/car/updateOne.action'/>" method="post" enctype="multipart/form-data">
		     <input type="hidden" name="pageNumber" value="${param.pageNumber}"/>
		     <table>
				 	<tr>
						<th>车辆ID:</th>
						<td>
							<input  name="carId" value="${param.id}" readonly="readonly"/>
						</td>
					</tr>
		             <tr>
		                <th>车辆品牌:</th>
		                <td><input type="text" name="carLogo" value="${param.carLogo}"/></td>
		             </tr>
		             <tr>   
		                <th>车辆牌照:</th>
		                <td><input type="text" name="carNumber" value="${param.carNumber}"/></td>
		             </tr>
		             <tr>
		                <th>车辆座位:</th>
						 <td>
							 <input type="text" name="carZuo" value="${param.carZuo}"/>
						 </td>
		              </tr>
		              <tr>     
		                <th>购车时间:</th>
		                <td><input type="date" name="carTime"/></td>
		             </tr>
		             <tr>
		                <th>车辆照片:</th>
		                <td><input type="file" name="photo"/></td>
		             </tr>
		             <tr>   
		                <th>车辆状态:</th>
		                <td><input type="text" name="carStatus" value="${param.carStatus}" readonly="readonly"/></td>
		             </tr>
		             <tr>   
		                <th>单日价格:</th>
		                <td><input type="text" name="carPrice" value="${param.carPrice}"/></td>
		             </tr>
		             <tr>
		                <th>车辆颜色:</th>
		                <td colspan="3"><input type="text" name="carColor" value="${param.carColor}"/></td>
		             </tr>
		             <tr>
		                <th colspan="4">
		                     <input  type="reset" value="重填"/>  &nbsp; &nbsp; &nbsp; &nbsp;
		                     <input  type="submit" value="修改"/>
		                </th>
		             </tr>
		     </table>
	     </form>
	     <div class="div_a"><a href="javascript:history.go(-1);">后退一步</a></div>
	</body>	
</html>
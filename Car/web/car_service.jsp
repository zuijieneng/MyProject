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
    <jsp:forward page="/car/getAll.action?pageNumber=1"/>
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
                        车检
                    </c:when>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${car.carStatus eq 1}">
                        <a href="/car_check_insert.jsp?carId=${car.carId}&userId=${sessionScope.user.userId}">车检</a>
                    </c:when>
                    <c:when test="${car.carStatus eq 2}">
                        <a href="/car_check_insert.jsp?carId=${car.carId}&userId=${sessionScope.user.userId}">车检</a>|
                        <a href="/car_In_insert.jsp?userId=${sessionScope.user.userId}&carId=${car.carId}">入库之前最好车检</a>
                    </c:when>
                    <c:when test="${car.carStatus eq 3}">
                        <a href="/car_out_insert.jsp?carId=${car.carId}&userId=${sessionScope.user.userId}">检完出租</a>
                    </c:when>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    <tr id="tr_fenye">
        <th colspan="5">
            <c:if test="${pageInfo.pageNum gt 1}">
                <a  href="<c:url value='/car/getAll.action?pageNumber=${pageInfo.pageNum-1}'/>">上一页</a>
                &nbsp; &nbsp;
            </c:if>
            <c:forEach begin="1" end="${pageInfo.pages}" var="n">
                <c:choose>
                    <c:when test="${n != pageInfo.pageNum}">
                        <a  href="<c:url value='/car/getAll.action?pageNumber=${n}'/>">${n}</a>
                        &nbsp; &nbsp;
                    </c:when>
                    <c:otherwise>
                        [${n}]
                        &nbsp; &nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${pageInfo.pageNum lt pageInfo.pages}">
                <a  href="<c:url value='/car/getAll.action?pageNumber=${pageInfo.pageNum+1}'/>">下一页</a>
            </c:if>
        </th>
    </tr>
</table>
<div class="div_a"><a href="javascript:history.go(-1);">后退一步</a></div>
</body>
</html>
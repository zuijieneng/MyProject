<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<c:url value='/js/jquery-1.8.0.min.js'/>"></script>
    <style type="text/css">
        table{
            width:90%;
            margin: 50px 5%;
            border: 1px solid;
            border-collapse: collapse;
        }
        .tab_list td,th{
            border:1px solid;
            max-width:130px;text-overflow: ellipsis;overflow: hidden;
            width: 130px;
        }
    </style>
</head>
<body>
<c:if test="${empty requestScope.pageInfo }">
    <jsp:forward page="/carin/getAll.action"/>
</c:if>
<table class="tab_list">
    <tr>
        <th>入库订单id号</th>
        <th>出库订单id号</th>
        <th>车检订单id号</th>
        <th>入库时间</th>
        <th>入库油量</th>
        <th>入库总租金</th>
        <th>用车反馈</th>
        <th>用户id号</th>
        <th>汽车id号</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.pageInfo.list}" var="carin">
        <tr>
            <td>${carin.carInId}</td>
            <td>${carin.carOutId}</td>
            <td>${carin.carCheckId}</td>
            <td><fmt:formatDate value="${carin.carInTime}" pattern="yyyy-MM-dd"/></td>
            <td>${carin.carInOil}</td>
            <td>${carin.carInMoney}</td>
            <td>${carin.carInFeedback}</td>
            <td>${carin.userId}</td>
            <td>${carin.carId}</td>
            <td>
                <c:choose>
                    <c:when test="${empty carin.carCheckId}">
                        <a href="/car_check_insert.jsp?carId=${carin.carId}&userId=${sessionScope.user.userId}">车检</a>
                    </c:when>
                    <c:when test="${not empty carin.carCheckId}">
                        <a href="/car_out_insert.jsp?carId=${carin.carId}&userId=${sessionScope.user.userId}&carCheckId=${carin.carCheckId}">出库</a>
                    </c:when>
                </c:choose>
        </tr>
    </c:forEach>
    <tr id="tr_fenye">
        <th colspan="11">
            <c:if test="${pageInfo.pageNum gt 1}">
                <a  href="<c:url value='/carin/getAll.action?pageNumber=${pageInfo.pageNum-1}'/>">上一页</a>
                &nbsp; &nbsp;
            </c:if>
            <c:forEach begin="1" end="${pageInfo.pages}" var="n">
                <c:choose>
                    <c:when test="${n != pageInfo.pageNum}">
                        <a  href="<c:url value='/carin/getAll.action?pageNumber=${n}'/>">${n}</a>
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
<div class="div_a"><a href="javascript:history.go(-1);">后退一步</a></div>
</body>
</html>
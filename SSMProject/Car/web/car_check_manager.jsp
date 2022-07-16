<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
    <jsp:forward page="/check/list.action"/>
</c:if>
<c:if test="${not empty requestScope.checkmessage}">
    ${requestScope.checkmessage}
</c:if>
<table class="tab_list">
    <tr>
        <th>车检订单号</th>
        <th>被检汽车id号</th>
        <th>车检时间</th>
        <th>服务反馈</th>
        <th>管理员的id</th>
        <th>检查后的状态码</th>
    </tr>
    <c:forEach items="${requestScope.pageInfo.list}" var="carcheck">
        <tr>
            <td>${carcheck.carCheckId}</td>
            <td>${carcheck.carId}</td>
            <td><fmt:formatDate value="${carcheck.carCheckTime}" pattern="yyyy-MM-dd"/></td>
            <td>${carcheck.carDescribe}</td>
            <td>${carcheck.userId}</td>
            <td>${carcheck.carCheckStatus}</td>
            <td>
                <a href="/check/delete.action?carCheckId=${carcheck.carCheckId}">删除订单</a>
                <a href="/car_check_insert.jsp">添加车检</a>
            </td>
        </tr>
    </c:forEach>
    <tr id="tr_fenye">
        <th colspan="11">
            <c:if test="${pageInfo.pageNum gt 1}">
                <a  href="<c:url value='/check/list.action?pageNumber=${pageInfo.pageNum-1}'/>">上一页</a>
                &nbsp; &nbsp;
            </c:if>
            <c:forEach begin="1" end="${pageInfo.pages}" var="n">
                <c:choose>
                    <c:when test="${n != pageInfo.pageNum}">
                        <a  href="<c:url value='/check/list.action?pageNumber=${n}'/>">${n}</a>
                        &nbsp; &nbsp;
                    </c:when>
                    <c:otherwise>
                        [${n}]
                        &nbsp; &nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${pageInfo.pageNum lt pageInfo.pages}">
                <a  href="<c:url value='/check/list.action?pageNumber=${pageInfo.pageNum+1}'/>">下一页</a>
            </c:if>
        </th>
    </tr>
</table>
<div class="div_a"><a href="javascript:history.go(-1);">后退一步</a></div>

</body>
</html>

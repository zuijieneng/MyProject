<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
        <jsp:forward page="/carout/getAll.action"/>
    </c:if>
    <table class="tab_list">
        <tr>
            <th>出租订单号</th>
            <th>车辆id号</th>
            <th>客户id号</th>
            <th>车检订单号</th>
            <th>出租时间</th>
            <th>出租结束时间</th>
            <th>天租金</th>
            <th>出租押金</th>
            <th>出租油价/天</th>
            <th>负责人id号</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${requestScope.pageInfo.list}" var="carout">
            <tr>
                <td>${carout.carOutId}</td>
                <td>${carout.carId}</td>
                <td>${carout.customerId}</td>
                <td>${carout.carCheckId}</td>
                <td><fmt:formatDate value="${carout.carOutStartTime}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${carout.carOutEndTime}" pattern="yyyy-MM-dd"/></td>
                <td>${carout.carOutDayPrice}</td>
                <td>${carout.carOutDeposit}</td>
                <td>${carout.carOutOil}</td>
                <td>${carout.userId}</td>
                <td>
                    <c:choose>
                        <c:when test="${empty carout.carCheckId}">
                            <a href="/car_check_insert.jsp?carId=${carout.carId}&userId=${sessionScope.user.userId}">车检</a>
                        </c:when>
                        <c:when test="${not empty carout.carCheckId}">
                            <a href="/car_In_insert.jsp?carId=${carout.carId}&carOutId=${carout.carOutId}&carCheckId=${carout.carCheckId}&userId=${sessionScope.user.userId}">入库</a>
                        </c:when>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        <tr id="tr_fenye">
            <th colspan="11">
                <c:if test="${pageInfo.pageNum gt 1}">
                    <a  href="<c:url value='/carout/getAll.action?pageNumber=${pageInfo.pageNum-1}'/>">上一页</a>
                    &nbsp; &nbsp;
                </c:if>
                <c:forEach begin="1" end="${pageInfo.pages}" var="n">
                    <c:choose>
                        <c:when test="${n != pageInfo.pageNum}">
                            <a  href="<c:url value='/carout/getAll.action?pageNumber=${n}'/>">${n}</a>
                            &nbsp; &nbsp;
                        </c:when>
                        <c:otherwise>
                            [${n}]
                            &nbsp; &nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pageInfo.pageNum lt pageInfo.pages}">
                    <a  href="<c:url value='/carout/getAll.action?pageNumber=${pageInfo.pageNum+1}'/>">下一页</a>
                </c:if>
            </th>
        </tr>
    </table>

</body>
</html>
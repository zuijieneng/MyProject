<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
            text-align: center;
            border:1px solid;
            max-width:130px;text-overflow: ellipsis;overflow: hidden;
            width: 130px;
        }
    </style>
</head>
<body>
<c:if test="${empty requestScope.pageInfo }">
    <jsp:forward page="/role/list.action?pageNumber=1"></jsp:forward>
</c:if>
<table class="tab_list">
    <tr>
        <th>角色id号</th>
        <th>角色名字</th>
        <th>角色描述</th>
        <th>操作范围</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.pageInfo.list}" var="role" varStatus="vs">
        <tr>
            <td>${role.roleId}</td>
            <td>${role.roleName}</td>
            <td>${role.roleDescribe}</td>
            <td>
                <c:forEach items="${role.operation}" var="ope" varStatus="vs">
                    ${ope.urlName}|
                </c:forEach>
            </td>
            <td>
                <a href="/role_option.jsp?option=update&roleId=${role.roleId}&roleName=${role.roleName}&roleDescribe=${role.roleDescribe}">修改</a>|
                <a href="/role/delete.action?roleId=${role.roleId}">删除</a>|
                <a href="/role_option.jsp?option=insert">增加</a>
            </td>
        </tr>
    </c:forEach>
    <tr id="tr_fenye">
        <th colspan="11">
            <c:if test="${pageInfo.pageNum gt 1}">
                <a  href="<c:url value='/role/list.action?pageNumber=${pageInfo.pageNum-1}'/>">上一页</a>
                &nbsp; &nbsp;
            </c:if>
            <c:forEach begin="1" end="${pageInfo.pages}" var="n">
                <c:choose>
                    <c:when test="${n != pageInfo.pageNum}">
                        <a  href="<c:url value='/role/list.action?pageNumber=${n}'/>">${n}</a>
                        &nbsp; &nbsp;
                    </c:when>
                    <c:otherwise>
                        [${n}]
                        &nbsp; &nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${pageInfo.pageNum lt pageInfo.pages}">
                <a  href="<c:url value='/role/list.action?pageNumber=${pageInfo.pageNum+1}'/>">下一页</a>
            </c:if>
        </th>
    </tr>
</table>
<div class="div_a"><a href="javascript:history.go(-1);">后退一步</a></div>
</body>
</html>
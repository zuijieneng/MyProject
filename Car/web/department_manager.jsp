<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="<c:url value='/js/jquery-1.8.0.min.js'/>"></script>
    <style type="text/css">
        table{
            width:70%;
            margin: 50px 15%;
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
        <jsp:forward page="/department/list.action?pageNumber=1"></jsp:forward>
    </c:if>
    <table class="tab_list">
        <tr>
            <th>部门id号</th>
            <th>部门名称</th>
            <th>部门描述</th>
            <th>管理操作</th>
        </tr>
        <c:if test="${empty requestScope.pageInfo.list}">
            <tr>
                <td>...</td>
                <td>...</td>
                <td>...</td>
                <td>
                    <a href="/department_option.jsp?option=update">修改</a>|
                    <a href="/department_option.jsp?option=insert">增加</a>
                </td>
            </tr>
        </c:if>
        <c:forEach items="${requestScope.pageInfo.list}" var="depart">
            <tr>
                <td>${depart.departmentId}</td>
                <td>${depart.departmentName}</td>
                <td>${depart.departmentDescribe}</td>
                <td>
                    <a href="/department_option.jsp?option=update&name=${depart.departmentName}&describe=${depart.departmentDescribe}&id=${depart.departmentId}">修改</a>|
                    <a href="/department/delete.action?id=${depart.departmentId}&current=${requestScope.pageInfo.pageNum}">删除</a>|
                    <a href="/department_option.jsp?option=insert">增加</a>
                </td>
            </tr>
        </c:forEach>
        <tr id="tr_fenye">
            <th colspan="11">
                <c:if test="${pageInfo.pageNum gt 1}">
                    <a  href="<c:url value='/department/list.action?pageNumber=${pageInfo.pageNum-1}'/>">上一页</a>
                    &nbsp; &nbsp;
                </c:if>
                <c:forEach begin="1" end="${pageInfo.pages}" var="n">
                    <c:choose>
                        <c:when test="${n != pageInfo.pageNum}">
                            <a  href="<c:url value='/department/list.action?pageNumber=${n}'/>">${n}</a>
                            &nbsp; &nbsp;
                        </c:when>
                        <c:otherwise>
                            [${n}]
                            &nbsp; &nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pageInfo.pageNum lt pageInfo.pages}">
                    <a  href="<c:url value='/department/list.action?pageNumber=${pageInfo.pageNum+1}'/>">下一页</a>
                </c:if>
            </th>
        </tr>
    </table>
    <div class="div_a"><a href="javascript:history.go(-1);">后退一步</a></div>
</body>
</html>
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
            width:30%;
            margin: 100px 30%;
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
    <c:if test="${not empty requestScope.departmessage}">
        ${requestScope.departmessage}
    </c:if>
    <c:choose>
        <c:when test="${param.option eq 'insert'}">
            <form action="/department/insert.action" method="post">
                <table class="tab_list">
                    <tr>
                        <th>部门id号</th>
                        <td><input type="text" name="departmentId" hidden="hidden"/></td>
                    </tr>
                    <tr>
                        <th>部门名称</th>
                        <td><input type="text" name="departmentName"/></td>
                    </tr>
                    <tr>
                        <th>部门描述</th>
                        <td><input type="text" name="departmentDescribe"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="提交表单"/>
                        </td>
                    </tr>
                </table>
            </form>
        </c:when>
        <c:when test="${param.option eq 'update'}">
            <form action="/department/update.action" method="get">
                <table class="tab_list">
                    <tr>
                        <th>部门id号</th>
                        <td><input type="text" name="departmentId"  value="${param.id}" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <th>部门名称</th>
                        <td><input type="text" name="departmentName" value="${param.name}"/></td>
                    </tr>
                    <tr>
                        <th>部门描述</th>
                        <td><input type="text" name="departmentDescribe" value="${param.describe}"/></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="提交表单"/>
                        </td>
                    </tr>

                </table>
            </form>
         </c:when>
    </c:choose>
</body>
</html>
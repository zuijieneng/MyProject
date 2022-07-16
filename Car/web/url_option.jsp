<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
<c:if test="${not empty requestScope.operationerror}">
    ${requestScope.operationerror}
</c:if>
<c:choose>
    <c:when test="${param.option eq 'insert'}">
        <form action="/operation/insert.action" method="post" >
            <table class="tab_list">
                <tr>
                    <th>操作id号</th>
                    <td><input type="text" name="urlId" hidden="hidden"/></td>
                </tr>
                <tr>
                    <th>操作路径</th>
                    <td><input type="text" name="urlPath"/></td>
                </tr>
                <tr>
                    <th>操作名称</th>
                    <td><input type="text" name="urlName"/></td>
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
        <form action="/operation/update.action" method="post">
            <table class="tab_list">
                <tr>
                    <th>操作id号</th>
                    <td><input type="text" name="urlId" value="${param.urlId}" hidden="hidden"/></td>
                </tr>
                <tr>
                    <th>操作路径</th>
                    <td><input type="text" name="urlPath" value="${param.urlPath}"/></td>
                </tr>
                <tr>
                    <th>操作名称</th>
                    <td><input type="text" name="urlName" value="${param.urlName}"/></td>
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
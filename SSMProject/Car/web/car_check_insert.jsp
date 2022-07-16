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
<form action="/check/insert.action" method="post">
    <table class="tab_list">
        <tr>
            <th>车检订单号</th>
            <td><input type="text" name="carCheckId" value="" hidden="hidden"/></td>
        </tr>
        <tr>
            <th>车辆id</th>
            <td><input type="text" name="carId" value="${param.carId}" readonly="readonly"/></td>
        </tr>
        <tr>
            <th>检查时间</th>
            <td><input type="date" name="carCheckTime"/></td>
        </tr>
        <tr>
            <th>检查后的描述</th>
            <td><input type="text" name="carDescribe"/></td>
        </tr>
        <tr>
            <th>管理员id</th>
            <td><input type="text" name="userId" value="${param.userId}" readonly="readonly"/></td>
        </tr>
        <tr>
            <th>检查的状态码</th>
            <td><input type="text" name="carCheckStatus"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交车检表"/>
            </td>
        </tr>

    </table>
    <div class="div_a"><a href="javascript:history.go(-1);">后退一步</a></div>
</form>
</body>
</html>
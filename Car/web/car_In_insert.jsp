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
<c:if test="${not empty requestScope.caroutmessage}">
    ${requestScope.caroutmessage}
</c:if>
<form action="/carin/insert.action" method="post">
    <table class="tab_list">
        <tr>
            <th>入库订单id号</th>
            <td><input type="text" name="carInId" value="" hidden="hidden"/></td>
        </tr>
        <tr>
            <th>出库订单id号</th>
            <td><input type="text" name="carOutId" value="${param.carOutId}" readonly="readonly"/></td>
        </tr>
        <tr>
            <th>车检id号</th>
            <td><input type="text" name="carCheckId" value="${param.carCheckId}" readonly="readonly"/></td>
        </tr>
        <tr>
            <th>入库时间</th>
            <td><input type="date" name="carInTime"/></td>
        </tr>
        <tr>
            <th>入库的油量</th>
            <td><input type="text" name="carInOil"/></td>
        </tr>
        <tr>
            <th>总租金</th>
            <td><input type="text" name="carInMoney"/></td>
        </tr>
        <tr>
            <th>反馈</th>
            <td><input type="text" name="carInFeedback"/></td>
        </tr>
        <tr>
            <th>入库管理员id</th>
            <td><input type="text" name="userId" value="${param.userId}"/></td>
        </tr>
        <tr>
            <th>汽车id</th>
            <td><input type="text" name="carId" value="${param.carId}" readonly="readonly"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交入库表"/>
            </td>
        </tr>

    </table>
</form>
<script type="text/javascript">
    $(function () {
        var carId= $("input[name='carId']").val();
        var checkId=$("input[name='carCheckId']").val();
        var outId=$("input[name='carOutId']").val();
        if(checkId==""&&carId!=""){
            $.ajax({
                async:true, //是否异步
                type:"GET", //请求类型
                url:"/check/getCheckId.action", //请求地址
                data: 'carId='+carId,
                cache:false, ///是否缓存
                dataType:"text", //接收的数据类型
                success:function (data) { //处理响应的数据
                $("input[name='carCheckId']").val(data);
                }
            })
         }
        if(outId==""){
            $.ajax({
                async:true, //是否异步
                type:"GET", //请求类型
                url:"/carout/getCheckId.action", //请求地址
                data: 'carId='+carId,
                cache:false, ///是否缓存
                dataType:"text", //接收的数据类型
                success:function (data) { //处理响应的数据
                    $("input[name='carOutId']").val(data);
                }
            })
        }
    })
</script>
</body>
</html>
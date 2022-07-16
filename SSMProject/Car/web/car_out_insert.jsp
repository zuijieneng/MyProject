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
<c:if test="${not empty requestScope.carinmessage}">
    ${requestScope.carinmessage}
</c:if>
<form action="/carout/insert.action" method="post">
    <table class="tab_list">
        <tr>
            <th>出库订单id号</th>
            <td><input type="text" name="carOutId" value="" hidden="hidden"/></td>
        </tr>
        <tr>
            <th>汽车id</th>
            <td><input type="text" name="carId" value="${param.carId}" readonly="readonly"/></td>
        </tr>
        <tr>
            <th>顾客id号</th>
            <td><input type="text" name="customerId"/>
                <select></select>
            </td>
        </tr>
        <tr>
            <th>车检id号</th>
            <td><input type="text" name="carCheckId" value="${param.carCheckId}" readonly="readonly"/></td>
        </tr>
        <tr>
            <th>出租时间</th>
            <td><input type="date" name="carOutStartTime"/></td>
        </tr>
        <tr>
            <th>出租截止时间</th>
            <td><input type="date" name="carOutEndTime"/></td>
        </tr>
        <tr>
            <th>出租每天价格</th>
            <td><input type="text" name="carOutDayPrice"/></td>
        </tr>
        <tr>
            <th>出租押金</th>
            <td><input type="text" name="carOutDeposit"/></td>
        </tr>
        <tr>
            <th>出租油量</th>
            <td><input type="text" name="carOutOil"/></td>
        </tr>
        <tr>
            <th>入库管理员id</th>
            <td><input type="text" name="userId" value="${param.userId}"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="提交入库表"/>
            </td>
        </tr>

    </table>
</form>
<div class="div_a"><a href="javascript:history.go(-1);">后退一步</a></div>
<script type="text/javascript">
        $(function () {
           var carId= $("input[name='carId']").val();
           var checkId=$("input[name='carCheckId']").val();
           var zhi="";
           if(checkId==""&&carId!=""){
               //获取车检id号
               $.ajax({
                   async:true, //是否异步
                   type:"GET", //请求类型
                   url:"/check/getCheckId.action", //请求地址
                   data: 'carId='+carId,
                   cache:false, ///是否缓存
                   dataType:"text", //接收的数据类型
                   success:function (data) { //处理响应的数据
                       $("input[name='carCheckId']").val(data);
                       //获取所有客户id号，并对
                       $.ajax({
                           async:true, //是否异步
                           type:"GET", //请求类型
                           url:"/customer/getId.action", //请求地址
                           cache:false, ///是否缓存
                           dataType:"json", //接收的数据类型
                           success:function (data) { //处理响应的数据
                               $("select").html("");
                               $.each(data,function (i,n) {
                                   $("select").append("<option>"+n+"</option>");
                               });
                               $("option").click(function () {
                                   zhi=$("select option:checked").text();
                                   $("input[name='customerId']").val(zhi);
                               })
                           }
                       });
                   }
               });
           }else {
               //获取所有客户id号，并对
               $.ajax({
                   async:true, //是否异步
                   type:"GET", //请求类型
                   url:"/customer/getId.action", //请求地址
                   cache:false, ///是否缓存
                   dataType:"json", //接收的数据类型
                   success:function (data) { //处理响应的数据
                       $("select").html("");
                       $.each(data,function (i,n) {
                           $("select").append("<option>"+n+"</option>");
                       });
                       $("option").click(function () {
                           zhi=$("select option:checked").text();
                           $("input[name='customerId']").val(zhi);
                       })
                   }
               });
           }

        })

</script>
</body>
</html>
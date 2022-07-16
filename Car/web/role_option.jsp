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
<c:choose>
    <c:when test="${param.option eq 'insert'}">
        <form action="/role/insert.action" method="post" >
            <table class="tab_list">
                <tr>
                    <th>角色id号</th>
                    <td><input type="text" name="roleId" hidden="hidden"/></td>
                </tr>
                <tr>
                    <th>角色名字</th>
                    <td><input type="text" name="roleName"/></td>
                </tr>
                <tr>
                    <th>角色描述</th>
                    <td><input type="text" name="roleDescribe"/></td>
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
        <form action="/role/update.action" method="post">
            <table class="tab_list">
                <tr>
                    <th>角色id号</th>
                    <td><input type="text" name="roleId" hidden="hidden" value="${param.roleId}"/></td>
                </tr>
                <tr>
                    <th>角色名字</th>
                    <td><input type="text" name="roleName" value="${param.roleName}"/></td>
                </tr>
                <tr>
                    <th>角色描述</th>
                    <td><input type="text" name="roleDescribe" value="${param.roleDescribe}"/></td>
                </tr>
                <tr>
                    <th>权限赋予</th>
                    <td><input type="text" name="urlId"/>
                        <select>
                        </select>
                    </td>
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
<script type="text/javascript">
    $(function () {
        var zhi="";
        $.ajax({
            async:true, //是否异步
            type:"GET", //请求类型
            url:"/operation/getNames.action", //请求地址
            cache:false, ///是否缓存
            dataType:"json", //接收的数据类型
            success:function (data) { //处理响应的数据
                $("select").html("");
                $.each(data,function (i,n) {
                    $("select").append("<option>"+n+"</option>");
                });
                $("option").click(function () {
                    zhi=$("select option:checked").text();
                    alert("zhi="+zhi);
                    $.ajax({
                        async:true, //是否异步
                        type:"GET", //请求类型
                        url:"/operation/getId.action", //请求地址
                        cache:false, ///是否缓存
                        data:'urlName='+zhi,
                        dataType:"text", //接收的数据类型
                        success:function (data) { //处理响应的数据
                            $("input[name='urlId']").val(data);
                        }
                    });
                })
            }
        });
    })
</script>
</body>
</html>
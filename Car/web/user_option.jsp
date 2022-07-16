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
        <form action="/user/insert.action" method="post" enctype="multipart/form-data">
            <table class="tab_list">
                <tr>
                    <th>用户id号</th>
                    <td><input type="text" name="userId" hidden="hidden"/></td>
                </tr>
                <tr>
                    <th>用户姓名</th>
                    <td><input type="text" name="userName"/></td>
                </tr>
                <tr>
                    <th>用户性别</th>
                    <td>男:<input type="radio" name="userSex" value="男"/>|女:<input type="radio" name="userSex" value="女"/></td>
                </tr>
                <tr>
                    <th>用户密码</th>
                    <td><input type="text" name="userPwd"/></td>
                </tr>
                <tr>
                    <th>用户电话</th>
                    <td><input type="text" name="userPhone"/></td>
                </tr>
                <tr>
                    <th>用户头像</th>
                    <td><input type="file" name="photo"/></td>
                </tr>
                <tr>
                    <th>所在部门</th>
                    <td>
                        <input type="text" name="departmentId"/>
                        <select></select>
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
    <c:when test="${param.option eq 'update'}">
        <form action="/user/update.action" method="post" enctype="multipart/form-data">
            <table class="tab_list">
                <tr>
                    <th>用户id号</th>
                    <td><input type="text" name="userId" hidden="hidden" value="${param.userId}"/></td>
                </tr>
                <tr>
                    <th>用户姓名</th>
                    <td><input type="text" name="userName" value="${param.userName}"/></td>
                </tr>
                <tr>
                    <th>用户性别</th>
                    <td>男:<input type="radio" name="userSex"/>|女:<input type="radio" name="userSex"/></td>
                </tr>
                <tr>
                    <th>用户密码</th>
                    <td><input type="text" name="userPwd" value="${param.userPwd}"/></td>
                </tr>
                <tr>
                    <th>用户电话</th>
                    <td><input type="text" name="userPhone" value="${param.userPhone}"/></td>
                </tr>
                <tr>
                    <th>用户头像</th>
                    <td><input type="file" name="photo"/></td>
                </tr>
                <tr>
                    <th>所在部门</th>
                    <td>
                        <input type="text" name="departmentId" hidden="hidden"/>
                        <select id="ds">
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>赋予角色</th>
                    <td>
                        <input type="text" name="roleId"/>
                        <select id="rs">
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
            var shi="";
            $.ajax({
                async:true, //是否异步
                type:"GET", //请求类型
                url:"/department/getNames.action", //请求地址
                cache:false, ///是否缓存
                dataType:"json", //接收的数据类型
                success:function (data) { //处理响应的数据
                    $("select[id='ds']").html("");
                    $.each(data,function (i,n) {
                        $("select[id='ds']").append("<option>"+n+"</option>");
                    });
                    $("select[id='ds'] option").click(function () {
                        zhi=$("select[id='ds'] option:checked").text();
                        alert("zhi="+zhi);
                        $.ajax({
                            async:true, //是否异步
                            type:"GET", //请求类型
                            url:"/department/getId.action", //请求地址
                            cache:false, ///是否缓存
                            data:'departmentName='+zhi,
                            dataType:"text", //接收的数据类型
                            success:function (data) { //处理响应的数据
                                $("input[name='departmentId']").val(data);
                            }
                        });
                    });
                }
            });
            //赋予角色的Ajax
            $.ajax({
                async:true, //是否异步
                type:"GET", //请求类型
                url:"/user/getNames.action", //请求地址
                cache:false, ///是否缓存
                dataType:"json", //接收的数据类型
                success:function (data) { //处理响应的数据
                    $("select[id='rs']").html("");
                    $.each(data,function (i,n) {
                        $("select[id='rs']").append("<option>"+n+"</option>");
                    });
                    $("select[id='rs'] option").click(function () {
                        shi=$("select[id='rs'] option:checked").text();
                        alert("shi="+shi);
                        $.ajax({
                            async:true, //是否异步
                            type:"GET", //请求类型
                            url:"/user/getId.action", //请求地址
                            cache:false, ///是否缓存
                            data:'roleName='+shi,
                            dataType:"text", //接收的数据类型
                            success:function (data) { //处理响应的数据
                                $("input[name='roleId']").val(data);
                            }
                        });
                    });
                }
            });
        })
    </script>
</body>
</html>
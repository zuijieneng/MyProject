<%--
  Created by IntelliJ IDEA.
  User: hi
  Date: 2022/6/18
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <link href="<c:url value='/css/login.css'/>" rel="stylesheet" type="text/css" />
</head>
<body>
    <div class="header">
        <div class="switch" id="switch">
            <h1>
                修改密码页面
            </h1>
        </div>
    </div>

    <form action="/user/updatePwd.action" method="post">
        <div id="updatediv" style="width: 200px;height: 200px;margin:10% 35%">
            <div>
                <label for="name">姓名:</label>
                <input class="easyui-textbox" data-options="iconCls:'icon-man'" prompt="UserName" style="width:150px;height: 34px" value="${sessionScope.user.userName}"  name="userName">
            </div>
            <div>
                <label for="name">密码:</label>
                <input class="easyui-passwordbox" prompt="Password" iconWidth="28"
                       style="width:150px;height:34px;padding:10px" name="userPwd" value="${sessionScope.user.userPwd}">
            </div>
            <div>
                <input class="easyui-textbox" type="submit" value="提交修改">
            </div>
        </div>
    </form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>快速登录页面</title>
<script type="text/javascript" src="<c:url value='/js/jquery-1.8.0.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/login.js'/>"></script>
<link href="<c:url value='/css/login.css'/>" rel="stylesheet" type="text/css" />

</head>
<body>
	<h1>
		汽车出租管理系统<sup>2018</sup>
	</h1>
	<div class="login" style="margin-top: 50px;">
		<div class="header">
			<div class="switch" id="switch">
				<span class="switch_btn_focus" id="switch_qlogin">登录页面</span>
				<c:if test="${not empty  requestScope.message or not empty  param.message}">
					   <span class="switch_btn_focus" id="switch_qlogin" style="color: red;">${requestScope.message}</span>
				</c:if>
			</div>
		</div>
		<div class="web_qr_login" id="web_qr_login"
			style="display: block; height: 235px;">
			<!--登录-->
			<div class="web_login" id="web_login">
				<div class="login-box">
					<div class="login_form">
						<form action="<c:url value='/user/login.action'/>" name="loginform"
							accept-charset="utf-8" id="login_form" class="loginForm"
							method="post">
							<div class="uinArea" id="uinArea">
								<label class="input-tips" for="u">帐号：</label>
								<div class="inputOuter" id="uArea">
									<input type="text" id="u" name="userName" class="inputstyle" value="呵呵"/>
								</div>
							</div>
							<div class="pwdArea" id="pwdArea">
								<label class="input-tips" for="p">密码：</label>
								<div class="inputOuter" id="pArea">
									<input type="password" id="p" name="userPwd" class="inputstyle" value="123456"/>
								</div>
							</div>
							<div class="uinArea">
								    <img src="<c:url value='/user/ajax/getYzm.action'/>" style="display: inline;" id="img_yzm"/>
									<input type="text" name="userYzm" style="width: 90px;vertical-align: 50%;height:30px;font-size: 16px" value="111"/>
							</div>

							<div style="padding-left: 50px; margin-top: 20px;">
								<input type="submit" value="登 录" style="width: 150px;"
									class="button_blue"  />
							</div>
						</form>
					</div>

				</div>

			</div>
			<!--登录end-->
		</div>
	</div>
	<div class="jianyi">*推荐使用ie8或以上版本ie浏览器或Chrome内核浏览器访问本站</div>
</body>

<script type="text/javascript">
    $(function(){
    	//给img_yzm添加点击事件
    	$("#img_yzm").click(function(){
    		$("#img_yzm").attr("src","<c:url value='/user/ajax/getYzm.action?n='/>"+new Date().getTime());
    	});
    });
</script>

</html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>千层在线学习后台系统</title>
<script src="${basePath}statics/js/jquery-1.7.2.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<style>
.background {
	position: absolute;
	background: url("<%=basePath%>/statics/zxlImgs/login2.png");
	height: 660px;
	background-size: cover;
	filter: blur(5px);
	width: 100%;
}

.container {
	position: absolute;
	text-align: center;
	color: #fff;
}

.formFrame {
	margin-top: 5%;
	background-color: rgba(255, 255, 255, 0.4);
	height: 440px;
}

.form-group {
	margin-top: 5%;
}

#checkCode {
	background-color: #c3c7d2;
	width: 80px;
	border: none;
	color: #0000FF;
	font-size: 16px;
}
</style>
<script>
	/* 账号、密码的输入不能为空*/

	$(function() {
		$(".required").each(function() {
			//将它追加到文档中
			$(this).after("<strong class='high'>*</strong>");
		});
		$("#submit").click(function() {
			var uname = $("#username").val();
			var password = $("#password").val();

			$.ajax({
				//请求路径
				url : 'login',
				//请求方式
				type : 'post',
				//请求所带的数据 
				data : {
					'uname' : uname,
					'password' : password
				},
				//请求过后返回的类型
				dataType : 'json',
				//成功请求之后回调的函数
				success : function(data) {
					if (data.result != null) {
						window.location.href = 'bgindex.jsp';
					}
				}
			})
		});

	});
</script>
<body>
	<div class="background"></div>
	<div class="container" style="margin: 0px; width: 100%;">
		<h2 align="center">欢迎登录千层在线学习后台系统</h2>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6 ">
				<form class="form-horizontal formFrame" method="post" action=""
					name="myForm" onSubmit="return validate()">
					<h2 style="padding-top: 10%;">登&nbsp;&nbsp;&nbsp;&nbsp;录</h2>
					<div class="form-group" style="margin-top: 5%;">
						<div class="col-md-2"></div>
						<label class="col-md-2 control-label">用户名：</label>
						<div class="col-md-6" id="cue1">
							<input type="text" class="form-control required" id="username"
								placeholder="用户名">
						</div>
						<div class="col-md-2"></div>
					</div>
					<div class="form-group">
						<div class="col-md-2"></div>
						<label class="col-md-2 control-label">密 &nbsp;&nbsp; 码：</label>
						<div class="col-md-6" id="cue2">
							<input type="password" class="form-control required"
								id="password" placeholder="密码">
						</div>
						<div class="col-md-2"></div>
					</div>

					<div class="form-group">
						<div class="col-md-2"></div>
						<div class="col-md-4">
							<input type="checkbox" checked="checked" /> 记住我
						</div>
						<div class="col-md-4">
							<input type="button" class="btn btn-default" value="登录"
								id="submit">
						</div>
						<div class="col-md-2"></div>
					</div>
				</form>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
</body>
</html>

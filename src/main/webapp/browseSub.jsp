<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath", basePath);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${basePath}statics/js/jquery-1.9.1.js"></script>
<link rel="stylesheet"
	href="${basePath}statics/bootstrap/css/bootstrap.min.css">
<style type="text/css">
#pullUp {
	background: #fff;
	height: 40px;
	padding: 5px 10px;
	font-size: 14px;
	color: #888;
	margin-left: 45%;
}
p{text-indent:2em} 
</style>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="container-fluid">
	
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-4">
				<input type="text" class="form-control" placeholder="关键字">
			</div>
			<div class="col-md-2">
				<a class="btn btn-default" href="#" role="button">搜索</a>
			</div>
			<div class="col-md-3"></div>
		</div>
		
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div style="margin-top: 20px;">
					<div name="subtime" style="float: right;"></div>
					<div style="height: 100px">
						<a name="subtext" href="#"></a>
						<p name="subsunmmary" class="navbar-text"></p>
					</div>
				</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>

	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js
"></script>
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
		<c:forEach var="sub" items="${Subs}" >   
				<div class="row" style="border-style: solid;">
			<div class="col-md-1"></div>
			<div class="col-md-10">
				<div style="margin-top: 20px;">
					<div style="float: right;">${sub.subTime}</div>
					<div style="height: 100px">
						<a name="subtext" href="#">${sub.subText}</a>
						<p class="navbar-text">${sub.subSummary}</p>
					</div>
				</div>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
					</c:forEach> 
	</div>
</body>
</html>
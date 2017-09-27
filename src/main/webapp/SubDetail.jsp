<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>详细题目</title>
<link href="${basePath}statics/themes/bootstrap/easyui.css"
	rel="stylesheet">
<link href="${basePath}statics/themes/icon.css" rel="stylesheet">
<link href="${basePath}statics/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${basePath}statics/js/jquery-1.9.1.js"></script>
<script src="${basePath}statics/js/jquery.easyui.min.js"></script>
<script src="${basePath}statics/js/easyui-lang-zh_CN.js"></script>
<script src="${basePath}statics/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/js/SubDetail.js"></script>
</head>
<body>
	<!-- 使用户知道这是个导航栏 -->
	<nav class="navbar navbar-default">
	<div class="container=fluid">
		<div class="navbar-header">
			<ul class="nav navbar-nav navbar-left">
				<li><a href="" style="color: gray">上一题</a></li>
				<li><a href="" style="color: gray">下一题</a></li>
			</ul>
			<!-- 导航栏左边 -->
			<a class="navbar-brand" style="color: gray"></a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<!-- 导航栏右边 -->
			<ul class="nav navbar-nav navbar-right">
				<li><a href="" style="color: gray">退出</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div>
		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
			<div class="col-md-4 col-sm-4 col-xs-4">
				<div>
					<input type="text" id="id" style="display: none"
						value="${param.sid}">
					<h2 id="subtype"></h2>
					<br>
					<h4 id="subcontent"></h4>
					<br>
				</div>
			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div>
		<br> <br>
		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
			<div class="col-md-6 col-sm-6 col-xs-6">
				<botton id="showAnswer" type="button" class="btn btn-default"
					style="margin-left: 40%" onclick="show()">显示答案以及评论</botton>
			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div>
	</div>
	<div id="anwerAndcomment" name="anwerAndcomment" style="display: none">
		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
			<div class="col-md-6 col-sm-6 col-xs-6">
				<h4 id="answer">答案是B</h4>
			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div>
		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
			<div class="col-md-6 col-sm-6 col-xs-6">
				<textarea cols="60" id="msg" name="msg" rows="4" placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。" style="display: inline"></textarea>
				<div style="display: inline">
					<a class="btn btn-default" id="addComment" role="button" onclick="addNewComment()">发表评论</a>
					<a class="btn btn-default" role="button">添加笔记</a>
				</div>
			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div>
			
		<div id="comment"></div>
	</div>
	<br>
</body>
</html>
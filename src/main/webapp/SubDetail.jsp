<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>详细题目</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/statics/bootstrap/css/bootstrap.min.css" />
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
					<h2 id="subtype">选择题</h2>
					<br>
					<h4 id="subcontent">PPP协议是 （） 的协议。</h4>
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
				<h4>答案是B</h4>
			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div>
		<div class="row">
			<div class="col-md-7 col-sm-7 col-xs-7"></div>
			<div class="col-md-2 col-sm-2 col-xs-2">
				<a class="btn btn-default" href="#" role="button">添加笔记</a>
			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div>

		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
			<div class="col-md-6 col-sm-6 col-xs-6">
				<div>
					<div id="username">用户名：鹳狸猿</div>
					<div class="talk">
						点对点协议（PPP）为在点对点连接上传输多协议数据包提供了一个标准方法。PPP 最初设计是为两个对等节点之间的 IP
						流量传输提供一种封装协议。在 TCP-IP 协议集中它是一种用来同步调制连接的数据链路层协议（OSI
						模式中的第二层），替代了原来非标准的第二层协议，即 SLIP。除了 IP 以外 PPP 还可以携带其它协议，包括 DECnet 和
						Novell 的 Internet 网包交换（IPX）。<br>
					</div>
					<div class="down">
						<a href="">赞(20)</a>
						<a href="">踩(0)</a>
					</div>
				</div>

			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div><br>
		
		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
			<div class="col-md-6 col-sm-6 col-xs-6">
				<div>
					<div id="username">用户名：学渣</div>
					<div class="talk">
						这题真鸡脖难<br>
					</div>
					<div class="down">
						<a href="">赞(0)</a>
						<a href="">踩(3)</a>
					</div>
				</div>

			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div><br>
		
		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
			<div class="col-md-6 col-sm-6 col-xs-6">
				<div>
					<div id="username">用户名：学霸</div>
					<div class="talk">
						基础题，难度还行。<br>
					</div>
					<div class="down">
						<a href="">赞(4)</a>
						<a href="">踩(1)</a>
					</div>
				</div>
			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div>
	</div><br>
</body>
</html>
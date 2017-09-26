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

p {
	text-indent: 2em
}
</style>
<script type="text/javascript">
//题目显示栏高度
var subHeight = 3000;
//下拉次数
var loadtimms = 1;
window.onscroll = function() {
	var a = document.documentElement.scrollTop == 0 ? document.body.clientHeight
			: document.documentElement.clientHeight;
	var b = document.documentElement.scrollTop == 0 ? document.body.scrollTop
			: document.documentElement.scrollTop;
	var c = document.documentElement.scrollTop == 0 ? document.body.scrollHeight
			: document.documentElement.scrollHeight;

	if (a + b + 5 >= c) {
		$.ajax({
			url : 'selectSub?loadtimms=' + loadtimms,
			type : 'POST',
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data.Subs.length > 0) {
					for (var i = 0; i < data.Subs.length; i++) {
						console.log(data);
						$('#Subs').append("<li class='span4'><div class='thumbnail'><div class='caption'><div style='float: right;'>"
					            +data.Subs[i].subTime+
					            "</div><h4>"
					            +data.Subs[i].subText+
					            "</h4><p>"
					            +data.Subs[i].subSummary+
					            "</p><p><a class='btn btn-primary' href='SubDetail.jsp?sid="+data.Subs[i].subId+"'>浏览</a></p></div></div>");
					}
				} else {
					$('#pullUp').text('已全部加载');
				}
			}
		});
		loadtimms = loadtimms + 1;
	}
};
$(function() {
	$.ajax({
		url : 'selectSub?loadtimms=' + 0,
		type : 'POST',
		async : false,
		dataType : 'json',
		success : function(data) {
        console.log(data);
			if (data.Subs.length > 0) {
				for (var i = 0; i < data.Subs.length; i++) {
					$('#Subs').append("<li class='span4'><div class='thumbnail'><div class='caption'><div style='float: right;'>"
					            +data.Subs[i].subTime+
					            "</div><h4>"
					            +data.Subs[i].subText+
					            "</h4><p>"
					            +data.Subs[i].subSummary+
					            "</p><p><a class='btn btn-primary' href='SubDetail.jsp?sid="+data.Subs[i].subId+"'>浏览</a></p></div></div>");
				}
			} else if(data.data.length < 4){
				$('#pullUp').text('已全部加载');
			}
		}
	});
});
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
	</div>
	<ul id="Subs" class="thumbnails">
	</ul>
	<div id="pullUp"><img src="statics/images/loading.gif" ></div>
</body>
</html>
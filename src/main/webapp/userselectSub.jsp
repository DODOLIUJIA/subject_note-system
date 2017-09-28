<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<!-- 引入js -->
<link rel="stylesheet"
	href="${basePath}statics/bootstrap/css/bootstrap.min.css">
<!-- 引入jq -->
<script type="text/javascript"
	src="${basePath}statics/js/jquery-1.9.1.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js 

"></script>

<!-- 字体设置 -->
<style>
body {background-color: #eee;}
.font {position: absolute;	z-index: 1;	width: 100%;text-align: center;color: #fff;margin-top: 2%;}
.trasation-font {width: 100%;text-align: center;margin-top: 4%;}
.footer {height: 150px;	background-color: #222;margin-top: 5%;color: #fff;padding: 3% 10%;}
.col-md-4 {text-align: center;}
.col-md-4 p a {color: #fff;}
.accordion-heading {
	color: #0059b2;
	font-size: 15px;
	letter-spacing: 1px;
	text-align: center;
	margin: 45px 0 10px;
}
.nav-sidebar{
cursor:pointer;
}

</style>

</head>
<script type="text/javascript">
//题目显示栏高度
var subHeight = 3000;
//下拉次数
var loadtimms = 1;
//是否还有题目的标志
var flage = true;
//设置题目标签点击事件		
window.onscroll = function() {
	var a = document.documentElement.scrollTop == 0 ? document.body.clientHeight
			: document.documentElement.clientHeight;
	var b = document.documentElement.scrollTop == 0 ? document.body.scrollTop
			: document.documentElement.scrollTop;
	var c = document.documentElement.scrollTop == 0 ? document.body.scrollHeight
			: document.documentElement.scrollHeight; 
	if (a + b + 1 >= c&&flage==true) {
		$.ajax({
			url : 'selectAllSubject?loadtimms=' + loadtimms,
			type : 'POST',
			async : false,
			dataType : 'json',
			success : function(data) {
				console.log(data.Subs.length);
				if (data.Subs.length==4){
					for (var i = 0; i < data.Subs.length; i++) {
						$('#AllSubs').append("<li class='span4'><div class='thumbnail'><div class='caption'><div style='float: right;'>"
					            +data.Subs[i].subTime+
					            "</div><h4>"
					            +data.Subs[i].subText+
					            "</h4><p>"
					            +data.Subs[i].subSummary+
					            "</p><p><a class='btn btn-primary' href='SubDetail.jsp?sid="+data.Subs[i].subId+"'>浏览</a></p></div></div>");
					}
				} else if(data.Subs.length > 0){
					for (var i = 0; i < data.Subs.length; i++) {
						$('#AllSubs').append("<li class='span4'><div class='thumbnail'><div class='caption'><div style='float: right;'>"
					            +data.Subs[i].subTime+
					            "</div><h4>"
					            +data.Subs[i].subText+
					            "</h4><p>"
					            +data.Subs[i].subSummary+
					            "</p><p><a class='btn btn-primary' href='SubDetail.jsp?sid="+data.Subs[i].subId+"'>浏览</a></p></div></div>");
						}
					$('#MainPullUp').text('已全部加载');
					flage = false;
				}else{
					$('#MainPullUp').text('已全部加载');
					flage = false;
				}
			}
		});
		loadtimms = loadtimms + 1;
	}
};
	$(function() {
		var timer;
		$(".userfun").mouseover(function() {
			clearTimeout(timer);
			$(".dropdown-menu").show();
			$(".menu").hide();
		});
		$(".userfun").mouseout(function() {
			timer = setTimeout(function() {
				$(".dropdown-menu").hide();
			}, 500);
		});
		$(".dropdown-menu").mouseover(function() {
			clearTimeout(timer);
		});
		$(".dropdown-menu").click(function() {
			timer = setTimeout(function() {
				$(".dropdown-menu").hide();
			}, 100);
		});
		$(".dropdown-menu li a").mouseover(function() {
			$(this).css("color", "black");
		});
		$(".dropdown-menu li a").mouseout(function() {
			$(this).css("color", "white");
		});
		//题目标签
		var sublabel ="";
		//出题时间
		var subCrateTime=0;
		$("[name='sublabel']").click(function(){
			if(subCrateTime!=0){
				subCrateTime=0;
			}
			    sublabel = $(this).html();
				var link = $(this).attr('target');
				$.ajax({
					url:'getSubMsg',
					type:'post',
					data:{'sublabel':sublabel,'subCrateTime':subCrateTime},
					success:function(data){
							$("#center").load(link);
							subCrateTime=0;
					}
				})
			
		});
		//设置出题时间点击事件
		$("[name='subCrateTime']").click(function(){
		        subCrateTime = $(this).html();
			var link = $(this).attr('target');
			$.ajax({
				url:'getSubMsg',
				type:'post',
				data:{'sublabel':sublabel,'subCrateTime':subCrateTime},
				success:function(data){
						$("#center").load(link);
				}
			})
		})

		//展示题目标签和出题时间
		$.ajax({
			url:'showLabelAndTime',
			type:'post',
			success:function(){
			}
		})
		//展示所有题目
	$.ajax({
		url : 'selectAllSubject?loadtimms=' + 0,
		type : 'POST',
		async : false,
		dataType : 'json',
		success : function(data) {
			$("#MainPullUp").hide();
			if (data.Subs.length ==4) {
				$("#MainPullUp").show();
				for (var i = 0; i < data.Subs.length; i++) {
					$('#AllSubs').append("<li class='span4'><div class='thumbnail'><div class='caption'><div style='float: right;'>"
					            +data.Subs[i].subTime+
					            "</div><h4>"
					            +data.Subs[i].subText+
					            "</h4><p>"
					            +data.Subs[i].subSummary+
					            "</p><p><a class='btn btn-primary' href='SubDetail.jsp?sid="+data.Subs[i].subId+"'>浏览</a></p></div></div>");
				}
			}else if(data.Subs.length >0){
				$("#MainPullUp").show();
				for (var i = 0; i < data.Subs.length; i++) {
					$('#AllSubs').append("<li class='span4'><div class='thumbnail'><div class='caption'><div style='float: right;'>"
					            +data.Subs[i].subTime+
					            "</div><h4>"
					            +data.Subs[i].subText+
					            "</h4><p>"
					            +data.Subs[i].subSummary+
					            "</p><p><a class='btn btn-primary' href='SubDetail.jsp?sid="+data.Subs[i].subId+"'>浏览</a></p></div></div>");
				}
				flage = false;
				$('#MainPullUp').text('已全部加载');
			}else{
				flage = false;
				$("#MainPullUp").show();
				$('#MainPullUp').text('已全部加载');
			}
		}
	});	
});
</script>


<body>
<!-- 导航栏 -->
	<nav class="navbar navbar-inverse navbar-fixed-top"
		style="background-color: #222; font-size: 20px; height: 65px; padding-top: 10px;">
		<div class="container-fluid"
			style="margin-left: 10%; margin-right: 10%;">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">

				<img class="navbar-brand"
					src="<%=basePath%>statics/zxlImgs/logo.jpg" style="padding: 0px;" />

			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li id="index" class="active"><a href="index.jsp">首页</a></li>
					<li id="sub"><a href="${basePath}showLabelAndTime">题库</a></li>
					<li id="note"><a href="${basePath}showtabel">我的笔记</a></li>
				</ul>
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search...">
					</div>
					<span class="glyphicon glyphicon-search" style="margin-left: -15%;"></span>
				</form>
				<ul class="nav navbar-nav navbar-right">

					<c:choose>
						<c:when test="${sessionScope.uname == null }">
							<li><a style="color:blue;font-size:15px;" href="login.jsp">登录</a></li>
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0)" class="userfun"
								style="padding: 0px;" aria-expanded="false"> <img
								src="<%=basePath%>statics/zxlImgs/image.jpg" alt="..."
								class="img-circle" style="width: 50px;">
							</a>
						</c:otherwise>
					</c:choose>
					<ul class="dropdown-menu" style="text-align: center; padding: 0px; background-color: #222;">
						<li><a href="userPage.jsp"
							style="color: #fff; height: 35px; line-height: 33px">个人中心</a></li>
						<li role="separator" class="divider" style="margin: 0px;"></li>
						<li><a href="#"
							style="color: #fff; height: 35px; line-height: 33px">账号设置</a></li>
						<li role="separator" class="divider" style="margin: 0px;"></li>
						<li><a href="login.jsp"
							style="color: #fff; height: 35px; line-height: 33px">退出账号</a></li>
					</ul>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div class="container-fluid" style="padding:7% 10%;">
		<div class="row-fluid">
			<div id="main" class="col-sm-3 col-md-2 sidebar">

				<div class="accordion" id="subChoose">
					<!-- 试题类型 -->
					<div class="accordion-group " id="subType">
						<div class="accordion-heading">
							<ul class="nav nav-sidebar">
								<li class="active "><a class="accordion-toggle"
									data-toggle="collapse" data-parent="#subChoose"
									href="#subTypeChoose">试题类型</a></li>
							</ul>
						</div>
						<div id="subTypeChoose" class="accordion-body collapse in">
							<div class="accordion-inner">
								<ul class="nav nav-sidebar">
								<c:forEach var="label" items="${labels}" >
								<li><a name="sublabel" target="browseSub.jsp">${label.s_lname}</a></li>
								</c:forEach>
								</ul>
							</div>
						</div>
					</div>

					<!-- 出题时间 -->
					<div class="accordion-group" id="subCrateTimes">
						<div class="accordion-heading">
							<ul class="nav nav-sidebar">
								<li class="active"><a class="accordion-toggle"
									data-toggle="collapse" data-parent="#subChoose"
									href="#subCrateTime">出题时间</a></li>
							</ul>
						</div>
						<div id="subCrateTime" class="accordion-body collapse in">
							<div class="accordion-inner">
								<ul class="nav nav-sidebar">
								<c:forEach var="time" items="${times}" >
								<li><a name="subCrateTime" target="browseSub.jsp">${time.subTime}</a></li>
								</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>


			</div>

			<div id="center" class="col-sm-9 col-md-10 sidebar">
				<ul id="AllSubs" class="thumbnails"></ul>
				 <div id="MainPullUp" style="position: absolute;left: 50%"><img src="statics/images/loading.gif" ></div>
			</div>
  
		</div>
	</div>
   <!-- 尾部 -->
	<footer class="footer row">
		<div class="col-md-4">
			<p>千层网，你的必备神器</p>
			<p>欢迎加入我们</p>
			<span style="font-family: arial;">Copyright &copy;2017
				www.qianceng.com</span>
		</div>
		<div class="col-md-4">
			<img src="<%=basePath%>/statics/zxlImgs/erweima.png" /> <br>
			<p>扫一扫</p>
		</div>
		<div class="col-md-4">
			<p>
				<a>关于我们</a>
			</p>
			<p>
				<a>联系我们</a>
			</p>
			<p>
				<a>合作企业</a>
			</p>
		</div>
	</footer>
</body>
</html>
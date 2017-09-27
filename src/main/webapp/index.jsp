<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
+path+"/";
	session.setAttribute("basePath",basePath);%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>千层在线学习系统</title>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${basePath}statics/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${basePath}statics/js/jquery-1.9.1.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js "></script>
<style>
		body{background-color: #eee;}
       .font{position: absolute;z-index: 1;width: 100%;text-align: center;color: #fff;margin-top:2%;}
        .trasation-font{width: 100%;text-align: center;margin-top: 4%;}
        .card {border: 1px solid #aaa; width: 29%; height: 300px; padding: 0px; margin: 0px 23px;}
        .card img {width: 100%;height: 200px;}
        .card-block {margin: 0px 15px;}
        .footer {height: 150px;background-color: #222;margin-top: 5%;color: #fff;padding: 3% 10%;}
        .col-md-4 {text-align: center;}
        .col-md-4 p a{color: #fff;}
    </style>
    <script>
        $(function(){
        	$("#content").load("index_content.jsp");
        	 
            var timer;
            $(".userfun").mouseover(function () {
                clearTimeout(timer);
                $(".dropdown-menu").show();
                $(".menu").hide();
            });
            $(".userfun").mouseout(function () {
                timer = setTimeout(function () {
                    $(".dropdown-menu").hide();
                },500);
            });
            $(".dropdown-menu").mouseover(function () {
                clearTimeout(timer);
            });
            $(".dropdown-menu").click(function () {
                $(".dropdown-menu").hide();
            });
            $(".dropdown-menu li a").mouseover(function(){
                $(this).css("color","black");
            });
            $(".dropdown-menu li a").mouseout(function(){
                $(this).css("color","white");
            });
          
        });

    </script>
</head>
<body>
<!-- 导航栏 -->
<nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: #222;font-size: 20px;height: 70px;padding-top: 10px;">
    <div class="container-fluid" style="margin-left: 10%;margin-right: 10%;">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header" >
        	<img class="navbar-brand" src="<%=basePath%>statics/zxlImgs/logo.jpg" style="padding: 0px;"/>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li id="index" class="active"><a href="index.jsp">首页</a></li>
                <li id="sub"><a href="userselectSub.jsp">题库</a></li>
                <li id="note" ><a href="note.jsp">我的笔记</a></li>
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
	                    <li><a href="login.jsp">登录</a></li>
	                </c:when>
				<c:otherwise>
                      <a  href="javascript:void(0)" class="userfun"
                    style="padding: 0px;"aria-expanded="false" >                  
                   		<img src="<%=basePath%>statics/zxlImgs/image.jpg" alt="..." class="img-circle" style="width:50px;"> 	
                	</a>                    
                </c:otherwise>
			</c:choose>
                <ul class="dropdown-menu" style="text-align:center;padding:0px;background-color: #222;">
                    <li><a href="userPage.jsp" style="color:#fff;height:35px;line-height:33px">个人中心</a></li>
                    <li role="separator" class="divider" style="margin:0px;"></li>
                    <li><a href="#" style="color:#fff;height:35px;line-height:33px">账号设置</a></li>
                    <li role="separator" class="divider" style="margin:0px;"></li>
                    <li><a href="login.jsp" style="color:#fff;height:35px;line-height:33px">退出账号</a></li>
                </ul>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div id="content"></div>

<!-- 尾部 -->
<footer class="footer row">
    <div class="col-md-4">
        <p>千层网，你的必备神器</p>
        <p>欢迎加入我们</p>
        <span style="font-family:arial;">Copyright &copy;2017 www.qianceng.com</span>
    </div>
    <div class="col-md-4">
        <img src="<%=basePath%>/statics/zxlImgs/erweima.png"/>
        <br>
        <p>扫一扫</p>
    </div>
    <div class="col-md-4">
        <p><a>关于我们</a></p>
        <p><a>联系我们</a></p>
        <p><a>合作企业</a></p>
    </div>
</footer>
</body>
</html>
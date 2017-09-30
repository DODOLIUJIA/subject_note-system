<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
+path+"/";
	session.setAttribute("basePath",basePath);%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <script src="${basePath}statics/js/jquery-1.7.2.min.js"></script>
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>千层在线学习</title>

<style>
		body{background-color: #eee;}
       .font{position: absolute;z-index: 1;width: 100%;text-align: center;color: #fff;margin-top:2%;}
        .trasation-font{width: 100%;text-align: center;margin-top: 4%;}
        .footer {height: 150px;background-color: #222;margin-top: 5%;color: #fff;padding: 3% 10%;}
        .col-md-4 {text-align: center;}
        .col-md-4 p a{color: #fff;}
        .card {	border: 1px solid #aaa;width: 25%;height: 200px;	padding: 0px;	margin: 0px 23px;}
.card img {width: 100%;	height: 100px;}
.card-block {margin: 0px 15px;}
    .left-bar {background-color: #222;padding: 5% 8%;margin-bottom: 10%;}
    .user-info{background-color: #222;padding: 5% 8%;text-align: center;height:400px;}
    .content{height: 100%;padding: 8% 10%;color: #fff;}
    .study-content-info{background-color:#222;height:400px;padding:4% 5%; margin: 0px;margin-top: 3.5%;}
    .dropdown-li {width:25%;}
    </style>
    <script>
        $(function(){
        	var timer0;
            $(".userfun").mouseover(function () {
                clearTimeout(timer0);
                
                $(".dropdown-menu").show();
                $(".menu").hide();
            });
            $(".userfun").mouseout(function () {
                timer0 = setTimeout(function () {
                    $(".dropdown-menu").hide();
                },500);
            });
            $(".dropdown-menu").mouseover(function () {
                clearTimeout(timer0);
            });
            $(".dropdown-menu").mouseout(function () {
            	timer0 = setTimeout(function () {
                    $(".dropdown-menu").hide();
                },100);
            });
            $(".dropdown-menu li a").mouseover(function(){
                $(this).css("color","black");
            });
            $(".dropdown-menu li a").mouseout(function(){
                $(this).css("color","white");
            });
            var timer;
            $(".dropdown").mouseover(function () {
                clearTimeout(timer);
                $(".menu").show();
            });
            $(".dropdown").mouseout(function () {
                timer = setTimeout(function () {
                    $(".menu").hide();
                },500);
            });
            $(".menu").mouseover(function () {
                clearTimeout(timer);
            });
            $(".menu").mouseout(function () {
            	timer = setTimeout(function () {
                    $(".menu").hide();
                },100);
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
        <img class="navbar-brand" src="<%=basePath%>/statics/zxlImgs/logo2.jpg" style="padding: 0px;"/>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li id="index"><a href="index.jsp">首页</a></li>
                <li id="sub" ><a href="userselectSub.jsp">题库</a></li>
                <li id="note" ><a href="note.jsp">我的笔记</a></li>
            </ul>
            

            <ul class="nav navbar-nav navbar-right">
                <a  href="javascript:void(0)" class=" active userfun"
                    style="padding: 0px;"aria-expanded="false">
                  
                   <img src="<%=basePath%>statics/zxlImgs/image.jpg" alt="..." class="img-circle" style="width:50px;">
                </a>
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
<!-- 内容 -->
<div class="content">
	<!-- 左侧用户基本信息 -->
	<div class="left-menu" style="float:left;width:25%;">
		<div class="left-bar">
			<div class="sign-time">
				<div class="sign-week">周六</div>
				<div>09-30</div>
			</div>		
		</div>
		<div class="user-info">
			<div class="side-profile-pic" href="javascript:void(0);">
			<img src="<%=basePath%>statics/zxlImgs/image.jpg" alt="..." class="img-circle" style="width:100px;" id="avatarImage">
			<div class="change-photo"></div>
			<h3 class="side-profile-name level-color-5">${uname}</h3>
		</div>
		<div class="profile-count-box">
			<a class="btn btn-primary" href="index.jsp">返回首页</a> 
			<a class="btn btn-primary" href="/">编辑资料</a>
		</div>					
	</div>	
	</div>
	<!-- 右侧操作内容 -->
	<div class="study-content" style="float:left;padding:0% 3%;width: 75%;">
	<nav class="navbar navbar-dark navbar-inverse" style="height:66px;padding:0px;">
        <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-left">
	            <ul class="dropdown-menu menu" style="text-align:center;padding:0px;background-color: #222;">
	            	<li><a href="userPage.jsp" style="color:#fff;height:35px;line-height:33px">Java基础</a></li>
	                <li role="separator" class="divider" style="margin:0px;"></li>
	                <li><a href="#" style="color:#fff;height:35px;line-height:33px">nodeJs练习</a></li>
	                <li role="separator" class="divider" style="margin:0px;"></li>
	                <li><a href="login.jsp" style="color:#fff;height:35px;line-height:33px">C++入门</a></li>
	            </ul>
	         </ul>
	         <ul class="nav navbar-nav" style="width:100%;font-size:18px;text-align:center;">
	            <li class="dropdown-li"><a  href="javascript:void(0)" class="dropdown"
	            aria-expanded="false" >刷题</a></li>
                <li class="dropdown-li"><a href="node.jsp">笔记</a></li>
                <li class="dropdown-li"><a href="#">个人资料</a></li>
                <li class="dropdown-li"><a href="#">设置</a></li>
            </ul>
       </div>
    </div><!-- /.container-fluid -->
</nav>
	
		<div class="row study-content-info">
		<h3>看过的试题</h1>
        <div class="card col-md-4">
            <img class="card-img-top"  src="<%=basePath%>/statics/zxlImgs/c++.jpg"/>
            <div class="card-block">
                <h4 class="card-title">C++入门</h4>
                <button class="btn btn-primary btn-sm">继续查看</button>
            </div>
        </div>
        <div class="card col-md-4">
            <img class="card-img-top"  src="<%=basePath%>/statics/zxlImgs/java.jpg"/>
            <div class="card-block">
                <h4 class="card-title">Java基础</h4>
                <button class="btn btn-primary btn-sm">继续查看</button>
            </div>
        </div>
        <div class="card col-md-4">
            <img class="card-img-top"  src="<%=basePath%>/statics/zxlImgs/node.jpg"/>
            <div class="card-block">
                <h4 class="card-title">nodeJs练习</h4>
               <button class="btn btn-primary btn-sm">继续查看</button>
            </div>
        </div>
    </div>
		</div>					
	</div>
</div>

<!-- 尾部 -->
<footer class="footer row">
    <div class="col-md-4">
        <p>千层网，你的必备神器</p>
        <p>欢迎加入我们</p>
        <span style="font-family:arial;">Copyright &copy;2017 www.qianceng.com</span>
    </div>
    <div class="col-md-4">
        <img src="<%=basePath%>statics/zxlImgs/erweima.png"/>
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
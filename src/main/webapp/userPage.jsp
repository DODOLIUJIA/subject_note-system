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
        .card {border: 1px solid #aaa; width: 29%; height: 300px; padding: 0px; margin: 0px 23px;}
        .card img {width: 100%;height: 200px;}
        .card-block {margin: 0px 15px;}
        .footer {height: 150px;background-color: #222;margin-top: 5%;color: #fff;padding: 3% 10%;}
        .col-md-4 {text-align: center;}
        .col-md-4 p a{color: #fff;}
        .left-bar {background-color: #222;padding: 5% 8%;margin-bottom: 10%;}
        .user-info{background-color: #222;padding: 5% 8%;text-align: center;}
        .content{height: 100%;padding: 8% 10%;color: #fff;}
    </style>
    <script>
        $(function(){
            var timer;
            $(".userfun").mouseover(function () {
                clearTimeout(timer);
                $(".dropdown-menu").show();
            });
            $(".userfun").mouseout(function () {
                timer = setTimeout(function () {
                    $(".dropdown-menu").hide();
                },100);
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
            })
        });

    </script>
</head>
<body>
<!-- 导航栏 -->
<nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: #222;font-size: 20px;height: 70px;padding-top: 10px;">
    <div class="container-fluid" style="margin-left: 10%;margin-right: 10%;">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header" >
        <img class="navbar-brand" src="<%=basePath%>/statics/zxlImgs/logo.jpg" style="padding: 0px;"/>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="#">首页<span class="sr-only">(current)</span></a></li>
                <li><a href="#">题库</a></li>
                <li><a href="#">我的笔记</a></li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search...">
                </div>
                <span class="glyphicon glyphicon-search" style="margin-left: -15%;"></span>
            </form>
            <ul class="nav navbar-nav navbar-right active">
                <a  href="javascript:void(0)" class="userfun active"
                    style="padding: 0px;"aria-expanded="false" >
                    <img src="<%=basePath%>/statics/zxlImgs/image.jpg" alt="..." class="img-circle" style="width:50px;">
                </a>
                <ul class="dropdown-menu" style="text-align:center;padding:0px;background-color: #222;">
                    <li><a href="#" style="color:#fff;height:35px;line-height:33px">个人中心</a></li>
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
				<div class="sign-week">周一</div>
				<div>09-25</div>
			</div>		
		</div>
		<div class="user-info">
			<div class="side-profile-pic" href="javascript:void(0);">
			<img src="<%=basePath%>/statics/zxlImgs/image.jpg" alt="..." class="img-circle" style="width:100px;" id="avatarImage">
			<div class="change-photo"></div>
			<h3 class="side-profile-name level-color-5">${account.uname}</h3>
		</div>
		<div class="profile-count-box">
			<a class="btn btn-primary" href="/">返回首页</a> 
			<a class="btn btn-primary" href="/profile/1160835/basicinfo#menubox">编辑资料</a>
		</div>					
	</div>	
	</div>
	<!-- 右侧操作内容 -->
	<div class="study-content" style="float:left;padding:0% 7%;width:100%; width: 75%;">
	<nav class="navbar navbar-dark navbar-inverse">
        <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-left">
	         	<a  href="javascript:void(0)" class="userfun"
	            aria-expanded="false" >刷题</a>
	            <ul class="dropdown-menu" style="text-align:center;padding:0px;background-color: #222;">
	            	<li><a href="userPage.jsp" style="color:#fff;height:35px;line-height:33px">Java基础</a></li>
	                <li role="separator" class="divider" style="margin:0px;"></li>
	                <li><a href="#" style="color:#fff;height:35px;line-height:33px">nodeJs练习</a></li>
	                <li role="separator" class="divider" style="margin:0px;"></li>
	                <li><a href="login.jsp" style="color:#fff;height:35px;line-height:33px">C++入门</a></li>
	            </ul>
	         </ul>
	         <ul class="nav navbar-nav">
               <li><a href="#">笔记</a></li>
               <li><a href="#">个人资料</a></li>
                <li><a href="#">设置</a></li>
            </ul>
       </div>
    </div><!-- /.container-fluid -->
</nav>
	
		<div class="study-content-info" style="backgroun-color:#222;">
			<h1>看过的试题</h1>
			<ul class="paper-list">
				<li class="paper-item">
					<a href="#">
						<div class="paper-title">编译和体系结构,加密和安全等专项练习</div>
						<div class="paper-type">专 项 练 习</div>
					</a>
					<ul class="paper-result">
						<a href="#">
							<li><i class="finish-time"></i>完成时间： 2017-09-18</li>
						</a>
						<li>
							<a href="/profile/1160835/test/11189603/108203">
							<i class="finish-score"></i>得分：50 </a>
							<a href="/test/7135437/summary" class="link-green link-again">再做一次</a>
						</li>
						<li style="visibility: hidden">
							<i class="finish-rank"></i>排名：1
						</li>
						<li style="visibility: hidden">
							<i class="legend-label"></i>已练习数：1
						</li>
					</ul>
				</li>
			</ul>
		</div>					
	</div>
</div>
	
<!-- 尾部 -->
<footer class="footer row" style="clear:both;">
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
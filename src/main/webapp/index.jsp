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
                <li class="active"><a href="#">首页<span class="sr-only">(current)</span></a></li>
                <li><a href="#">题库</a></li>
                <li><a href="note.jsp">我的笔记</a></li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search...">
                </div>
                <span class="glyphicon glyphicon-search" style="margin-left: -15%;"></span>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <a  href="javascript:void(0)" class="userfun"
                    style="padding: 0px;"aria-expanded="false" >
                    <img src="<%=basePath%>/statics/zxlImgs/image.jpg" alt="..." class="img-circle" style="width:50px;">
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
<div class="content" style="margin-top:5.2%;">
    <!-- carousel -->
    <div id="myCarousel" class="carousel slide" >
        <!-- 轮播（Carousel）指标 -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner">
            <div class="font">
                <h1>千层在线学习网站</h1>
                <h3>期待你的加入</h3>
            </div>
            <div class="item active">
                <img src="<%=basePath%>/statics/zxlImgs/carousel0.jpg" alt="First slide" style="width:100%;height:300px;">
            </div>
            <div class="item">
                <img src="<%=basePath%>/statics/zxlImgs/carousel1.jpg" alt="Second slide" style="width:100%;height:300px;">
            </div>
            <div class="item">
                <img src="<%=basePath%>/statics/zxlImgs/carousel2.jpg" alt="Third slide" style="width:100%;height:300px;">
            </div>
        </div>
        <!-- 轮播（Carousel）导航 -->
        <a class="carousel-control left" href="#myCarousel" data-slide="prev">
            <span style="padding-top:42%;position: absolute;font-size: 80px;">&lsaquo;</span>
        </a>
        <a class="carousel-control right" href="#myCarousel" data-slide="next">
            <span style="padding-top:42%;position: absolute;font-size: 80px;opacity:5">&rsaquo;</span>
        </a>
    </div>
    <!-- 过渡 -->
    <div class="trasation" style="padding: 2% 20%">
        <h1 class="trasation-font">最新精彩试题推荐</h1>
        <hr  />
    </div>
    <!-- 精彩试题 -->
    <div class="row" style="padding:2% 10%">
        <div class="card col-md-1">
            <img class="card-img-top"  src="<%=basePath%>/statics/zxlImgs/c++.jpg"/>
            <div class="card-block">
                <h4 class="card-title">C++入门</h4>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card col-md-1">
            <img class="card-img-top"  src="<%=basePath%>/statics/zxlImgs/java.jpg"/>
            <div class="card-block">
                <h4 class="card-title">Java基础</h4>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card col-md-1">
            <img class="card-img-top"  src="<%=basePath%>/statics/zxlImgs/node.jpg"/>
            <div class="card-block">
                <h4 class="card-title">nodeJs练习</h4>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
        </div>
    </div>
    <div class="row" style="padding:2% 10%">
        <div class="card col-md-1">
            <img class="card-img-top"  src="<%=basePath%>/statics/zxlImgs/js.jpg"/>
            <div class="card-block">
                <h4 class="card-title">javaScript框架</h4>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card col-md-1">
            <img class="card-img-top"  src="<%=basePath%>/statics/zxlImgs/spring.jpg"/>
            <div class="card-block">
                <h4 class="card-title">springMVC初级</h4>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            </div>
        </div>
        <div class="card col-md-1">
            <img class="card-img-top"  src="<%=basePath%>/statics/zxlImgs/web.jpg"/>
            <div class="card-block">
                <h4 class="card-title">web前端入门</h4>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String path=request.getContextPath();
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()
+path+"/";
	session.setAttribute("basePath",basePath);%>
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
                <img src="<%=basePath%>statics/zxlImgs/carousel0.jpg" alt="First slide" style="width:100%;height:300px;">
            </div>
            <div class="item">
                <img src="<%=basePath%>statics/zxlImgs/carousel1.jpg" alt="Second slide" style="width:100%;height:300px;">
            </div>
            <div class="item">
                <img src="<%=basePath%>statics/zxlImgs/carousel2.jpg" alt="Third slide" style="width:100%;height:300px;">
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

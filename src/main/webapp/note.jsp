<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet"
	href=" ${basePath}statics/bootstrap/css/bootstrap.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="${basePath}statics/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
  #left{
          
	      
    }
.li {
	display: flock;
	background: blanchedalmond;
	margin-right: 50px;
	padding-top:10px;
	text-align: center;
	margin-top:20px;
	display: block;
	width: 100%;
	height: 40px;
}
.type{
     cursor:pointer;
}
#ul li:hover {
	background: #F5F5DC;
}

#ul a {	
	font-family: "微软雅黑";
	text-decoration : none;
}
#ul a:hover {
	color: black;
	font-size: 15px;
	
}
#center{   
}
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
</head>
<script type="text/javascript">
      $(function(){
    	  var timer;
          $(".userfun").mouseover(function () {
              clearTimeout(timer);
              
              $(".dropdown-menu").show();
              $(".menu").hide();
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
          });
    	 $("li").click(function(){
    		// console.log(1111);
    		 var link = $(this).find('a').attr("target");
    		 $("#center").load(link);
    	 });
    	 $.ajax({
    		url:'${basePath}showtabel',
    		data:'',
    		  type:'post',
	    	  dataType:'json',
	    	  success:function(data){
	    		  
	    	  }
    	 });
     })
</script>
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
                <li id="index"><a href="index.jsp">首页</a></li>
                <li id="sub" ><a href="userselectSub.jsp">题库</a></li>
                <li id="note" class="active"><a href="note.jsp">我的笔记</a></li>
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
                  <%--  <c:if test="${uname} == null"> --%>
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


	<div class="container-fluid" style="height:500px;">
		<div class="row">
			<div id="left" class="col-md-2">
					<ul  id="ul" class="list-group">
						<c:forEach items="${sessionScope.labels}" var="label">
							<li class="list-group-item"><a class="type" href='${basePath}show?id=${label.n_lid}'>${label.n_lname}</a></li>
						</c:forEach>
						
					</ul>
				
			</div>
			<div id="center" class="col-md-10">
			
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
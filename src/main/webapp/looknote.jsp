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
<!-- 引入主题样式 -->
<link href="${basePath}statics/themes/gray/easyui.css" rel="stylesheet">
<!-- 引入图标的样式 -->
<link href="${basePath}statics/themes/icon.css" rel="stylesheet">
<!-- 先引入jquery -->
<script type="text/javascript" src="${basePath}statics/js/jquery-1.7.2.min.js"></script>
<!-- 引入easyui -->
<script type="text/javascript" src="${basePath}statics/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}statics/js/easyui-lang-zh_CN.js"></script>

<style type="text/css">
   
   #head{
        height:50px;
        border:1px solid black;
   }
   #back{
     float:left;
     background:#F8F8FF;
	 margin-left: 30px;
	 padding-top:5px;
	 text-align: center;
	margin-top:5px;
	transform:all 0.5s ;
   }
   #back:HOVER{
   transform: scale(1.1) rotate(360deg);
   }
    #delete{
      float:right;
	 margin-right: 30px;
	 padding-top:5px;
	 text-align: center;
	margin-top:5px;
	background:#F8F8FF;
    transform:all 0.5s ;
   }
   #delete:HOVER{
   transform: scale(1.1) ;
   }
   #update{
      float:right;
	 margin-right: 30px;
	 padding-top:5px;
	 text-align: center;
	margin-top:5px;
	background:#F8F8FF;
    transform:all 0.5s ;
   }
   #update:HOVER{
   transform: scale(1.1) ;
   }
   #tabel{
      margin-top: 10px;
      margin-left: 10px;
     border-radius:8px;
   }
   #table{
       text-align: center;
   }
   #frame{
      
   }
   #title{
       height:60px;
      
   }
   #title1{

      height:60px;
      margin: 0 auto;
   }
   #title2{
      text-align: center;
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
             $("#menu").hide();
         });
         $(".userfun").mouseout(function () {
             timer = setTimeout(function () {
                 $(".dropdown-menu").hide();
             },500);
         });
         $(".dropdown-menu").mouseover(function () {
             clearTimeout(timer);
         });
         $(".dropdown-menu").mouseout(function () {
         	 timer = setTimeout(function () {
         		 $(".dropdown-menu").hide();
              },100);  
         });
         $(".dropdown-menu li a").mouseover(function(){
             $(this).css("color","black");
         });
         $(".dropdown-menu li a").mouseout(function(){
             $(this).css("color","white");
         });
	        $("#search").keydown(function(){
	        	  
	        });
    	$("#back").click(function(){
    		location.href='note.jsp';
    		$.ajax({
    			
    		})
    	})
    	$("#update").click(function(){
    		location.href='updatenote.jsp';
    		$.ajax({
    			
    		})
    	});
    	$("#delete").click(function(){
    		var title =  $("h2").text();
    		console.log(title);
    		$.ajax({
    			url:'${basePath}deletenote',
    			data:'title='+title,
    			type : 'post',
    			dataType : 'json',
    			success:function(data){
    				if(data.msg==1&&data.msg1==1){
    					location.href = "note.jsp";
    				}else{
    					location.href = "looknote.jsp";
    				}
    			}
    			
    		})
    	});
    })
</script>
<body>
<!-- 导航栏 -->
<nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: #222;font-size: 20px;height: 70px;padding-top: 10px;">
    <div class="container-fluid" style="margin-left: 10%;margin-right: 10%;">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header" >
        	<img class="navbar-brand" src="<%=basePath%>statics/zxlImgs/logo2.jpg" style="padding: 0px;"/>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li id="index" class="active"><a href="index.jsp">首页</a></li>
                <li id="sub"><a href="userselectSub.jsp">题库</a></li>
                <li id="note" ><a href="${basePath}showtabel">我的笔记</a></li>
            </ul>
            

            <ul class="nav navbar-nav navbar-right">
	            <c:choose>
					<c:when test="${sessionScope.uname == null}">
	                      <li><a href="login.jsp" style="color: blue;font-size: 15px;">登录</a></li>         
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
                    <li><a href="loginout" style="color:#fff;height:35px;line-height:33px">退出账号</a></li>
                </ul>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
    <div class="container" style="height:600px ; margin-top: 6%;">
         <div id="head">
             <button class="btn btn-default " id="back">返回</button>
               <button class="btn btn-default " id="delete">删除</button>
             <button class="btn btn-default " id="update">修改</button>
         </div>
         <div id="frame">
                <div id="tabel">
                      类型：<input type="text" name="table" id="table" disabled="disabled" value="${sessionScope.type}">                   
                </div>
                <div id="title">
                       <div id="title1">
                          <h2 id="title2">${sessionScope.title}</h2>
                       </div>
                </div>
                <div id="text">
                      <div class="row">
                           <div class="col-md-1"></div>
                           <div class="col-md-10">${sessionScope.text}</div>
                           <div class="col-md-1"></div>
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
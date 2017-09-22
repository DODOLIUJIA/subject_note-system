<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
        height:40px;
        border:1px solid black;
   }
   #back{
     display: flock;
     float:left;
	background: blanchedalmond;
	margin-right: 10px;
	padding-top:5px;
	text-align: center;
	margin-top:5px;
	display: block;
	width: 70px;
	height: 30px;
   }
  #head1 li:hover{
     background: #F0E68C;
   }
   #update{
      display: flock;
      float:right;
	 background: blanchedalmond;
	 margin-right: 30px;
	 padding-top:5px;
	 text-align: center;
	margin-top:5px;
	display: block;
	width: 70px;
	height: 30px;
   }
     #head1 update:hover{
     background: #F0E68C;
   }
   #head1 a{
    color: black;
	font: 30px;
	font-family: "微软雅黑";
   }
   #head1 a:hover {
	color: white;
}
#se{
    display:block;
}
</style>
</head>
<script type="text/javascript">
    $(function(){
    	$("#select").combobox({
    		
    	})
    })
</script>
<body>
    <div class="container">
         <div id="head">
            <ul id="head1">
                <li id = "back"><a href="shownote.jsp"> 返回</a></li>
                <li id ="update"><a href="#">编辑</a></li>
            </ul> 
         </div>
         <div id="frame">
                <div id="top">
                      <div> <input type="text" name="table" id="table" value="  "></div>
                       <div id="se"> <input id="select" name ="select" ></div>
                </div>
         </div>
    </div>
</body>
</html>
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
</style>
</head>
<script type="text/javascript">
    $(function(){
    	$("#back").click(function(){
    		location.href='shownote.jsp';
    		$.ajax({
    			
    		})
    	})
    })
</script>
<body>
    <div class="container">
         <div id="head">
             <button class="btn btn-default " id="back">返回</button>
             <button class="btn btn-default " id="update">修改</button>
         </div>
         <div id="frame">
                <div id="tabel">
                      类型：<input type="text" name="table" id="table" disabled="disabled">                   
                </div>
                <div id="title">
                    
                </div>
                <div id="text"></div>
         </div>
    </div>
</body>
</html>
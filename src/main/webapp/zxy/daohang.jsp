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
<link href="${basePath}statics/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${basePath}statics/js/jquery-1.9.1.js"></script>
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      
        <li><a id="aa" target="${basePath}subLabelChart.jsp" href="#">Link</a></li>
        
      </ul>
    </div>
  </div>
</nav>

<div id="content"></div>

</body>

<script type="text/javascript">
	var path = "";
	$(function(){
		$("#aa").click(function(){	
			path = $(this).attr('target');
			$("#content").load(path);
		});
	});
	
	

</script>
</html>
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
<script type="text/javascript" src="${basePath}statics/js/jquery-1.7.2.min.js"></script>
<style type="text/css">
  #left{
          	height:300px;
	        border: 1px solid red;
	        background: #F0F8FF;
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

#ul li:hover {
	background: #F5F5DC;
}

#ul a {
	color: darkslategray;
	font: 30px;
	font-family: "微软雅黑";
}

#ul a:hover {
	color: white;
}
#center{
    border:1px solid black;
    height:300px;
}
</style>
</head>
<script type="text/javascript">
      $(function(){
    	 $("li").click(function(){
    		// console.log(1111);
    		 var link = $(this).find('a').attr("target");
    		 $("#center").load(link);
    	 }) 
     })
</script>
<body>
    
	<div class="container-fluid">
		<div class="row">
			<div id="left" class="col-md-3">
				<ul id="ul">
					<li  class="li"><a target="shownote.jsp">java</a></li>
					<li  class="li"><a href="#">bb</a></li>
					<li  class="li"><a href="#">cc</a></li>
				</ul>
			</div>
			<div id="center" class="col-md-9">132131231</div>
			
		</div>
	</div>
</body>
</html>
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
#head{
    height:160px;
    border:1px solid black;
}
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
</style>
</head>
<script type="text/javascript">
      $(function(){
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
    <div id="head"></div>
	<div class="container-fluid">
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
</body>
</html>
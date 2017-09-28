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
<script type="text/javascript"
	src="${basePath}statics/js/jquery-1.7.2.min.js"></script>
<!-- 引入easyui -->
<script type="text/javascript"
	src="${basePath}statics/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${basePath}statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${basePath}statics/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${basePath}statics/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="${basePath}statics/ckeditor/ckeditor.js"></script>
	<style type="text/css">
	   body{
	      background:  	#F0F0F0;
	   }
	    #insert{
	       float: right;
	       margin-right: 50px;
	       margin-top: 10px;
	    }
	    #insert2{
	       float: right;
	       margin-right: 50px;
	       margin-top: 10px;
	    }
	      #insert3{
	       float: right;
	       margin-right: 50px;
	       margin-top: 10px;
	       display: none;
	    }
	      #tabel{
	       float: right;
	       margin-right: 20px;
	       margin-top: 13px;
	       display: none;
	    }
	 #back1{
	       float: left;
	       margin-left: 50px;
	       margin-top: 10px;
	    }
      .title{
          color:blue;
                  
          cursor:pointer;
         
      }
      .tr{
        height:40px;
      }
      td{
        text-align:center;
      }
     #tbody  a {	
    	font-family: "微软雅黑";
	   text-decoration : none;   
    }
  #tbody   a:hover {
	color: black;
}
	</style>
</head>

<body>
	<div class="container-fluid">
		<!--  <table id ="note"></table> -->
		<div id="note" class="col-md-12">
			<button class="btn btn-default " id="insert">添加笔记</button>
			<button class="btn btn-default " id="insert2">添加标签</button>
			<button class="btn btn-default " id="insert3">添加</button>
			<input   id = "tabel" type="text" placeholder="请输入标签名">
			<form id="form"  >
				<table class="table table-hover" id="table" >
				
				<thead>
					<tr>
						<!-- <td><input type="checkbox" id="check"></td> -->
						<td><b>序列</b></td>
						<td style="display: none;"><b>笔记ID</b></td>
						<td><b>笔记标题</b></td>
						<td><b>笔记摘要</b></td>
					</tr>
					</thead>
					<tbody id="tbody">
  
			        </tbody>
					</table>
		</div>
	</div>
</body>
<script type="text/javascript">

	$(function() {
		 $.ajax({
	    	  url:'${basePath}shownote',
	    	  data:'',
	    	  type:'post',
	    	  dataType:'json',
	    	  success:function(data){
	    		//  console.log(data.length);
	    		/*   $("#tbody").empty(); */
	    			for(var i=0 ;i<data.length ;i++){
	    			//	console.log(data[i].noteid);
	    				$("#tbody").append("<tr class='tr'><td>"+(i+1)+"</td>"+"<td style='display:none;'>"+data[i].noteid+"</td>"+
								"<td><a class='title'   name='"+data[i].noteid+"' href='${basePath}looknote?id="+data[i].noteid+"'>"+data[i].notetitle+
										"</a></td>"+"<td>"+data[i].notesummary+"</td>"+"</tr>");
					   
	    			}
	    	  }
	       });
		$("#insert2").click(function(){
			var insert2 = $("#insert2");
			var insert3 = $("#insert3");
			var tabel = $("#tabel");
			insert2.css("display","none");
			insert3.css("display","block");
			tabel.css("display","block");
		});
		$("#insert3").click(function(){
			var type = $("#tabel").val();
			$.ajax({
				url:'${basePath}insertTabel',
				data:'tabel='+type,
				type : 'post',
				dataType : 'json',
				success:function(data){
					if(data.msg==1){
						var insert2 = $("#insert2");
						var insert3 = $("#insert3");
						var tabel = $("#tabel");
						insert2.css("display","block");
						insert3.css("display","none");
						tabel.css("display","none");
						 location.href="note.jsp";
						
					}else{
						$.messager.alert('提示',"添加失败,标签名不能为空",'info',function(){
			        	});
						var insert2 = $("#insert2");
						var insert3 = $("#insert3");
						var tabel = $("#tabel");
						insert2.css("display","block");
						insert3.css("display","none");
						tabel.css("display","none");
					}
				}
			});
		});
	      $("#insert").click(function(){
	    	   location.href="insertnote.jsp";
	      });
    	
	});
</script>
</html>
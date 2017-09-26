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
	  
	    #insert{
	       float: right;
	       margin-right: 50px;
	       margin-top: 10px;
	    }
	
	    #head{
         height:60px;
         border: 1px solid black;
      }
      #title{
          margin-top: 5px ; 
          float:left;
          margin-top:7px;
          margin-left:2px;
           height: 40px;
           width: 250px;
           
      }
      #biao{
          float:left;
          margin-left:60px;
          margin-top:7px;
          font-size:30px;
         
      } 
       #keyword{
          margin-top: 5px ; 
          float:left;
          margin-top:7px;
          margin-left:2px;
           height: 40px;
           width: 250px;
           
      }
      #key{
          float:left;
          margin-left:60px;
          margin-top:7px;
          font-size:30px;
         
      } 
      #cancel{
       position:relative;
       right:0px;
       top:10px;
      
      }
      #insert1{
         position:relative;
       right:0px;
       top:10px;
      }
      #add{
          display:none;
          border: 1px solid; 
          text-align: center; 
          position: absolute; 
          height: 100%; 
          width: 100%; 
         top:0px; 
          background: #F5F5F5; 
           background-size: 100% 100%;  
      }
      #scan{
          height:150px;
          border: 1px solid black;
      }
      .title{
          color:blue;
          cursor:pointer;
         
      }
     #tbody  a {	
    	font-family: "微软雅黑";
	   text-decoration : none;   
    }
  #tbody   a:hover {
	color: black;
	font-size: 15px;
}
	</style>
</head>

<body>
	<div class="container">
		<!--  <table id ="note"></table> -->
		<div id="note" class="col-md-12">
			<button class="btn btn-default " id="insert">添加</button>
			<form id="form"  >
				<table class="table table-striped" id="table">
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
			</form>
			<div id="mu"
		style="display: none; position: absolute; height: 100%; width: 100%; top: 0%; left: 0%; background: #ffffff; opacity: 0"></div>
			
			<form id="add"  >
			<div id="scan">
			    
			</div>
             <div id="head">                    
                     <span id="biao">标题：  </span>     
                    <input id="title" name="title" type="text" placeholder="请输入笔记标题">
                    <span id="key">关键字：  </span>
                    <input id="keyword" name="keyword" type="text" placeholder="请输入关键字">
                    <a id ="cancel" href="#">取消</a>
                     <a id ="insert1" href="#">添加</a>
                        
             </div>
             <div >
                       <textarea name="desc" id="editor"></textarea>
             </div>
             
     </form> 
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
	    		  $("#tbody").empty();
	    			for(var i=0 ;i<data.length ;i++){
	    			//	console.log(data[i].noteid);
	    				$("#tbody").append("<tr><td>"+(i+1)+"</td>"+"<td style='display:none;'>"+data[i].noteid+"</td>"+
								"<td><a class='title'   name='"+data[i].noteid+"' href='${basePath}looknote?id="+data[i].noteid+"'>"+data[i].notetitle+"</a></td>"+"<td>"+data[i].notesummary+"</td>"+"</tr>");
					   
	    			}
	    	  }
	       });
		
		function select(){
			 $.ajax({
		    	  url:'${basePath}shownote',
		    	  data:'',
		    	  type:'post',
		    	  dataType:'json',
		    	  success:function(data){
		    		//  console.log(data.length);
		    		  $("#tbody").empty();
		    			for(var i=0 ;i<data.length ;i++){
		    			//	console.log(data[i].noteid);
		    				$("#tbody").append("<tr><td>"+(i+1)+"</td>"+"<td style='display:none;'>"+data[i].noteid+"</td>"+
									"<td><a class='title'   name='"+data[i].noteid+"' href='${basePath}looknote?id="+data[i].noteid+"'>"+data[i].notetitle+"</a></td>"+"<td>"+data[i].notesummary+"</td>"+"</tr>");
						   	}
		    	  }
		       });
		};
		
		//初始化textarea标签为cheditor
	       CKEDITOR.replace('editor',{
	       	language:'zh-cn',
	       	allowedContent:true,
	       	removePlugins:'elementspath',
	       	resize_enabled:false,
	       	height:'300px',
	       	filebrowserImageUploadUrl:'${basePath}uploadImg?fileType=image&workType=node'
	       });
	       function setContent(){
	       	CKEDITOR.instances.editor.setData('');
	       	
	       }
	       function getContent(){
	       	var content = CKEDITOR.instances.editor.getData();
	     //  console.log(content);
	       return content;
	       }
	      
	       	$("#insert1").linkbutton({
	       	 	iconCls:'icon-ok',
	       	 	onClick:function(){
	       	 		$("#").form('submit');
	       	 	}
	       	 });
	       	$("#cancel").linkbutton({
	       	 	iconCls:'icon-cancel',
	       	 	onClick:function(){
	       	 		$("#").form('submit');
	       	 	}
	       	 });
	      $("#insert").click(function(){
	    	  var mu = $("#mu");
	    	 var add1 = $("#add");
	    	 mu.css("display","block");
	    	 add1.css("display","block");
	    	 $("#insert1").click(function(){
	    		 add();
	    		
	    	 })
	    	
	      });
         function add(){
        	 var title = $("#title").val();
        	 var keyword = $("#keyword").val();
        	 var content = getContent() ;
        	 console.log(title)
        	$.ajax({
        		url:'${basePath}insertnote',
        		data:"title="+title+"&notetext="+content+"&notesummary="+keyword,
        		type : 'post',
				dataType : 'json',
				success:function(data){
					console.log(data)
					if(data==1){
						select();
					}else{
						location.href = "shownote.jsp";
					}
				}
        	});
         }
     	$("#cancel").click(function() {
			var mu = $("#mu");
			var form = $("#add");
			mu.css("display", "none");
			form.css("display", "none");
		});
	});
</script>
</html>
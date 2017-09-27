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
<script type="text/javascript" src="${basePath}statics/ckeditor/ckeditor.js"></script>
<style type="text/css">
      #head{
         height:60px;
         border: 1px solid black;
      }
      #title{
          margin-top: 5px ; 
           float: right;
           margin-right: 100px;
           height: 40px;
           width: 300px;
           
      }
      #biao{
          float: right;
          margin-top: 4px;
          font-size:30px;
          margin-right: 10px;
      }
      #cancel{
          float: right;
          margin-top: 10px;
          margin-right: 60px
      }
      #insert{
          float: right;
          margin-top: 10px;
           margin-right: 30px
      }
</style>
</head>

<body>
  <form id="" method="post">
     <div class="container">
             <div id="head">                    
                     <a id ="cancel" href="#">取消</a>
                     <a id ="insert" href="#">添加</a>
                 <input id="title" type="text" placeholder="请输入笔记标题">
                 <span id="biao">标题：  </span>            
             </div>
             <div >
                       <textarea id="editor"></textarea>
             </div>
             
     </div>
     </form>
</body>
<script type="text/javascript">
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
	var content = CKEDITOR.instance.editor.getData();
}
$(function(){
	$("#insert").linkbutton({
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
});

</script>
</html>
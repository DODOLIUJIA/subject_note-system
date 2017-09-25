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
    height: 60px;
}
     #back{
        float: left;
        margin-top:20px;
        margin-left: 30px;
      }
      #save{
         float: right;
         margin-top:20px;
         margin-right: 30px;
      }
      #tab{
          margin-left: 30px;
      }
      #tit{
         text-align: center;
      }
</style>
</head>
<body>
     <div id="head">
             <button class="btn btn-default " id="back">返回</button>
             <button class="btn btn-default " id="save">保存</button>
     </div>
    <div id="frame">
       <div id="tab">
                      类型：<input name="tabel" id="tabel" value="${sessionScope.type}">
                                                         
        </div>
         <div id="tit"> <h3 id="title" >${sessionScope.title}</h3>  </div>
        <div>
            <textarea id="editor">${sessionScope.text}</textarea>
        </div>
    </div>
</body>
<script type="text/javascript">
 $(function(){
	$("#back").click(function(){
		location.href='looknote.jsp';
	});
	$("#save").click(function(){
		var type = $("#tabel").combobox('getValue');
		console.log(type)
		var text = getContent();
		var title = $("h3").text();
		//console.log(title);
		$.ajax({
			url:'${basePath}updatenote',
			data:"type="+type+"&notetext="+text+"&title="+title,
    		type : 'post',
			dataType : 'json',
			success:function(data){
				if(data.msg==1&&data.msg1==1){
					location.href = "shownote.jsp";
				}else{
					location.href = "looknote.jsp";
				}
			}
		});
	});
 })
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
 $("#tabel").combobox({
	  url:'${basePath}gettabel',
       method : "post", 
	    mode:'local',
	    width:160,
	    valueField: 'n_lid',  
	    textField: 'n_lname', 
	    editable:false,
	    onSelect: function(rec){      
	  },
}); 
</script>
</html>
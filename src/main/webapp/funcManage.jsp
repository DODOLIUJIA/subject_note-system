<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>功能分配</title>
<link href="${basePath}statics/themes/bootstrap/easyui.css"
	rel="stylesheet">
<link href="${basePath}statics/themes/icon.css" rel="stylesheet">
<link href="${basePath}statics/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript" src="${basePath}statics/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="${basePath}statics/js/jquery.easyui.min.js"></script>
<script src="${basePath}statics/js/easyui-lang-zh_CN.js"></script>
<script src="${basePath}statics/ckeditor/ckeditor.js"></script>
</head>
<script type="text/javascript">
$(function(){
	$("#SFunc").hide();
	//选择角色
	$('#SelectRole').combobox({  
		url:'ShowRoles',    
	    valueField:'roleid',    
	    textField:'rolename',
	});
	
	//设置确认角色的点击按钮
	$("#EnterRole").click(function(){
		var roleName = $('#SelectRole').textbox('getText');
		$('#SelectFunc').combotree({    
		    url: 'ShowRoleFuncs?rolename='+roleName,   
		    required: true, 
		    multiple: true,
		});  
		$("#SFunc").show();
})
//设置确认分配按钮
  $("#EnterFunc").click(function(){
	  var roleName = $('#SelectRole').textbox('getText');
	  var n = $('#SelectFunc').combotree('getValues');
	  var funcsid="";
	  for(var i=0;i<n.length-1;i++){
		  if(i==0){
			  funcsid = funcsid+n[i];
		  }else{
			  funcsid = funcsid+","+n[i];
		  } 
	  }
	  console.log(funcsid);
	  //console.log("funcsid:"+funcsid);
	  $.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
		    if (r){    
		        $.ajax({
		        	url:'upDataRoleFunc',
		        	type:'post',
		        	data:{'roleName':roleName,'funcsid':funcsid},
		        	dataType:'json',
		        	success:function(){		        		
		        	}
		        })
		    }    
	  })
  })

});



</script>
<body>
<div id="SRole">
<input id="SelectRole" value="请选择角色">
<input id="EnterRole" type="button" value="确认角色">
</div>
<div id="SFunc">
<input id="SelectFunc" value="请给角色分配功能">
<input id="EnterFunc" type="button" value="确认分配">
</div> 
</body>
</html>
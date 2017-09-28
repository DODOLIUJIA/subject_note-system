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
<!-- 引入主题样式 -->
<link href="${basePath}statics/themes/gray/easyui.css" rel="stylesheet">
<!-- 引入图标的样式 -->
<link href="${basePath}statics/themes/icon.css" rel="stylesheet">
<!-- 先引入jquery -->
<script type="text/javascript"
	src="${basePath}statics/js/jquery-1.7.2.min.js"></script>
<!-- 引入easyui -->
<script type="text/javascript"
	src="${basePath}statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${basePath}statics/js/easyui-lang-zh_CN.js"></script>
<script src="${basePath}statics/ckeditor/ckeditor.js"></script>
<%-- <script src="${basePath}statics/js/UpdateSubject.js"></script> --%>
<style type="text/css">
</style>
</head>
<script type="text/javascript">

//更新
function updataRole(index){
	
	$('#showUsers').datagrid('selectRow',index);
    var row = $('#showUsers').datagrid('getSelected'); 
    console.log($($("input[name='role']")[index]).val());
	$.ajax({
		url : 'roleManage?type=updata',
		type : 'post',
		data : {
			userId : row.userId,
			roleId:$($("input[name='role']")[index]).val(),
		},
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				$.messager.alert('提示', '更新成功');
				$('#showUsers').datagrid('reload');

			}else{
				$.messager.alert('提示', '更新失败');
			}
		}
	})
}


	$(function() {
		
		$('#showUsers').datagrid({
			url : 'roleManage?type=user',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 15, 20 ],
			rownumbers : true,
			title : '用户权限列表列表',
			singleSelect: true,
			columns : [ [
			{field : 'userId',title : '用户编号',align:'center',	width:$(this).width()*0.1,hidden:true,},
			{field : 'userName',title : '用户名',align:'center',width : $(this).width()*0.1}, 
			{field : 'roleName',title : '权限',align:'center',width:$(this).width()*0.15}, 
			{field : 'subType',title : '修改角色',align:'center',width : $(this).width()*0.25,
				formatter:function(value, row, index){
					var role = '<input name="role" value="请选择">';
					return role;
			}},
			{field:'operate',title:'操作',align:'center',width:$(this).width()*0.2,
				formatter:function(value, row, index){
					var upd = '<a name="updata" class="easyui-linkbutton" onclick="updataRole('+index+')" ></a>';
					return upd;
			}},
			]],
			onLoadSuccess:function(data){  
	       		$("a[name='updata']").linkbutton({text:'保存',iconCls:'icon-remove'});  
	       		console.log(data);
	       		/* 配置权限下拉框 */
	       		$("input[name='role']").combobox({
  	    		 	url:'roleManage?type=role',
  	    		    valueField:'id',    
  	    		    textField:'text',
  	    		});
	       		$('#showUsers').datagrid('fixRowHeight');
			},

		});	
	});
</script>
<body>
	<div>
		<table id="showUsers"></table>
	</div>
</body>
</html>
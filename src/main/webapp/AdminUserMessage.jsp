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
<style type="text/css">
</style>
</head>
<script type="text/javascript">
function deleteSub(index){  
    $('#selectUser').datagrid('selectRow',index);// 关键在这里  
    var row = $('#selectUser').datagrid('getSelected');  
    $.messager.confirm('确认', '确认删除', function(r) {
		/* if(r){
	    	$.ajax({
				url:'DeleteSub?subId='+row.subId,
				type : 'post',
				
			});
			$('#selectUser').datagrid('reload');
		} */
	}); 
   
};

	$(function(){

		$('#selectUser').datagrid({
			url : 'AdminUserMessage',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 15, 20 ],
			rownumbers : true,
		/* 	sortName : 'subid', 
			sortOrder : 'acs',  */
			title : '用户信息列表',
			singleSelect: true,
		/* 	queryParams: {
				'year': $('#Year').textbox('getText'),
				'subType': $('#SubType').textbox('getText'),
				'subLabel': $('#SubLabel').textbox('getText'),
			}, */
			columns : [ [
			{field : 'userId',title : '用户id',align:'center',	width:$(this).width()*0.05},
			{field : 'roleId',title : '角色id',align:'center',width : $(this).width()*0.05}, 
			{field : 'userName',title : '用户名',align:'center',width:$(this).width()*0.15}, 
			{field : 'uPassword',title : '密码',align:'center',width:$(this).width()*0.15}, 
			{field : 'userSecurity',title : '密保',align:'center',width : $(this).width()*0.15},
			{field : 'avator',title : '头像的url',	align:'center',width : $(this).width()*0.15},
			{field:'operate',title:'操作',align:'center',width:$(this).width()*0.1,
				formatter:function(value, row, index){
					var del = '<a name="delete" class="easyui-linkbutton" onclick="deleteSub('+index+')" ></a>';
					var alt = '<a href="#" name="updata" class="easyui-linkbutton" ></a>';
					return del+' '+alt;
			}},
			]],
			onLoadSuccess:function(data){  
	       		$("a[name='delete']").linkbutton({text:'删除',iconCls:'icon-remove'});  
	       		$("a[name='updata']").linkbutton({text:'修改',iconCls:'icon-edit'});
	       		$('#selectUser').datagrid('fixRowHeight');
			},

		}); 

	});
</script>
<body>
	<div>
		<table id="selectUser"></table>
	</div>
</body>
</html>
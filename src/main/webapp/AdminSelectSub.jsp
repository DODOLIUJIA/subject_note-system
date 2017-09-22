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
	$(function() {
		$('#selectsub').datagrid({
			url : 'SelectSub',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 15, 20 ],
			rownumbers : true,
		/* 	sortName : 'subid', 
			sortOrder : 'acs',  */
			title : '用户信息列表',
			columns : [ [
			{field : 'subid',title : '题号',align:'center',	width : 100	},
			{field : 's_label',title : '标签',align:'center',width : 100}, 
			{field : 'subsummary',title : '描述',align:'center',width:$(this).width()*0.15}, 
			{field : 'subtext',title : '题目',align:'center',width:$(this).width()*0.15}, 
			{field : 'subtype',title : '类型',align:'center',width : 100, 
				formatter:function(value, row, index){
					var subtype ;
					if(value == 1) {
						subtype = "简答题";
					}else if(value == 2) {
						subtype = "填空题";
					}else if(value == 3) {
						subtype = "单选题";
					}else if(value == 4){
						subtype = "多选题";
					}else{
						subtype = "其他"
					}
					
					return subtype;
			}},
			{field : 'subaccuracy',title : '准确率',	align:'center',width : 100}, 
			{field : 'subanswer',title : '答案',align:'center',width : 100},
			{field : 'subtime',	title : '时间',	align:'center',width : 100},
			{field:'operate',title:'操作',align:'center',width:$(this).width()*0.1,
				formatter:function(value, row, index){
					var del = '<a href="#" name="remove" class="easyui-linkbutton" ></a>';
					var alt = '<a href="#" name="edit" class="easyui-linkbutton" ></a>';
					return del+' '+alt;
			}},
			]],
			onLoadSuccess:function(data){  
	       		$("a[name='remove']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});  
	       		$("a[name='edit']").linkbutton({text:'修改',plain:true,iconCls:'icon-edit'});  
			},

		});
		/* 年份下拉框 */
		$('#year').combobox({    
		    url:'ShowYearsAndSubtype?type=year',    
		    valueField:'id',    
		    textField:'text'   
		}); 
		/* 题型下拉框 */
		$('#subType').combobox({    
		    url:'ShowYearsAndSubtype?type=subtype',    
		    valueField:'id',    
		    textField:'text'   
		}); 
		
		
	});
</script>
<body>
	<div id="tb" style="padding:5px;height:auto">
		<div>
			<!-- 年份下拉框 -->
			年份:<input id="year" name="dept" value="所有">
			<!-- 题型下拉框 -->
			题型:<input id="subType" name="dept" value="所有">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
			<div style="float:right;"><a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a></div>
		</div>
	</div>
	
	<div>
		<table id="selectsub"></table>
	</div>
</body>
</html>
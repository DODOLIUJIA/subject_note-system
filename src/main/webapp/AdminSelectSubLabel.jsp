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
    $('#selectSubLabel').datagrid('selectRow',index);// 关键在这里  
    var row = $('#selectSubLabel').datagrid('getSelected');  
    $.messager.confirm('确认', '确认删除', function(r) {
		/* if(r){
	    	$.ajax({
				url:'DeleteSub?subId='+row.subId,
				type : 'post',
				
			});
			$('#selectSubLabel').datagrid('reload');
		} */
	}); 
   
};

	$(function(){
		$('#selectSubLabel').datagrid({
			url : 'SelectSubLabel',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 15, 20 ],
			rownumbers : true,
			title : '用户信息列表',
			singleSelect: true,
		/* 	queryParams: {
				'year': $('#Year').textbox('getText'),
				'subType': $('#SubType').textbox('getText'),
				'subLabel': $('#SubLabel').textbox('getText'),
			}, */
			columns : [ [
			{field : 'subLabelId',title : '标签号',align:'center',	width:$(this).width()*0.1},
			{field : 'subLabelName',title : '标签名',align:'center',width : $(this).width()*0.1}, 
			{field : 'subCount',title : '库存量',align:'center',width:$(this).width()*0.1}, 
			{field:'operate',title:'操作',align:'center',width:$(this).width()*0.2,
				formatter:function(value, row, index){
					var del = '<a name="delete" class="easyui-linkbutton" onclick="deleteSub('+index+')" ></a>';
					var alt = '<a href="#" name="updata" href="'+row.subId+'" class="easyui-linkbutton" ></a>';
					return del+' '+alt;
			}},
			
			]],
			toolbar : [{
				iconCls: 'icon-add',
				text:'增加',
				handler: function(){
					$("#dd").window('open');
					}
			}],
			onLoadSuccess:function(data){  
	       		$("a[name='delete']").linkbutton({text:'删除',iconCls:'icon-remove'});  
	       		$("a[name='updata']").linkbutton({text:'修改',iconCls:'icon-edit'});
	       		$('#selectSubLabel').datagrid('fixRowHeight');
			},

		}); 

		//配置添加窗口
		$("#dd").window({
			width:300,
			height:300,
			title:'添加新用户',
			shadow:true,
			modal:true,
			closed:true,
			minimizable:false,
			maximizable:false,
			draggable:false,
			resizable:false,
		});
		
		//配置表单
		$('#ff').form({    
		    url:'#',    

		}); 

		$('#newSubLabelName').textbox({    
		    iconCls:'icon-man', 
		    iconAlign:'left',
		    prompt:'新标签名',
			width:200,
		});
	});
</script>
<body>
	<div>
		<table id="selectSubLabel"></table>
	</div>
	<div id="dd">
		<form id="ff" method="post">
			<div style="margin:20px 48px;">
				<br>
				<br>
				<br>
				<br>
 				<input id="newSubLabelName" name="rname" type="text">
				<br>
				<br>
				<br>
				<br>
				<button id="sub" >提交</button>
				<button id="not">取消</button>
			</div>
		</form>
	</div>
</body>
</html>
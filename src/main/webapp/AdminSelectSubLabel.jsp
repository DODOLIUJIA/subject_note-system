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
	function deleteSubLab(index) {
		$('#selectSubLabel').datagrid('selectRow', index);// 关键在这里  
		var row = $('#selectSubLabel').datagrid('getSelected');
		$.messager.confirm('确认', '确认删除', function(r) {
			if (r) {
				$.ajax({
					url : 'SelectSubLabel?flag=2' + '&' + 's_lid=' + row.s_lid,
					type : 'post',
					/* async : false, */
					dataType : 'json',
					success : function(data) {
						console.log(data.success);
						if (data.success) {
							$.messager.alert('提示', '删除成功');
							$('#selectSubLabel').datagrid('reload');

						}else{
							$.messager.alert('提示', '删除失败');
						}
					}
				});
			}
		});
	};
	
	var s_lid = null;
	function updataSubLab(index) {
		$('#selectSubLabel').datagrid('selectRow', index);// 关键在这里  
		var row = $('#selectSubLabel').datagrid('getSelected');
		s_lid = row.s_lid;
		$('#uln').textbox('setValue', row.s_lname );
		$("#uu").window('open');
		
	};

	$(function() {
		$('#selectSubLabel')
				.datagrid(
						{
							url : 'SelectSubLabel?flag=' + 0,
							pagination : true,
							pageSize : 10,
							pageList : [ 10, 15, 20 ],
							rownumbers : true,
							title : '用户信息列表',
							singleSelect : true,
							columns : [ [
									{
										field : 's_lid',
										title : '标签号',
										align : 'center',
										width : $(this).width() * 0.1
									},
									{
										field : 's_lname',
										title : '标签名',
										align : 'center',
										width : $(this).width() * 0.1
									},
									{
										field : 'subCount',
										title : '库存量',
										align : 'center',
										width : $(this).width() * 0.1
									},
									{
										field : 'operate',
										title : '操作',
										align : 'center',
										width : $(this).width() * 0.2,
										formatter : function(value, row, index) {
											var del = '<a name="delete" class="easyui-linkbutton" onclick="deleteSubLab('+ index +')" ></a>';
											var alt = '<a name="updata" class="easyui-linkbutton" onclick="updataSubLab('+ index +')"></a>';
											return del + ' ' + alt;
										}
									},

							] ],
							toolbar : [ {
								iconCls : 'icon-add',
								text : '增加',
								handler : function() {
									$("#dd").window('open');
								}
							} ],
							onLoadSuccess : function(data) {
								$("a[name='delete']").linkbutton({
									text : '删除',
									iconCls : 'icon-remove'
								});
								$("a[name='updata']").linkbutton({
									text : '修改',
									iconCls : 'icon-edit'
								});
								$('#selectSubLabel').datagrid('fixRowHeight');
							},

						});

		//配置添加窗口
		$("#dd").window({
			width : 300,
			height : 200,
			title : '新标签名',
			shadow : true,
			modal : true,
			closed : true,
			minimizable : false,
			maximizable : false,
			draggable : false,
			resizable : false,
		});
		//配置更新的窗口
		$("#uu").window({
			width : 300,
			height : 200,
			title : '更新标签名',
			shadow : true,
			modal : true,
			closed : true,
			minimizable : false,
			maximizable : false,
			draggable : false,
			resizable : false,
		});

		//配置添加的提交按钮
		$("#sub").linkbutton({
			iconCls : 'icon-save',
			onClick : function() {
			if ($('#newln').val() != '') {
				$.ajax({
					url : 'SelectSubLabel?flag=1'+ '&' + 'newln='+ $('#newln').val(),
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						if (data.success) {
								$.messager.confirm('提示','添加成功',function(r) {
									if (r) {
											$("#dd").window('close');
											$('#uln').textbox('setValue', '' );
											$('#newln').textbox('setValue', '' );
									}
							});
							$('#selectSubLabel').datagrid('reload');
						} else {
							$.messager.alert('提示',	'已存在此标签');
						}
					}
				});
			} else {
				$.messager.alert('提示', '不能提交空值');
			}
		}
	});
		
		//配置更新的提交按钮
		$("#usub").linkbutton({
			iconCls : 'icon-save',
			onClick : function() {
			if ($('#uln').val() != '') {
				$.ajax({
					url : 'SelectSubLabel?flag=3'+ '&' + 'uln='+ $('#uln').val()+ '&' +'s_lid='+s_lid,
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						if (data.success) {
								$.messager.confirm('提示','更新成功',function(r) {
									if (r) {
											$("#uu").window('close');
											$('#uln').textbox('setValue', '' );
											$('#newln').textbox('setValue', '' );
									}
							});
							$('#selectSubLabel').datagrid('reload');
						} else {
							$.messager.alert('提示',	'已存在此标签');
						}
					}
				});
			} else {
				$.messager.alert('提示', '不能提交空值');
			}
		}
	});
		
		//配置取消按钮
		$("#not").linkbutton({
			iconCls : 'icon-cancel',

			onClick : function() {
				$("#dd").window('close');
				$('#uln').textbox('setValue', '' );
				$('#newln').textbox('setValue', '' );
			}
		});
		$("#unot").linkbutton({
			iconCls : 'icon-cancel',

			onClick : function() {
				$("#uu").window('close');
				$('#uln').textbox('setValue', '' );
				$('#newln').textbox('setValue', '' );
			}
		});


		$('#newln').textbox({
			iconCls : 'icon-man',
			iconAlign : 'left',
			prompt : '新标签名',
			width : 250,
		});
		$('#uln').textbox({
			iconCls : 'icon-man',
			iconAlign : 'left',
			prompt : '更改标签名', 
			width : 250,
		});
	});
</script>
<body>
	<div>
		<table id="selectSubLabel"></table>
	</div>
	<div id="dd">
		<br> <br> <br> <br> <input id="newln" name="rname"type="text" value="" /> 
		<br> <br> <br> <br> 
		<a href="#" id="sub">提交</a> <a href="#" id="not">取消</a>
	</div>
	<div id="uu">
		<br> <br> <br> <br> 
			<input id="uln" name="urname" type="text" value="" /> 
		<br> <br> <br> <br> 
		<a href="#" id="usub">提交</a> <a href="#" id="unot">取消</a>
	</div>
</body>
</html>
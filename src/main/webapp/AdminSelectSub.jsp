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

function updateSubject() {
	$.ajax({
		url : 'UpdateSubjectAction',
		type : 'post',
		data : {
			id : $('#sid').val(),
			content : CKEDITOR.instances.editor.getData(),
			sum : $('#summary').val(),
			ans : $('#answer').val(),
			type : $('#type').val(),
			time : $('#Time').val()
		},
		dataType : 'text',
		success : function(data) {
			$('#updataWin').window('close');
			$('#selectsub').datagrid('reload');
			if (data == "success") {
				$.messager.alert('警告', '更新成功', 'info', function() {
					$('#sid').val("");
					$('#summary').val("");
					$('#answer').val("");
					$('#type').val("");
					$('#Time').val("");
					CKEDITOR.instances.editor.setData('');
				});
			} else {
				$.messager.alert('警告', '更新失败', 'info', function() {

				});
			}
		}
	})
}

function deleteSub(index){  
    $('#selectsub').datagrid('selectRow',index);// 关键在这里  
    var row = $('#selectsub').datagrid('getSelected');  
    $.messager.confirm('确认', '确认删除', function(r) {
		if(r){
	    	$.ajax({
				url:'DeleteSub?subId='+row.subId,
				type : 'post',
				
			});
			$('#selectsub').datagrid('reload');
		}
	}); 
};

//更新
function updataSub(index){
	
	$('#selectsub').datagrid('selectRow',index);
    var row = $('#selectsub').datagrid('getSelected');  
	$('#updataWin').window('open');
//	$('#updataWin').load('UpdateSubject.jsp');
	
	$.ajax({
		url : 'GetSubjectBySId',
		type : 'post',
		data : {
			sid : row.subId
		},
		dataType : 'text',
		success : function(data) {
			var sub = eval('(' + data + ')');
			CKEDITOR.instances.editor.setData(sub.subtext);
			$("#sid").val(row.subId);
			$('#summary').val(sub.subsummary);
			$("#answer").val(sub.subanswer);
			$("#type").val(sub.subtype);
			$("#Time").val(sub.subtime);
		}
	})
}

	$(function() {
		$('#updataWin').window({    
		    width:1100,    
		    height:700,    
		    shadow:true,
		}); 
		$('#updataWin').window('center');
		$('#updataWin').window('close');
		
		/* 年份下拉框 */
		$('#Year').combobox({    
		    url:'ShowYearsAndSubtype?type=year',    
		    valueField:'id',    
		    textField:'text',  
		}); 
		/* 题型下拉框 */
		$('#SubType').combobox({    
		    url:'ShowYearsAndSubtype?type=subType',    
		    valueField:'id',    
		    textField:'text',
		}); 
		/* 题型标签下拉框 */
		$('#SubLabel').combobox({    
		    url:'ShowYearsAndSubtype?type=subLabel',    
		    valueField:'id',    
		    textField:'text',
		});
		
		//配置提交按钮
		$("#Search").linkbutton({    
		    onClick:function(){
		    	$('#selectsub').datagrid({
		    		queryParams: {
		    			'year': $('#Year').textbox('getText'),
						'subType': $('#SubType').textbox('getText'),
						'subLabel': $('#SubLabel').textbox('getText'),
		    		}
		    	});
		    }
		}); 
		
		$('#selectsub').datagrid({
			url : 'SelectSub',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 15, 20 ],
			rownumbers : true,
			title : '题目信息列表',
			singleSelect: true,
			queryParams: {
				'year': $('#Year').textbox('getText'),
				'subType': $('#SubType').textbox('getText'),
				'subLabel': $('#SubLabel').textbox('getText'),
			},
			columns : [ [
			{field : 'subId',title : '题号',align:'center',	width:$(this).width()*0.05},
			{field : 's_label',title : '标签',align:'center',width : $(this).width()*0.05}, 
			{field : 'subSummary',title : '描述',align:'center',width:$(this).width()*0.15}, 
			{field : 'subText',title : '题目',align:'center',width:$(this).width()*0.15}, 
			{field : 'subType',title : '类型',align:'center',width : $(this).width()*0.1, 
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
			{field : 'subAccuracy',title : '准确率',	align:'center',width : $(this).width()*0.05}, 
			{field : 'subAnswer',title : '答案',align:'center',width : $(this).width()*0.1},
			{field : 'subTime',	title : '时间',	align:'center',width : $(this).width()*0.1},
			{field:'operate',title:'操作',align:'center',width:$(this).width()*0.2,
				formatter:function(value, row, index){
					var del = '<a name="delete" class="easyui-linkbutton" onclick="deleteSub('+index+')" ></a>';
					var alt = '<a name="updata" class="easyui-linkbutton" onclick="updataSub('+index+')"></a>';
					
					return del+' '+alt;
			}},
			]],
			onLoadSuccess:function(data){  
	       		$("a[name='delete']").linkbutton({text:'删除',iconCls:'icon-remove'});  
	       		$("a[name='updata']").linkbutton({text:'修改',iconCls:'icon-edit'});
	       		$('#selectsub').datagrid('fixRowHeight');
			},

		}); 
	});
</script>
<body>
	<div id="tb" style="padding:5px;height:auto">
		<div>
			<!-- 年份下拉框 -->
			年份:<input id="Year" name="yesr" value="所有">
			<!-- 题型下拉框 -->
			题型:<input id="SubType" name="subType" value="所有">
			<!-- 标签下拉框 -->
			标签:<input id="SubLabel" name="subLabel" value="所有">
			<!-- 查询按钮 -->
			<a id="Search" href="#" class="easyui-linkbutton" iconCls="icon-search">Search</a>
		</div>
	</div>
	
	<div>
		<table id="selectsub"></table>
	</div>
	
	<div id="updataWin">
			<textarea id="editor"></textarea>
	<h3>请在上面输入题目，若该题是选择题，请输入选项</h3>
	<br>
	<div>
		题目ID<input id="sid" type="text" readonly="readonly"
			value="${param.sid}"> 题目描述<input id="summary" type="text">
	</div>
	<br>
	<div>
		题目答案<textarea id="answer" style="width: 600px; height: 250px;" ></textarea>
	</div>
	<br>
	<div>
		题目类型 <select id="type" class="easyui-combobox" name="dept">
			<option value="1">1 简答题</option>
			<option value="2">2 填空题</option>
			<option value="3">3 单选题</option>
			<option value="4">4 多选题</option>
		</select>
	</div>
	<br>
	<div>
		创建年份 <select id="Time" class="easyui-combobox" name="dept" >
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
		</select>
	</div>
	<br>
	<button type="button" class="btn btn-default" onclick="updateSubject()">更新</button>
	</div>
	
</body>
<script>
	CKEDITOR
			.replace(
					'editor',
					{
						language : 'zh-cn',
						allowedContent : true,
						removePlugins : 'elementspath',
						resize_enabled : false,
						height : '300px',
						filebrowserImageUploadUrl : '${basePath}uploadImg?fileType=image&workType=sub'
					});
</script>
</html>
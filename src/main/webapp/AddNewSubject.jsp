<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增题目</title>
<link href="${basePath}statics/themes/bootstrap/easyui.css"
	rel="stylesheet">
<link href="${basePath}statics/themes/icon.css" rel="stylesheet">
<link href="${basePath}statics/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${basePath}statics/js/jquery-1.9.1.js"></script>
<script src="${basePath}statics/js/jquery.easyui.min.js"></script>
<script src="${basePath}statics/js/easyui-lang-zh_CN.js"></script>
<script src="${basePath}statics/ckeditor/ckeditor.js"></script>
<script src="${basePath}statics/js/AddNewSubject.js"></script>

</head>
<script type="text/javascript">
$(function() {
	$('#summary').textbox({
		height : 40,
	})
	$('#answer').textbox({
		height : 40,
	})
	$('#Label').combobox({ 
	    required:true,    
	    multiple:true   
	});  
	$.ajax({
		url : 'GetAllSLabelAction',
		type : 'post',
		dataType : 'text',
		success : function(data){
			da = $.parseJSON(data);
			$("#Label").combobox("loadData", da);
		}
		
	})
})
function isnertSubject() {
	$.ajax({
		url : 'InsertNewSubjectAction',
		type : 'post',
		data : {
			content : CKEDITOR.instances.editor.getData(),
			sum : $('#summary').val(),
			ans : $('#answer').val(),
			type : $("#typee").val(),
			time : $("#Time").val(),
			Lids : $('#Label').combobox('getValues')
		},
		dataType : 'text',
		success : function(data) {
			if(data=="Insert Success"){
				$.messager.alert('警告', '插入成功', 'info', function() {
					$('#summary').val("");
					$('#answer').val("");
					$('#typee').val("");
					$('#Time').val("");
					CKEDITOR.instances.editor.setData('');
				});
			}else{
				$.messager.alert('警告', '插入失败', 'info', function() {
					
				});
			}
		}
	})
}

function test(){
	console.log(111);
	var text=$('#Label').combobox('getValues');
	console.log(text);
}


</script>
<body>
	<textarea id="editor"></textarea>
	<h3>请在上面输入题目，若该题是选择题，请输入选项</h3>
	<br>
	<div>
		题目描述<input id="summary" type="text" style="width: 300px">
	</div><br>
	<div>
		题目答案<input id="answer" type="text" style="width: 300px">
	</div><br>
	<div>
		题目类型 
		<select id="typee" class="" name="dept" style="width: 300px;height : 40px;">
			<option value="1">1 简答题</option>
			<option value="2">2 填空题</option>
			<option value="3">3 单选题</option>
			<option value="4">4 多选题</option>
		</select>&nbsp;
		题目标签 
		<input id="Label"  style="width: 300px;height : 40px;">
	</div><br>
	<div>
		创建年份
		<select id="Time" class="" name="dept" style="width: 300px;height : 40px;">
			<option value="2014">2014</option>
			<option value="2015">2015</option>
			<option value="2016">2016</option>
			<option value="2017">2017</option>
		</select>
	</div><br>
	<button type="button" class="btn btn-default" onclick="isnertSubject()">插入</button>  	

</body>
<script>
	CKEDITOR.replace('editor', {
		language : 'zh-cn',
		allowedContent : true,
		removePlugins : 'elementspath',
		resize_enabled : false,
		height : '300px',
		filebrowserImageUploadUrl : '${basePath}uploadImg?fileType=image&workType=sub'
	});

</script>
</html>
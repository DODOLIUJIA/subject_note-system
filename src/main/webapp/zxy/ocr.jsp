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
<script type="text/javascript" src="${basePath}statics/js/jquery-1.9.1.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${basePath}fileupload" enctype="multipart/form-data"
		method="post">

		上传用户：<input type="text" name="username"><br /> 
		上传文件1：<input type="file" name="image"><br />

		<button type="button" onclick="upload()">确认</button>
	</form>

	上传进度：
	<br>
	<progress></progress>
	<br>
	<p id="progress">0 bytes</p>
	<p id="info"></p>

	<textarea id="ocrResult" style="width: 300px;height: 200px;"></textarea>

</body>
<script>
	var totalSize = 0;
	//绑定所有type=file的元素的onchange事件的处理函数
	$(':file').change(
			function() {
				//var file = $(":file").files[0]; //假设file标签没打开multiple属性，那么只取第一个文件就行了
				var file = this.files[0];
				console.log("file:　"+file);
				name = file.name;
				size = file.size;
				type = file.type;
				url = window.URL.createObjectURL(file); //获取本地文件的url，如果是图片文件，可用于预览图片
				$(this).next().html(
						"文件名：" + name + " 文件类型：" + type + " 文件大小：" + size
								+ " url: " + url);
				totalSize += size;
				$("#info").html("总大小: " + totalSize + "bytes");
			});
	
	function upload() {
		//创建FormData对象，初始化为form表单中的数据。需要添加其他数据可使用formData.append("property", "value");
		var formData = new FormData($('form')[0]);
		//ajax异步上传
		$.ajax({
			url : "${basePath}fileupload",
			type : "POST",
			data : formData,
			xhr : function() { //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数
				myXhr = $.ajaxSettings.xhr();
				if (myXhr.upload) { //检查upload属性是否存在
				//绑定progress事件的回调函数
					myXhr.upload.addEventListener('progress',
							progressHandlingFunction, false);
				}
				return myXhr; //xhr对象返回给jQuery使用
			},
			success : function(result) {
				$("#result").html(result.data);
				$("#ocrResult").val("解析中请稍后");
				$("#ocrResult").attr({readonly : 'true'});
				$.ajax({
					url : "${basePath}AnalyzeImg",
					type : "POST",
					data : {
						"imgPath" : result
						},
					success : function(res){
						$("#ocrResult").attr({readonly : 'false'});
						$("#ocrResult").val("");
						$("#ocrResult").val(res);
					}
				});
				console.log(result);
			},
			contentType : false, //必须false才会自动加上正确的Content-Type
			processData : false
		//必须false才会避开jQuery对 formdata 的默认处理
		});
	}
	
	//上传进度回调函数：
	function progressHandlingFunction(e) {
		if (e.lengthComputable) {
			$('progress').attr({
				value : e.loaded,
				max : e.total
			}); //更新数据到进度条
			var percent = e.loaded / e.total * 100;
			$('#progress').html(
					e.loaded + "/" + e.total + " bytes. " + percent.toFixed(2)
							+ "%");
		}
	}
</script>
</html>
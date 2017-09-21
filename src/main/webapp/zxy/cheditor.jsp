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
<script src="${basePath}statics/ckeditor/ckeditor.js"></script>
<title>Insert title here</title>
</head>
<body>
	<textarea id="editor"></textarea>
	<button onclick="setContent()">Set Content</button>
	<button onclick="getContent()">Get Content</button>
	<script>
		//初始化textarea标签为ckeditor
		CKEDITOR.replace('editor', {
	        language: 'zh-cn',
	        allowedContent: true,
	        removePlugins: 'elementspath',
	        resize_enabled: false,
	        height: '300px',
	        filebrowserImageUploadUrl: '${basePath}uploadImg?fileType=image&workType=note' // *** [2] 不同页面上传的地址有可能不一样哦 ***
	    });
		
		// 设置 CKEditor 中的内容
		function setContent() {
			// editor1 是上面的 id
			CKEDITOR.instances.editor.setData('<b>This is for test</b>');
		}
		
		// 获取 CKEditor 中的内容
		function getContent() {
			var content = CKEDITOR.instances.editor.getData();
			alert(content);
		}
	</script>

</body>
</html>
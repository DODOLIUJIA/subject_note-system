<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>详细题目</title>
<link href="${basePath}statics/themes/bootstrap/easyui.css"
	rel="stylesheet">
<link href="${basePath}statics/themes/icon.css" rel="stylesheet">
<link href="${basePath}statics/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${basePath}statics/js/jquery-1.9.1.js"></script>
<script src="${basePath}statics/js/jquery.easyui.min.js"></script>
<script src="${basePath}statics/js/easyui-lang-zh_CN.js"></script>
<script src="${basePath}statics/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/js/SubDetail.js"></script>
</head>
<style>
* {
	margin: 0;
	padding: 0;
}

#barrage {
	margin: auto;
	margin-top: 50px;
	position: absolute;
	left: 200px;
	top: 14px;
	width: 1100px;
	height: 600px;
	filter: alpha(Opacity = 60);
	-moz-opacity: 0.6;
	opacity: 0.6;
}

#barrage div {
	width: 50%;
	height: 20px;
	line-height: 20px;
	position: absolute;
}

#btn {
	margin: auto;
	margin-top: 30px;
	height: 50px;
	width: 300px;
}

#text {
	font-size: 20px;
	height: 30px;
	width: 350px;
	border-radius: 4px;
	border: 1px solid #c8cccf;
	color: #6a6f77;
}

#submit {
	padding: 7px;
	font-size: 14px;
	height: 30px;
	border-radius: 4px;
	border: 1px solid #c8cccf;
}
</style>

<script type="text/javascript">
	var host = "172.18.23.77"; //172.18.23.77

	function handleOnMessage() {
		console.log("收到消息");
	}

	//打开webSocket连接
	var webSocket = new WebSocket("ws://"+host+":8080/sub_note/DanMuServer");

	//-----------websocket事件注册--------------
	//websocket连接事件
	webSocket.onopen = function(evt) {
		console.log("websocket已连接");
	};

	//websocket关闭事件
	webSocket.onclose = function(evt) {
		webSocket.close();
	};

	//websocket消息响应事件
	webSocket.onmessage = function(msg) {
		console.log("123: " + msg.data);
		create(msg.data);
		$("#peopleNums").val();
	}

	//-------------end-----------------------
</script>


<body>
	
	<div id="barrage"></div>
	<!-- 使用户知道这是个导航栏 -->
	<nav class="navbar navbar-default">
	<div class="container=fluid">
		<div class="navbar-header">
			<ul class="nav navbar-nav navbar-left">
				<li><a href="" style="color: gray">上一题</a></li>
				<li><a href="" style="color: gray">下一题</a></li>
			</ul>
			<!-- 导航栏左边 -->
			<a class="navbar-brand" style="color: gray"></a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<!-- 导航栏右边 -->
			<ul class="nav navbar-nav navbar-right">
				<li><a href="" style="color: gray">退出</a></li>
			</ul>
		</div>
	</div>
	</nav>
	
	<div id="peopleNums">当前在线人数：   </div>
	
	<div>
		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
			<div class="col-md-4 col-sm-4 col-xs-4">
				<div>
					<input type="text" id="id" style="display: none"
						value="${param.sid}">
					<h2 id="subtype"></h2>
					<br>
					<h4 id="subcontent"></h4>
					<br>
				</div>
			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div>
		<br> <br>
		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
			<div class="col-md-6 col-sm-6 col-xs-6">
				<botton id="showAnswer" type="button" class="btn btn-default"
					style="margin-left: 40%;z-index: 10;" onclick="show()">显示答案以及评论</botton>

				<!-- 发送弹幕的input 和 按钮 -->
				<div id="btn">
					<input type="text" id="text" placeholder = "在这里输入弹幕 回车发送"></input>
				</div>

			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div>
	</div>
	<div id="anwerAndcomment" name="anwerAndcomment" style="display: none">
		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
			<div class="col-md-6 col-sm-6 col-xs-6">
				<h4 id="answer">答案是B</h4>
			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div>
		<div class="row">
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
			<div class="col-md-6 col-sm-6 col-xs-6">
				<textarea cols="60" id="msg" name="msg" rows="4"
					placeholder="请自觉遵守互联网相关的政策法规，严禁发布色情、暴力、反动的言论。"
					style="display: inline"></textarea>
				<div style="display: inline">
					<a class="btn btn-default" id="addComment" role="button"
						onclick="addNewComment()">发表评论</a> <a class="btn btn-default"
						role="button">添加笔记</a>
				</div>
			</div>
			<div class="col-md-3 col-sm-3 col-xs-3"></div>
		</div>

		<div id="comment"></div>
	</div>
	<br>
</body>
<script>
	var timer = null;
	var current = [];//存储当前输入框的内容  
	var newarr = [];//存储每个弹幕距左边框的距离  
	var num = new Array();//数组，用来存储划分每个块的序号  

	$(function() {
		for (var i = 0; i < $("#barrage").get(0).offsetHeight / 20 - 1; i++) {
			num.splice(i, 0, i);//将整个显示框划分成多个块，并对每个块进行标号  
		}

		clearInterval(timer);//清除定时器  

		timer = setInterval(move, 30);//开启定时器 

		$("#text").on('keypress', function(event) {
				
			if (event.keyCode == 13)
			{
				if($("#text").val() != "")
					sendMsg();
			}
		});
	});

	function create(msg) {//创建一个弹幕  
		var node = document.createElement("div");//创建一个div元素，用来存储弹幕的信息  
		node.innerHTML = msg;
		var t = random(0, num.length - 1);
		node.style.top = num[t] * 20 + "px";//从划分的块中随机选中一块。  
		Delete(num[t]);//删除已被选中的块   
		node.style.left = ($("#barrage").get(0).offsetWidth - 100) + "px";
		node.style.color = "#" + randomColor();//随机颜色  
		node.style.fontSize = random(20 , 30) + "px";//弹幕字体大小
		$("#barrage").append(node);//插入子节点  
	}

	function move() {
		var arr = $("#barrage").children("div");//获取所有的弹幕  
		for (var i = 0; i < arr.length; i++) {

			//将每个弹幕距左边边框的距离分别存储在newarr数组中  
			newarr.push(arr[i].offsetLeft);
			arr[i].style.left = newarr[i] + "px";//更新距离  
			newarr[i] = newarr[i] - 3;//每次减少2px  

			if (newarr[i] < 0) {
				arr[i].remove();
				newarr.splice(i, 1);//在newarr中删除这个div  
			}
		}
	}

	function sendMsg() {//输入款发送弹幕  
		current[current.length] = $("#text").val();
		webSocket.send($("#text").val());
		$("#text").val("");

	}

	function Delete(m) {//从预选块中删除已被选择的块  
		for (var i = 0; i < num.length; i++) {
			if (num[i] == m) {
				num.splice(i, 1);
			}
		}
	}

	function randomColor() {//随机颜色  
		var color = Math.ceil(Math.random() * 16777215).toString(16);

		while (color.length < 6) {
			color = "0" + color;
		}
		return color;
	}

	function random(m, n) {//随机在m、n之间的整数  
		return Math.round(Math.random() * (n - m)) + m;
	}
</script>
</html>
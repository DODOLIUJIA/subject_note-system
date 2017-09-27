<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>千层在线学习</title>
<link rel="stylesheet" href="${basePath}statics/css/login.css">
<script src="${basePath}statics/js/login.js"></script>
<script src="${basePath}statics/js/jquery-1.7.2.min.js"></script>
</head>
<script>
$(function() {
	//登录时验证用户是否存在
	$("#login").click(function(){
		var uname = $("#l_uname").val();
		var password = $("#l_password").val();
		$.ajax({
			  //请求路径
			  url:'login',
			  //请求方式
			  type:'post',
			  //请求所带的数据 
			  data:{'uname':uname,'password':password},
			  //请求过后返回的类型
			  dataType:'json',
			  //成功请求之后回调的函数
			  success:function(data){
				if(data.result == 1){
					window.location.href='index.jsp';
				}
				else {
					$("#ncue").text("");
				}
			  }  
		});
	});
	
	//注册时验证用户名是否已存在
	$("#r_uname").blur(function(){
		var uname = $("#r_uname").val();
		var reg =  /^[0-9a-zA-Z]*$/g;  //判断字符串是否为数字和字母组合     //判断正整数 /^[1-9]+[0-9]*]*$/    
		if (uname == '') {
			$("#ncue").text("请输入账号");
		} 
		else if (!reg.test(uname))  {  
			$("#ncue").text("账号为字母或数字");
		}
		else {
			$("#ncue").text("");	
			$.ajax({
				  //请求路径
				  url:'checkUname',
				  //请求方式
				  type:'post',
				  //请求所带的数据 
				  data:{'uname':uname},
				  //请求过后返回的类型
				  dataType:'json',
				  //成功请求之后回调的函数
				  success:function(data){
					if(data.result == 1){
						$("#ncue").text("账号已存在");
					}
					else {
						$("#ncue").text("");
					}
				  }
			});
		}
	});
	//注册时验证密码格式
	$("#r_password").blur(function(){
		var password = $("#r_password").val();
		var reg =  /^[0-9a-zA-Z]*$/g;  //判断字符串是否为数字和字母组合     //判断正整数 /^[1-9]+[0-9]*]*$/    
		if (password == '') {
			$("#pcue").text("请输入密码");
		} 
		else if (!reg.test(password))  {  
			$("#pcue").text("密码为字母或数字");
		}
		else {
			$("#pcue").text("");
		}
	});
	
	//注册时验证用户两次输入密码是否相同
	$("#register").click(function(){
		var uname = $("#r_uname").val();
		var password = $("#r_password").val();
		var confirm = $("#r_confirm").val();
		if(uname == '') {
			$("#ncue").text("请输入账号");
		}
		 else if(password == ''){
			$("#pcue").text("请输入密码");
		} 
		else if(confirm == '') {
			$("#ccue").text("请确认密码");
		}
		else if(password != confirm){
			$("#pcue").text("两次输入密码不同");
		} 
		else {
			$("#pcue").text("");
			$("#ccue").text("");
			$.ajax({
				  //请求路径
				  url:'register',
				  //请求方式
				  type:'post',
				  //请求所带的数据 
				  data:{'uname':uname,'password':password},
				  //请求过后返回的类型
				  dataType:'json',
				  //成功请求之后回调的函数
				  success:function(data){
					if(data.result == 1){
						cambiar_login()
					}
					else {
						$(":input").val('');
						alert("注册失败");
					}
				  }  
			});
		}
	});
	
})
</script>
<body>
	<div class="cotn_principal">
		<div class="cont_centrar">
			<div class="cont_login">
				<div class="cont_info_log_sign_up">
					<div class="col_md_login">
						<div class="cont_ba_opcitiy">
							<h2>登 录</h2>
							<p>
								Layer online learning platform<br>qianceng
							</p>
							<button class="btn_login" onClick="cambiar_login()">登 录</button>
						</div>
					</div>
					<div class="col_md_sign_up">
						<div class="cont_ba_opcitiy">
							<h2>注 册</h2>
							<p>
								Layer online learning platform<br>qianceng
							</p>
							<button class="btn_sign_up" onClick="cambiar_sign_up()">注册</button>
						</div>
					</div>
				</div>
				<div class="cont_back_info">
					<div class="cont_img_back_grey">
						<img src="<%=basePath%>/statics/zxlImgs/po.jpg" alt="" />
					</div>
				</div>
				<div class="cont_forms">
					<div class="cont_img_back_">
						<img src="<%=basePath%>/statics/zxlImgs/po.jpg" alt="" />
					</div>
					<div class="cont_form_login">
						<a href="#" onClick="ocultar_login_sign_up()"><i
							class="material-icons">&#xE5C4;</i></a>
						<h2>登 录</h2>
						<input type="text" id="l_uname" placeholder="请输入账号" />
						<input type="password" id="l_password" placeholder="请输入密码"/>
						<button id="login" class="btn_login" onClick="cambiar_login()" style="margin-top:25px;">登 录</button>
					</div>
					<div class="cont_form_sign_up">
						<a href="#" onClick="ocultar_login_sign_up()">
						<i class="material-icons">&#xE5C4;</i></a>
						<h2>注 册</h2>
						<input type="text" id="r_uname" placeholder="请输入账号" /> 
						<p id="ncue" style="color:red;font-size:8px;"></p>
						<input type="password" id="r_password" placeholder="请输入密码" /> 
						<p id="pcue" style="color:red;font-size:8px;"></p>
						<input type="password" id="r_confirm" placeholder="请确认密码" />
						<p id="ccue" style="color:red;font-size:8px;"></p>
						<button id="register" class="btn_sign_up" onClick="cambiar_sign_up()" style="margin-top:25px;">注 册</button>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>

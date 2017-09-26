<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath", basePath);
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/statics/css/default.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/statics/themes/icon.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src='${pageContext.request.contextPath}/statics/js/outlook2.js'>
	
</script>
<script type="text/javascript">
	var _menus = {
		"menus" : [ {
			"menuid" : "1",
			"menuname" : "用户管理",
			"menus" : [ {
				"menuid" : "2",
				"menuname" : "删除用户",
				"icon" : "icon-add",
				"url" : "#"
			}, {
				"menuid" : "13",
				"menuname" : "用户管理",
				"icon" : "icon-add",
				"url" : "#"
			}, ]
		}, {
			"menuid" : "8",
			"menuname" : "评论管理",
			"menus" : [ {
				"menuid" : "21",
				"menuname" : "员工列表",
				"icon" : "icon-add",
				"url" : "#"
			}, {
				"menuid" : "22",
				"menuname" : "视频监控",
				"icon" : "icon-add",
				"url" : "#"
			} ]
		}, {
			"menuid" : "56",
			"menuname" : "功能管理",
			"menus" : [ {
				"menuid" : "31",
				"menuname" : "添加部门",
				"icon" : "icon-add",
				"url" : "#"
			}, {
				"menuid" : "32",
				"menuname" : "部门列表",
				"icon" : "icon-add",
				"url" : "#"
			} ]
		}, {
			"menuid" : "28",
			"menuname" : "题目管理",
			"menus" : [ {
				"menuid" : "41",
				"menuname" : "查询题目",
				"icon" : "icon-add",
				"url" : "#"
			}, {
				"menuid" : "42",
				"menuname" : "新增题目",
				"icon" : "icon-add",
				"url" : "AddNewSubject.jsp"
			}, {
				"menuid" : "43",
				"menuname" : "更新题目测试",
				"icon" : "icon-add",
				"url" : "UpdateSubject.jsp?sid=1"
			}]
		}, {
			"menuid" : "54",
			"menuname" : "题目标签管理",
			"menus" : [ {
				"menuid" : "55",
				"menuname" : "收支分类",
				"icon" : "icon-add",
				"url" : "#"
			}, {
				"menuid" : "56",
				"menuname" : "报表统计",
				"icon" : "icon-add",
				"url" : "#"
			}, ]
		}, {
			"menuid" : "39",
			"menuname" : "题目标签管理",
			"menus" : [ {
				"menuid" : "51",
				"menuname" : "商品分类",
				"icon" : "icon-add",
				"url" : "#"
			}, {
				"menuid" : "52",
				"menuname" : "商品列表",
				"icon" : "icon-add",
				"url" : "#"
			}, ]
		} ]
	};
	//设置登录窗口
	function openPwd() {
		$('#w').window({
			title : '修改密码',
			width : 500,
			modal : true,
			shadow : true,
			closed : true,
			height : 300,
			resizable : false
		});
	}
	//关闭登录窗口
	function closePwd() {
		$('#w').window('close');
	}
	//修改密码
	function serverLogin() {
		var $newpass = $('#txtNewPass');
		var $rePass = $('#txtRePass');

		if ($newpass.val() == '') {
			msgShow('提示', '请输入密码！', 'warning');
			return false;
		}
		if ($rePass.val() == '') {
			msgShow('提示', '请在一次输入密码！', 'warning');
			return false;
		}

		if ($newpass.val() != $rePass.val()) {
			msgShow('提示', '两次密码不一至！请重新输入', 'warning');
			return false;
		}

		$.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(
				msg) {
			msgShow('提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
			$newpass.val('');
			$rePass.val('');
			close();
		})
	}

	$(function() {
		openPwd();
		$('#editpass').click(function() {
			$('#w').window('open');
		});
		$('#btnEp').click(function() {
			serverLogin();
		})
		$('#btnCancel').click(function() {
			closePwd();
		})
		$('#loginOut').click(function() {
			$.messager.confirm('提示', '您确定要退出本次登录吗?', function(r) {
				if (r) {
					//location.href = '/ajax/loginout.ashx';
				}
			});
		})
	});
</script>


</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="<%=basePath%>/statics/zxlImgs/noscript.gif"
				alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="true" border="false"
		style="overflow: hidden; height: 30px;
        background: url(${bathPath}statics/zxlImgs/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
		<span style="float: right; padding-right: 20px;" class="head">欢迎
			${username} <a href="#" id="editpass">修改密码</a> <a href="#"
			id="loginOut">安全退出</a>
		</span> <span style="padding-left: 10px; font-size: 16px;"><img
			src="<%=basePath%>/statics/zxlImgs/blocks.gif" width="20" height="20" align="absmiddle" />
			千层在线学习后台</span>

	</div>
	<div region="south" split="true"
		style="height: 30px; background: #D2E0F2;">
		<div class="footer">
			<span style="font-family: arial;">Copyright &copy;2017 www.qianceng.com</span>
		</div>
	</div>
	<div region="west" hide="true" split="true" title="导航菜单"
		style="width: 180px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->
		</div>
	</div>
	<div id="mainPanle" region="center"
		style="background: #eee; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用" style="padding: 20px; overflow: hidden; color: red;">
				<h1>欢迎使用千层在线学习后台管理系统！</h1>
			</div>
		</div>
	</div>
	<!--修改密码窗口-->
	<div id="w" class="easyui-window" title="修改密码" collapsible="false"
		minimizable="false" maximizable="false" icon="icon-save"
		style="width: 300px; height: 150px; padding: 5px; background: #fafafa;">
		<div class="easyui-layout" fit="true">
			<div region="center" border="false"
				style="padding: 10px; background: #fff; border: 1px solid #ccc;">
				<table cellpadding=3>
					<tr>
						<td>新密码：</td>
						<td><input id="txtNewPass" type="password" class="txt01" /></td>
					</tr>
					<tr>
						<td>确认密码：</td>
						<td><input id="txtRePass" type="password" class="txt01" /></td>
					</tr>
				</table>
			</div>
			<div region="south" border="false"
				style="text-align: right; height: 30px; line-height: 30px;">
				<a id="btnEp" class="easyui-linkbutton" icon="icon-ok"

					href="javascript:void(0)">确定</a> 
					<a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel"

					href="javascript:void(0)">取消</a>
			</div>
		</div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
</body>
</html>
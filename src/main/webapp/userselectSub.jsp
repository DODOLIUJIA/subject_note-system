<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath", basePath);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>

<!-- 引入js -->
<link rel="stylesheet"
	href="${basePath}statics/bootstrap/css/bootstrap.min.css">
<!-- 引入jq -->
<script type="text/javascript"
	src="${basePath}statics/js/jquery-1.9.1.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js 

"></script>

</script>
<!-- 字体设置 -->
<style>
.accordion-heading {
	color: #0059b2;
	font-size: 15px;
	letter-spacing: 1px;
	text-align: center;
	margin: 45px 0 10px;
}
.nav-sidebar{
cursor:pointer;
}

</style>

</head>
<script type="text/javascript">
//题目显示栏高度
var subHeight = 3000;
//下拉次数
var loadtimms = 1;
//是否还有题目的标志
var flage = true;
//设置题目标签点击事件		
window.onscroll = function() {
	var a = document.documentElement.scrollTop == 0 ? document.body.clientHeight
			: document.documentElement.clientHeight;
	var b = document.documentElement.scrollTop == 0 ? document.body.scrollTop
			: document.documentElement.scrollTop;
	var c = document.documentElement.scrollTop == 0 ? document.body.scrollHeight
			: document.documentElement.scrollHeight; 
	if (a + b + 1 >= c&&flage==true) {
		$.ajax({
			url : 'selectAllSubject?loadtimms=' + loadtimms,
			type : 'POST',
			async : false,
			dataType : 'json',
			success : function(data) {
				console.log(data.Subs.length);
				if (data.Subs.length==4){
					for (var i = 0; i < data.Subs.length; i++) {
						$('#AllSubs').append("<li class='span4'><div class='thumbnail'><div class='caption'><div style='float: right;'>"
					            +data.Subs[i].subTime+
					            "</div><h4>"
					            +data.Subs[i].subText+
					            "</h4><p>"
					            +data.Subs[i].subSummary+
					            "</p><p><a class='btn btn-primary' href='SubDetail.jsp?sid="+data.Subs[i].subId+"'>浏览</a></p></div></div>");
					}
				} else if(data.Subs.length > 0){
					for (var i = 0; i < data.Subs.length; i++) {
						$('#AllSubs').append("<li class='span4'><div class='thumbnail'><div class='caption'><div style='float: right;'>"
					            +data.Subs[i].subTime+
					            "</div><h4>"
					            +data.Subs[i].subText+
					            "</h4><p>"
					            +data.Subs[i].subSummary+
					            "</p><p><a class='btn btn-primary' href='SubDetail.jsp?sid="+data.Subs[i].subId+"'>浏览</a></p></div></div>");
						}
					$('#MainPullUp').text('已全部加载');
					flage = false;
				}else{
					$('#MainPullUp').text('已全部加载');
					flage = false;
				}
			}
		});
		loadtimms = loadtimms + 1;
	}
};
	$(function() {
		//题目标签
		var sublabel ="";
		//出题时间
		var subCrateTime=0;
		$("[name='sublabel']").click(function(){
			if(subCrateTime!=0){
				subCrateTime=0;
			}
			    sublabel = $(this).html();
				var link = $(this).attr('target');
				$.ajax({
					url:'getSubMsg',
					type:'post',
					data:{'sublabel':sublabel,'subCrateTime':subCrateTime},
					success:function(data){
							$("#center").load(link);
							subCrateTime=0;
					}
				})
			
		});
		//设置出题时间点击事件
		$("[name='subCrateTime']").click(function(){
		        subCrateTime = $(this).html();
			var link = $(this).attr('target');
			$.ajax({
				url:'getSubMsg',
				type:'post',
				data:{'sublabel':sublabel,'subCrateTime':subCrateTime},
				success:function(data){
						$("#center").load(link);
				}
			})
		})

		//展示题目标签和出题时间
		$.ajax({
			url:'showLabelAndTime',
			type:'post',
			success:function(){
			}
		})
		//展示所有题目
	$.ajax({
		url : 'selectAllSubject?loadtimms=' + 0,
		type : 'POST',
		async : false,
		dataType : 'json',
		success : function(data) {
			$("#MainPullUp").hide();
			if (data.Subs.length ==4) {
				$("#MainPullUp").show();
				for (var i = 0; i < data.Subs.length; i++) {
					$('#AllSubs').append("<li class='span4'><div class='thumbnail'><div class='caption'><div style='float: right;'>"
					            +data.Subs[i].subTime+
					            "</div><h4>"
					            +data.Subs[i].subText+
					            "</h4><p>"
					            +data.Subs[i].subSummary+
					            "</p><p><a class='btn btn-primary' href='SubDetail.jsp?sid="+data.Subs[i].subId+"'>浏览</a></p></div></div>");
				}
			}else if(data.Subs.length >0){
				$("#MainPullUp").show();
				for (var i = 0; i < data.Subs.length; i++) {
					$('#AllSubs').append("<li class='span4'><div class='thumbnail'><div class='caption'><div style='float: right;'>"
					            +data.Subs[i].subTime+
					            "</div><h4>"
					            +data.Subs[i].subText+
					            "</h4><p>"
					            +data.Subs[i].subSummary+
					            "</p><p><a class='btn btn-primary' href='SubDetail.jsp?sid="+data.Subs[i].subId+"'>浏览</a></p></div></div>");
				}
				flage = false;
				$('#MainPullUp').text('已全部加载');
			}else{
				flage = false;
				$("#MainPullUp").show();
				$('#MainPullUp').text('已全部加载');
			}
		}
	});	
});
</script>


<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div id="main" class="col-sm-3 col-md-2 sidebar">

				<div class="accordion" id="subChoose">
					<!-- 试题类型 -->
					<div class="accordion-group " id="subType">
						<div class="accordion-heading">
							<ul class="nav nav-sidebar">
								<li class="active "><a class="accordion-toggle"
									data-toggle="collapse" data-parent="#subChoose"
									href="#subTypeChoose">试题类型</a></li>
							</ul>
						</div>
						<div id="subTypeChoose" class="accordion-body collapse in">
							<div class="accordion-inner">
								<ul class="nav nav-sidebar">
								<c:forEach var="label" items="${labels}" >
								<li><a name="sublabel" target="browseSub.jsp">${label.s_lname}</a></li>
								</c:forEach>
								</ul>
							</div>
						</div>
					</div>

					<!-- 出题时间 -->
					<div class="accordion-group" id="subCrateTimes">
						<div class="accordion-heading">
							<ul class="nav nav-sidebar">
								<li class="active"><a class="accordion-toggle"
									data-toggle="collapse" data-parent="#subChoose"
									href="#subCrateTime">出题时间</a></li>
							</ul>
						</div>
						<div id="subCrateTime" class="accordion-body collapse in">
							<div class="accordion-inner">
								<ul class="nav nav-sidebar">
								<c:forEach var="time" items="${times}" >
								<li><a name="subCrateTime" target="browseSub.jsp">${time.subTime}</a></li>
								</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>


			</div>

			<div id="center" class="col-sm-9 col-md-10 sidebar">
				<ul id="AllSubs" class="thumbnails"></ul>
				 <div id="MainPullUp" style="position: absolute;left: 50%"><img src="statics/images/loading.gif" ></div>
			</div>
  
		</div>
	</div>
   
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<!-- 引入ck -->
<script src="${basePath}statics/ckeditor/ckeditor.js">
	
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
</style>

</head>
<script type="text/javascript">
	$(function() {
		//判断题目类型是否被点击 是为1 反之为0
		var STcheck = 0;
		//判断出题时间是否被点击 是为1 反之为0
		var SCTcheck = 0;
		$("li").click(function() {
			var link = $(this).find('a').attr('target');
			console.log(link);
			if ($(this).find('a').attr('name') == 'sublabel') {
				var subtype = $(this).find('a').html();
				STcheck = 1;
				console.log('STcheck=' + STcheck);
				$.ajax({
					url : 'selectsub',
					type : 'post',
					data : {
						'sublabel' : sublabel,
						'STcheck' : STcheck,
						'SCTcheck' : SCTcheck
					},
					dataType : 'json',
					success : function() {

						$('#center').load(link);
					}
				})

			} else if($(this).find('a').attr('name') == 'subCrateTime'){
				var subCrateTimes = $(this).find('a').html();
				SCTcheck = 1;
				$.ajax({
					url : 'selectsub',
					type : 'post',
					data : {
						'subCrateTime' : subCrateTime,
						'STcheck' : STcheck,
						'SCTcheck' : SCTcheck
					},
					dataType : 'json',
					success : function() {
						$('#center').load(link);
					}
				})
			}

		})

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
									<li><a name="sublabel" target="browseSub.jsp">Java</a></li>
									<li><a name="sublabel" target="browseSub.jsp">c/c++</a></li>
									<li><a name="sublabel" target="browseSub.jsp">c#</a></li>
									<li><a name="sublabel" target="browseSub.jsp">python</a></li>
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
									<li><a name="subCrateTime" target="browseSub.jsp">2010</a></li>
									<li><a name="subCrateTime" target="browseSub.jsp">2011</a></li>
									<li><a name="subCrateTime" target="browseSub.jsp">2012</a></li>
									<li><a name="subCrateTime" target="browseSub.jsp">2013</a></li>
									<li><a name="subCrateTime" target="browseSub.jsp">2017</a></li>
								</ul>
							</div>
						</div>
					</div>
				</div>


			</div>

			<div id="center" class="col-sm-9 col-md-10 sidebar">
				<!-- 测试 -->
				<ul class="thumbnails">
					<li class="span4">
						<div class="thumbnail">
							<img alt="300x200" src="img/people.jpg" />
							<div class="caption">
								<h3>冯诺尔曼结构</h3>
								<p>
									也称普林斯顿结构，是一种将程序指令存储器和数据存储器合并在一起的存储器结构。程序指令存储地址和数据存储地址指向同一个存储器的不同物理位置。
								</p>
								<p>
									<a class="btn btn-primary" href="#">浏览</a> <a class="btn"
										href="#">分享</a>
								</p>
							</div>
						</div>
					</li>
					<li class="span4">
						<div class="thumbnail">
							<img alt="300x200" src="img/city.jpg" />
							<div class="caption">
								<h3>哈佛结构</h3>
								<p>
									哈佛结构是一种将程序指令存储和数据存储分开的存储器结构，它的主要特点是将程序和数据存储在不同的存储空间中，进行独立编址。</p>
								<p>
									<a class="btn btn-primary" href="#">浏览</a> <a class="btn"
										href="#">分享</a>
								</p>
							</div>
						</div>
					</li>
					<li class="span4">
						<div class="thumbnail">
							<img alt="300x200" src="img/sports.jpg" />
							<div class="caption">
								<h3>改进型哈佛结构</h3>
								<p>
									改进型的哈佛结构具有一条独立的地址总线和一条独立的数据总线，两条总线由程序存储器和数据存储器分时复用，使结构更紧凑。</p>
								<p>
									<a class="btn btn-primary" href="#">浏览</a> <a class="btn"
										href="#">分享</a>
								</p>
							</div>
						</div>
					</li>
				</ul>
			</div>


		</div>
	</div>
	</div>
</body>
</html>
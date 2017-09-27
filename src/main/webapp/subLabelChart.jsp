<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	session.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${basePath}statics/js/Chart.js"></script>
<script type="text/javascript" src="${basePath}statics/js/jquery-1.9.1.js"></script>
<title>Insert title here</title>
</head>
<style type="text/css">
#chart {
	width: 50vw;
	height: 28vh;
	position: relative;
}
</style>
<body>
	<div id="chart">
		<canvas id="canvas"></canvas>
	</div>
	
</body>
<script>
	var chartData = {
			type : 'bar',
			data : {
				labels : [""],
				datasets : [{
					label : '题目标签统计',
					data : [""],
					backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
							'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)',
							'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)',
							'rgba(255, 159, 64, 0.2)' ],
					borderColor : [ 'rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)',
							'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)',
							'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)' ],
					borderWidth : 1
				}]
			},
			
			options : {
				scales : {
					yAxes : [{
						ticks : {
							beginAtZero : true
						}
					}]
				}
			}
		};
	
	$(function(){
		$.ajax({
			url:"${basePath}subLabelChart",
			type:"post",
			dataType : "json",
			success : function(data){
				console.log("data.labelNamesJsonArr: "+data[1]);
				chartData.data.labels = data[1];
				chartData.data.datasets[0].data = data[0];
				var ctx = document.getElementById("canvas").getContext('2d');
				
				var myChart = new Chart(ctx, chartData);
			}
			
		})
	});
	
	
	
</script>
</html>
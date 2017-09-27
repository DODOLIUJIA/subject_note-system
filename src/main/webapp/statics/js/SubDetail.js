$(function() {
	var sid = $("#id").val();
	console.log("获得的题目id为：" + sid);
	$.ajax({
		url : 'GetSubjectBySId',
		type : 'post',
		data : {
			sid : sid
		},
		dataType : 'text',
		success : function(data) {
			var sub = eval('(' + data + ')');
			if(sub.subtype==1){
				$("#subtype").html("简答题");
			}else if(sub.subtype==2){
				$("#subtype").html("填空题");
			}else if(sub.subtype==3){
				$("#subtype").html("单选题");
			}else{
				$("#subtype").html("多选题");
			}
			$("#subcontent").html(sub.subtext);
			$("#answer").html("答案:"+sub.subanswer);
		}
	});
});

function show() {
	console.log("点击了显示答案及评论按钮");
	var sid = $("#id").val();
	$.ajax({
		url : 'GetAllCommentBySID',
		type : 'post',
		data : {
			sid : sid
		},
		dataType : 'json',
		success : function(data){
			var jsondatas = eval(data);
			console.log(jsondatas);
			var page=1;
			for (var i = (page-1)*5; jsondatas.length-(page-1)*5<5 ? i < jsondatas.length-(page-1)*5 : i < 5 ; i++) {
				var div = $("<div class="+"row"+">" +
						"<div class='col-md-3 col-sm-3 col-xs-3'>" +
						"</div>" +
						"<div class='col-md-6 col-sm-6 col-xs-6'>" +
						"<div>" +
						"<div name="+"username"+">用户名："+jsondatas[i].userName+"</div>" +
						"<div class="+"talk"+">"+jsondatas[i].comText+"<br></div>" +
						"<div class="+"down"+">" +
						"<a id='like"+jsondatas[i].comID+"' onclick='addLikeNums("+jsondatas[i].comID+")'>赞("+jsondatas[i].likeNums+")</a><a id='unlike"+jsondatas[i].comID+"' onclick='addUnLikeNums("+jsondatas[i].comID+")'>踩("+jsondatas[i].unLikeNums+")</a>" +
						"</div></div></div><div class='col-md-3 col-sm-3 col-xs-3'></div></div><br> ");
				$("#comment").append(div);
			}
			// 接下来添加分页控件
			var divpage=$("<div class='row'><div class='col-md-3 col-sm-3 col-xs-3'></div>" +
					"<div class='col-md-6 col-sm-6 col-xs-6'><ul class='pagination' id='ul'></ul></div>" +
					"<div class='col-md-3 col-sm-3 col-xs-3'></div></div>")
			$("#anwerAndcomment").append(divpage);
			var pageCount = jsondatas.length;// 总数
			var pagingCount = pageCount% 5==0?pageCount/5 : parseInt(pageCount/5)+1;
			console.log("一共有几页："+pagingCount);
			var ul=$("#ul");
			ul.empty();
			ul.append("<li value='1'><a>首页</a></li>");
			ul.append("<li class='disabled'><a>上一页</a></li>");
			for (var i = 1 - 3; i <= 1 + 3; i++) {
				if (i < 1 || i > pagingCount) {
					continue;
				}
				if (i == 1) {
					ul.append("<li class='active'><a>" + i + "</a></li>");
				} else {
					ul.append("<li value='" + i + "'><a>" + i + "</a><li>");
				}
			}
			if(1==pageCount){
				ul.append("<li value='2'><a>下一页</a></li>");
			}else{
				ul.append("<li value='" + (page + 1) + "'><a>下一页</a></li>");
			}
			ul.append("<li value=" + pagingCount + "><a>尾页</a></li>");
		}
	})
	document.getElementById('anwerAndcomment').style.display = 'block';
	document.getElementById('showAnswer').style.display = 'none';
	
	$(document).on('click',"li", function() {
		var page = $(this).val();
		console.log("点击的page是"+page);
		var sid = $("#id").val();
		$.ajax({
			url : 'GetAllCommentBySID',
			type : 'post',
			data : {
				sid : sid
			},
			dataType : 'json',
			success : function(data){
				var jsondatas = eval(data);
				console.log(jsondatas);
				$("#comment").html("");
				for (var i = (page-1)*5; jsondatas.length-(page-1)*5<5 ? i < (page - 1) * 5+jsondatas.length-(page-1)*5 : i < (page - 1) * 5+5 ; i++) {
					var div = $("<div class="+"row"+">" +
							"<div class='col-md-3 col-sm-3 col-xs-3'>" +
							"</div>" +
							"<div class='col-md-6 col-sm-6 col-xs-6'>" +
							"<div>" +
							"<div name="+"username"+">用户名："+jsondatas[i].userName+"</div>" +
							"<div class="+"talk"+">"+jsondatas[i].comText+"<br></div>" +
							"<div class="+"down"+">" +
							"<a id='like"+jsondatas[i].comID+"' onclick='addLikeNums("+jsondatas[i].comID+")'>赞("+jsondatas[i].likeNums+")</a><a id='unlike"+jsondatas[i].comID+"' onclick='addUnLikeNums("+jsondatas[i].comID+")'>踩("+jsondatas[i].unLikeNums+")</a>" +
							"</div></div></div><div class='col-md-3 col-sm-3 col-xs-3'></div></div><br> ");
					$("#comment").append(div);
				}
				var pageCount = jsondatas.length;// 总数
				var pagingCount = pageCount% 5==0?pageCount/5 : parseInt(pageCount/5)+1;
				console.log("一共有几页："+pagingCount);
				var ul=$("#ul");
				ul.html("");
				ul.append("<li value='1'><a>首页</a></li>");
				if(page == 1){
					ul.append("<li class='disabled'><a>上一页</a></li>");
				}else{
					ul.append("<li value='" + (page - 1) + "'><a>上一页</a></li>");
				}
				for (var i = page - 3; i <= page + 3; i++) {
					if (i < 1 || i > pagingCount) {
						continue;
					}
					if (i == page) {
						ul.append("<li class='active'><a>" + i + "</a></li>");
					} else {
						ul.append("<li value='" + i + "'><a>" + i + "</a></li>");
					}
				}
				if(page == pagingCount){
					ul.append("<li class='disabled'><a>下一页</a></li>");
				}else{
					ul.append("<li value='" + (page + 1) + "'><a>下一页</a></li>");
				}
				ul.append("<li value='"+pagingCount+"'><a>尾页</a></li>");
			}
		});
	});
}

function addNewComment(){
	var comText=$("#msg").val();
	var sid = $("#id").val();
	$.ajax({
		url : 'InsertNewCommentAction',
		type : 'post',
		data : {
			comText : comText,
			sid : sid
		},
		dataType : 'text',
		success : function(data) {
			if(data="success")
				alert("评论插入成功");
			else
				alert("评论插入失败");
		}
	});
}

function addLikeNums(cid){
	$.ajax({
		url:'AddLikeOrUnLikeNumsAction',
		type:'post',
		data:{
			comID:cid,
			type:1
		},
		dataType : 'text',
		success:function(data){
			var json = eval('(' + data + ')');
			if(json.result="success"){
				$("#like"+cid+"").text("赞("+json.likeNums+")");
				$("#like"+cid+"").attr('onclick', '');
			}else{
				alert("点赞失败");
			}
		}
	})
}

function addUnLikeNums(cid){
	$.ajax({
		url:'AddLikeOrUnLikeNumsAction',
		type:'post',
		data:{
			comID:cid,
			type:2
		},
		dataType : 'text',
		success:function(data){
			var json = eval('(' + data + ')');
			if(json.result="success"){
				$("#unlike"+cid+"").text("踩("+json.unLikeNums+")");
				$("#unlike"+cid+"").attr('onclick', '');
			}else{
				alert("踩失败");
			}
		}
	})
}

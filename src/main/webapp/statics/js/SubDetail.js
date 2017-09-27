$(function() {
	var sid = ${param.sid};
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
//			CKEDITOR.instances.editor.setData(sub.subtext);
//			$('#summary').val(sub.subsummary);
//			$("#answer").val(sub.subanswer);
//			$("#type").val(sub.subtype);
//			$("#Time").val(sub.subtime);
			if(sub.subtype==1){
				$("#subtype").val("简答题");
			}else if(sub.subtype==2){
				$("#subtype").val("填空题");
			}else if(sub.subtype==3){
				$("#subtype").val("单选题");
			}else{
				$("#subtype").val("多选题");
			}
		}
	})

})

function show() {
	console.log("点击了显示答案及评论按钮");
	document.getElementById('anwerAndcomment').style.display = 'block';
	document.getElementById('showAnswer').style.display = 'none';
}
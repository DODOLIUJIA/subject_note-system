$(function() {
	$('#summary').textbox({
		height : 40,
	})
	$('#answer').textbox({
		height : 40,
	})
	$('#type').textbox({
		height : 40,
	})
	$('#Label').combobox({ 
	    required:true,    
	    multiple:true   
	});  
	$.ajax({
		url : 'GetAllSLabelAction',
		type : 'post',
		dataType : 'text',
		success : function(data){
			da = $.parseJSON(data);
			$("#Label").combobox("loadData", da);
		}
		
	})
})
function isnertSubject() {
	$.ajax({
		url : 'InsertNewSubjectAction',
		type : 'post',
		data : {
			content : CKEDITOR.instances.editor.getData(),
			sum : $('#summary').val(),
			ans : $('#answer').val(),
			type : $('#type').val(),
			time : $('#Time').val(),
			Lids : $('#Label').combobox('getValues')
		},
		dataType : 'text',
		success : function(data) {
			if(data=="Insert Success"){
				$.messager.alert('警告', '插入成功', 'info', function() {
					$('#summary').val("");
					$('#answer').val("");
					$('#type').val("");
					$('#Time').val("");
					CKEDITOR.instances.editor.setData('');
				});
			}else{
				$.messager.alert('警告', '插入失败', 'info', function() {
					
				});
			}
		}
	})
}

function test(){
	console.log(111);
	var text=$('#Label').combobox('getValues');
	console.log(text);
}

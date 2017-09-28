$(function(){
	$('#pp').datagrid({
		url : 'GetCommentListAction',
		pageSize : 15,
		pageList : [ 15, 20 ],
		rownumbers : true,
		toolbar : '#toolbar',
		pagination : 'true',
		rownumbers : 'true',
		title : '评论信息列表',
		columns : [ [ 
		    {field : 'ck',title : 'ck',checkbox : true,width : 100}, 
		    {field : 'comID',title : '评论ID',width : 100,align : 'left'},
		    {field : 'subID',title : '题目ID',width : 100,align : 'left'},
		    {field : 'userID',title : '评论用户ID',width : 100,align : 'left'},
		    {field : 'comText',title : '评论内容',width : 300,align : 'center'}, 
		    {field : 'likeNums',title : '点赞次数',width : 100,align : 'right'},
		    {field : 'unLikeNums',title : '踩次数',width : 100,align : 'right'}, 
		    {field : 'create_DateTime',title : '创建时间',width : 100,align : 'right'} 
		] ],
		toolbar : [ {
			iconCls : 'icon-remove',
			text : '移除',
			handler : function() {
				$.messager.confirm('确认对话框', '确认删除？', function(r){
	    			if (r){
	    				console.log("执行删除操作");
	    				var rows = $('#pp').datagrid('getSelections');
	    				var comIDs = [];
	    				for (var i = 0; i < rows.length; i++) {
	    					comIDs.push(rows[i].comID);
	    				}
	    				console.log(comIDs);
	    				$.ajax({
	    					url : 'DeleteCommentServlet',
	    					type : 'post',
	    					dataType : 'text',
	    					data : {
	    						"selectedIDs" : comIDs
	    					},
	    					success : function(data) {
	    						if(data=="success"){
	    							$.messager.alert('警告', '更新成功', 'info', function() {
		    							$('#pp').datagrid('reload');
		    						});
	    						}else{
	    							$.messager.alert('警告', '更新失败', 'info', function() {
		    							$('#pp').datagrid('reload');
		    						});
	    						}
	    					}
	    				});
	    			}
	    		});
			}
		}]
	})
})
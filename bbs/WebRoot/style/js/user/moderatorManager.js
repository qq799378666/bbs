// 数据加载
$(function() {
	$("#dg").datagrid({
		title : '用户列表',
		url : "j_User_getPageVO",
		singleSelect : true,
		pagination : true,
		striped : true,
		pageList : [ 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
		pageSize : 20,
		fit : true,
		fitColumns : true,
		toolbar : "#stb",
		remoteSort : false,
		onDblClickCell : modify,
		columns : [ [ {
			field : 'id',
			title : 'ID',
			hidden : true
		}, {
			field : 'user',
			title : '用户',
			width : 200
		}, {
			field : 'username',
			title : '用户名',
			width : 50,
		}, 
		{
			field : 'sex',
			title : '性别',
			width : 60
		},

		{
			field : 'email',
			title : '邮箱',
			width : 60
		},

		{
			field : 'sex',
			title : '性别',
			width : 60
		},
		{
			field : 'score',
			title : '积分',
			width : 60
		},
		{
			field : 'money',
			title : '金币',
			width : 60
		},
		{
			field : 'rank',
			title : '等级',
			width : 60
		},
		{
			field : 'operator',
			title : '任命版主',
			align : "center",
			width : 50,
			formatter : function(value, row, index) {
				return '<a class="l-btn l-btn-plain"  href="javascript:modify();" ><span class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty icon-edit">&nbsp;</span></span></span></a>&nbsp;';
			}
		}
		
		] ],
		loadFilter : function(result) {
			if (!result.success) {
				$.messager.alert("系统提示", result.msg, "error");
				return;
			}
			
			for (var i = 0; i < result.rows.length; i++) {
				result.rows[i].user = "<a target='_blank' href='User?id="+result.rows[i].id+"'>"+result.rows[i].nickname+"</a>";
			}
			return result;
		},
	});

	

	$("#dlg").dialog({
		width : 360,
		height : 220,
		top : 50,
		closed : true,
		modal : true,
		collapsible : true,
		onOpen : loadCombobox,
		onClose : function(){
		},
		buttons : [ {
			id : "ok",
			text : '确认',
			iconCls : "icon-ok",
			handler : close
		}]
	});
	

	
	// 搜索监听
	$("#form").bind("submit", function(event) {
			query();
			return false;
	});
	

	$("#query").linkbutton({
		iconCls : "icon-search",
		plain : true
	});

	$("#reload").linkbutton({
		iconCls : "icon-reload",
		plain : true,
	});

});

// 重置对话框内容
function resetValue() {
	$("#fm").form("reset");
}

// 关闭对话框
function close() {
	resetValue();
	$("#dlg").dialog("close");
}

function loadCombobox() {
	var row = $("#dg").datagrid('getSelected');
	$("#module").combotree({
		url : "j_Module_getAllToTreeByUser?user.id="+row.id,
		width : 205,
		panelHeight : 150,
		editable : false,
		required : true,
		multiple : true,
		animate : true,
		onBeforeCheck:function(node,checked){
			var row = $("#dg").datagrid('getSelected');
			if(checked){
				$.post("j_Module_addModerator",{"user.id":row.id,'id':node.id},function(result){
					if(result.success){
					}else{
						alert(result.msg);
					}
				},"json");
				
			}
			else{
				$.post("j_Module_delModerator",{"user.id":row.id,'id':node.id},function(result){
					if(result.success){
					}else{
						alert(result.msg);
					}
				},"json");
				
			}
	
		},
		validType : 'combox',
		onShowPanel : function() {
			$(this).combotree("reload");
		},
		loadFilter:function(result){
		/*	for (var i = 0; i < result.length; i++) {
				result[i].text = result[i].rname;
			}
			return result;*/
			return result;
		}
	}).combotree("setValue", "请选择...")
}

// 弹出任职窗口
var modify = function(){
	var row = $("#dg").datagrid('getSelected');
	if (row == null) {
		$.messager.alert("系统提示", "请选择其中<font color=red>1</font>条数据！");
		return;
	}
	index = $("#dg").datagrid("getRowIndex", row);
	$("#fm").form("load", row);
	$("#dlg").dialog({
		title : "任职",
		closed : false,
		iconCls : "icon-edit",
	});
}




//删除客户信息
function del() {
	var row = $("#dg").datagrid('getSelected');
	if (row == null) {
		$.messager.alert("系统提示", "请选择需要删除的用户！");
		return;
	}
	index = $("#dg").datagrid("getRowIndex", row);
	$.messager.confirm("系统提示", "您确定删除这条数据？", function(r) {
		if (r) {
			$.post("j_User_del", {
				id : row.id
			}, function(result) {
				if (result.success) {
					$("#dg").datagrid("deleteRow", index);
					show("删除用户成功");
				} else {
					$.messager.alert("系统提示", result.msg, "error");
				}
			}, "json");
		}
	});
}

// 查询
function query() {
	var data = serializeObject($("#form"));
	$("#dg").datagrid("load", data);
}
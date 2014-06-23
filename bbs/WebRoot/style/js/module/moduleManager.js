// 数据加载
$(function() {
	$("#dg").datagrid({
		title : '版块列表',
		url : "j_Module_getPageVO",
		singleSelect : true,
		pagination : true,
		striped : true,
		pageList : [ 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
		pageSize : 20,
		fit : true,
		fitColumns : true,
		toolbar : "#stb",
		remoteSort : false,
		columns : [ [ {
			field : 'id',
			title : 'ID',
			hidden : true
		}, {
			field : 'rname',
			title : '名称',
			width : 100
		}, {
			field : 'intro',
			title : '简介',
			width : 50,
		}, 
		{
			field : 'newestArticle',
			title : '最新文章',
			width : 100
		},{
			field:'moderators',
			title:'版主',
			width:100,
		}
		] ],
		loadFilter : function(result) {
			if (!result.success) {
				$.messager.alert("系统提示", result.msg, "error");
				return;
			}
			
			for (var i = 0; i < result.rows.length; i++) {
				if(result.rows[i].newestArticleId>0){
					result.rows[i].newestArticle = "<a target='_blank' href='Article?id="+result.rows[i].newestArticleId+"'>"+result.rows[i].newestArticleTitle+"</a>";
				}else{
					result.rows[i].newestArticle = "无";
				}
				var ms = "";
				for(var j=0;j<result.rows[i].moderators.length;j++){
					var u = result.rows[i].moderators[j];
					ms += "<a href='User?id='"+u.id+">"+u.nickname+"</a>"+(j==result.rows[i].moderators.length-1?'':',')
				}
				
				result.rows[i].moderators = ms;
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
		buttons : [ {
			id : "ok",
			text : '保存',
			iconCls : "icon-ok",
			handler : save
		}, {
			text : '关闭',
			iconCls : "icon-cancel",
			handler : close
		} ]
	});
	
	
	
	
	
	// 回车键监听
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

//添加信息
function add() {
	index = -1;
	$("#dlg").dialog({
		title : "添加版块",
		closed : false,
		iconCls : "icon-add",
	});
	url = "j_Module_add";
}

//编辑
function modify() {
	var selectedRows = $("#dg").datagrid('getSelections');
	if (selectedRows.length != 1) {
		$.messager.alert("系统提示", "请选择其中<font color=red>1</font>条数据！");
		return;
	}
	var row = selectedRows[0];
	index = $("#dg").datagrid('getRowIndex', row);
	$("#dlg").dialog({
		title : "编辑货物信息",
		closed : false,
		iconCls : "icon-edit",
	});
	$("#fm").form("load", row);
	url = "j_Module_update";
}


//保存提交信息
function save() {
	if ($("#fm").form("validate")) {
		loading();
		var data = serializeObject($("#fm"));
		$.post(url, $("#fm").serialize(), function(result) {
			if (result.success) {
				if (index >= 0) {
					$("#dg").datagrid("updateRow", {
						index : index,
						row : data
					});
				} else {
					data["id"] = result.id;
					$("#dg").datagrid("appendRow", data);
				}
				close();
				show("保存成功");
			} else {
				$.messager.alert("系统提示", result.msg, "error");
				ok();
				return;
			}
		}, "json");
	}
}

//关闭对话框
function close() {
	resetValue();
	$("#dlg").dialog("close");
}

//删除
function del() {
	var row = $("#dg").datagrid('getSelected');
	if (row == null) {
		$.messager.alert("系统提示", "请选择需要删除的版块！");
		return;
	}
	index = $("#dg").datagrid("getRowIndex", row);
	$.messager.confirm("系统提示", "您确定删除该板块吗？", function(r) {
		if (r) {
			$.post("j_Module_del", {
				id : row.id
			}, function(result) {
				if (result.success) {
					$("#dg").datagrid("deleteRow", index);
					show("删除版块成功");
				} else {
					$.messager.alert("系统提示", result.msg, "error");
				}
			}, "json");
		}
	});
}
//重置对话框内容
function resetValue() {
	$("#fm").form("reset");
}

// 查询
function query() {
	var data = serializeObject($("#form"));
	$("#dg").datagrid("load", data);
}
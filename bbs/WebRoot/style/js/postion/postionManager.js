// 数据加载
$(function() {
	$("#dg").datagrid({
		title : '职位管理',
		url : "j_Postion_getPageVO",
		singleSelect : true,
		pagination : true,
		striped : true,
		pageList : [ 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
		pageSize : 20,
		fit : true,
		fitColumns : true,
		toolbar : "#tb",
		height : 'auto',
		onDblClickCell : modify,
		columns : [ [ {
			field : 'id',
			title : 'ID',
			hidden : true
		}, {
			field : 'rname',
			title : '职位名称',
			width : 200
		}, {
			field : 'operator',
			title : '查看详情',
			align : "center",
			width : 20,
			formatter : function(value, row, index) {
				return '<a class="l-btn l-btn-plain"  href="javascript:modify()" ><span class="l-btn-left"><span class="l-btn-text"><span class="l-btn-empty icon-edit">&nbsp;</span></span></span></a>&nbsp;';
			}
		} ] ],
		loadFilter : function(result) {
			if (!result.success) {
				$.messager.alert("系统提示", result.msg, "error");
				return;
			}
			return result;
		},
	})

	$("#query").linkbutton({
		iconCls : "icon-search",
		plain : true
	});
	// 回车键监听
	$("body").bind("submit", function(event) {
		query();
		return false;
	});

	$("#dlg").dialog({
		width : 360,
		height : 220,
		top : 50,
		closed : true,
		modal : true,
		collapsible : true,
		onOpen : loadCombobox,
		onClose : resetValue,
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

	$("#rname").validatebox({
		required : true
	});

});

function loadCombobox() {
	// 获取权限菜单
	$("#role").combotree({
		url : "j_Menu_getAllMenu",
		width : 205,
		panelHeight : 350,
		editable : false,
		required : true,
		multiple : true,
		animate : true,
		parentField : "pid",
		validType : 'combox',
		onShowPanel : function() {
			$(this).combotree("reload");
		}
	}).combotree("setValue", "请选择...");
	;

}

var url;
var index;
// 添加职位信息
function add() {
	index = -1;
	$("#dlg").dialog({
		title : "添加职位",
		closed : false,
		iconCls : "icon-add",
	});
	url = "j_Postion_add";
}

// 编辑职位信息
function modify() {
	var row = $("#dg").datagrid('getSelected');
	if (row == null) {
		$.messager.alert("系统提示", "请选择其中<font color=red>1</font>条数据！");
		return;
	}
	index = $("#dg").datagrid("getRowIndex", row);
	$("#fm").form("load", row);
	$("#dlg").dialog({
		title : "编辑职位信息",
		closed : false,
		iconCls : "icon-edit",
	});
	$.post("j_Menu_getMenusByPostion", {
		"postion.id" : row.id
	}, function(result) {
		var ids = [];
		for (var i = 0; i < result.length; i++) {
			if (result[i].pid) {
				ids.push(result[i].id);
			}
		}
		$("#role").combotree("setValues", ids);
	}, "json");
	url = "j_Postion_update?id=" + row.id;// /////////////////////
}

// 保存提交信息
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

// 删除职位
function del() {
	var row = $("#dg").datagrid('getSelected');
	if (row == null) {
		$.messager.alert("系统提示", "请选择要删除的职位！");
		return;
	}
	index = $("#dg").datagrid("getRowIndex", row);
	$.messager.confirm("系统提示", "您确定删除职位:<strong style='color:red'>" + row.rname + "</strong>吗?", function(r) {
		if (r) {
			$.post("j_Postion_del", {
				id : row.id
			}, function(result) {
				if (result.success) {
					$("#dg").datagrid("deleteRow", index);
					show("删除职位成功");
				} else {
					$.messager.alert("系统提示", result.msg, "error");
				}
			}, "json");
		}
	});
}

// 重置对话框内容
function resetValue() {
	$("#fm").form("reset");
}

// 关闭对话框
function close() {
	resetValue();
	$("#dlg").dialog("close");
}
// 查询
function query() {
	var data = serializeObject($("#form"));
	$("#dg").datagrid("load", data);
}

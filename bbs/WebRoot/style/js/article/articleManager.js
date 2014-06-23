// 数据加载
$(function() {
	
	$("#module").combobox({
		url:'j_Module_getCurUserAuthorityTree',
		valueField:'id',    
	    textField:'text',
	    panelHeight:80,
	    editable:false,
	    autoSizeColumn:true,
	    onChange:query,
	    onLoadSuccess:function(){
	    	var r = $("#module").combobox("getData")[0].id
	    	$("#module").combobox("setValue", r);
	    }
	});
	
	
	$("#startDate").datetimebox({
		editable : false
	})
	
	$("#endDate").datetimebox({
		editable : false
	})

	$("#query").linkbutton({
		iconCls : "icon-search",
		plain : true
	});

	$("#reload").linkbutton({
		iconCls : "icon-reload",
		plain : true,
	});
	
	$("#form").bind("submit", function(event) {
		query();
		return false;
	});
	
	
	
	$("#dg").datagrid({
		title : '帖子列表',
		singleSelect : true,
		url:"j_Article_getPageVOToSmart",
		pagination : true,
		striped : true,
		pageList : [ 5, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 ],
		pageSize : 20,
		onBeforeLoad:function(node,p){
			if(node['module.id']==undefined)
				return false;//防止第一次加载数据方法待改进
		},
		fit : true,
		fitColumns : true,
		toolbar : "#stb",
		remoteSort : false,
		columns : [ [ {
			field : 'id',
			title : 'ID',
			hidden : true
		}, {
			field : 'article',
			title : '标题',
			width : 200
		}, {
			field : 'user',
			title : '姓名',
			width : 50,
		}, 
		{
			field :'good',
			title : '精华',
			width: 30
		},
		
		{
			field : 'time',
			title : '发布时间',
			width : 60
		} ] ],
		loadFilter : function(result) {
			if (!result.success) {
				console.log(result);
				$.messager.alert("系统提示", result.msg, "error");
				return;
			}
			
			for (var i = 0; i < result.rows.length; i++) {
				if (result.rows[i].time != null) {
					result.rows[i].time = result.rows[i].time.replace("T", " ");
				}
				result.rows[i].user = "<a target='_blank' href='User?id="+result.rows[i].userId+"'>"+result.rows[i].userNickname+"</a>";
				result.rows[i].article = "<a target='_blank' href='Article?id="+result.rows[i].id+"'>"+result.rows[i].title+"</a>";
			}
			return result;
		},
	});

});

//删除客户信息
function changeState() {
	var row = $("#dg").datagrid('getSelected');
	if (row == null) {
		$.messager.alert("系统提示", "请选择需要更改状态的数据！");
		return;
	}
	index = $("#dg").datagrid("getRowIndex", row);
	var msg = "";
	if(row.good){
		msg = "确定将该贴取消精华吗?";
	}else{
		msg = "确定要将该贴设置为精华帖吗?";
	}
	
	$.messager.confirm("系统提示",msg, function(r) {
		if (r) {
			$.post("j_Article_changeState", {
				id : row.id,
				good:!row.good
			}, function(result) {
				if (result.success) {
					row.good = !row.good;
					$("#dg").datagrid("updateRow", {
						index : index,
						row : row
					});
					if(row.good){
						show("设置精华帖成功");
					}else{
						show("取消精华成功");
					}
				} else {
					$.messager.alert("系统提示", result.msg, "error");
				}
			}, "json");
		}
	});
}
//删除
function del() {
	var row = $("#dg").datagrid('getSelected');
	if (row == null) {
		$.messager.alert("系统提示", "请选择要删除掉数据！");
		return;
	}
	index = $("#dg").datagrid("getRowIndex", row);
	$.messager.confirm("系统提示", "您确定删除这条数据？", function(r) {
		if (r) {
			$.post("j_Article_del", {
				id : row.id
			}, function(result) {
				if (result.success) {
					$("#dg").datagrid("deleteRow", index);
					show("删除帖子成功");
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
	if(data.good==""){
		data.good = undefined;
	}
//	$("#dg").data("load","j_Article_getPageVOToSmart").datagrid({onBeforeLoad:function(node,param){param=data}}); 
	$("#dg").datagrid("load", data);
}
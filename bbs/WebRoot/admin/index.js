$(function(){
	$("#tree").tree({
		url : "j_Menu_getMenusByCurUser",
		parentField : "pid",
		onClick : openTab,
		animate : true,
		// 绑定tree右键菜单
		onContextMenu : function(e, node) {
			e.preventDefault();
			$(this).tree('select', node.target);
			$('#treeMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
			var expand = $('#treeMenu').menu('findItem', '展开');
			var collapse = $('#treeMenu').menu('findItem', '收缩');
			var open = $('#treeMenu').menu('findItem', '新标签打开');
			if (node.pid) {//如果该节点有父节点
				$('#treeMenu').menu('enableItem', open.target);
				$('#treeMenu').menu('disableItem', [ expand.target, collapse.target ]);
				
			} else {
				$('#treeMenu').menu('disableItem', open.target);
				$('#treeMenu').menu('enableItem', [ expand.target, collapse.target ]);
			}
		},
	});

	$("#tabs").tabs({
		fit : true,
		border : false,
		tools : "#mainTab_tools",
		// 绑定tabs的右键菜单
		onContextMenu : function(e, title) {
			e.preventDefault();
			$('#tabsMenu').menu('show', {
				left : e.pageX,
				top : e.pageY
			}).data("tabTitle", title);
			var close = $('#tabsMenu').menu('findItem', '关闭');
			if (title == "首页") {
				// 禁用菜单
				$('#tabsMenu').menu('disableItem', close.target);
			} else {
				// 启用菜单
				$('#tabsMenu').menu('enableItem', close.target);
			}
		},
	});	
	
});

function openTab(node) {
	url = node.attributes.url;
	var tab = $("#tabs");
	if (tab.tabs('exists', node.text)) {
		tab.tabs('select', node.text);
	} else if (node.attributes.url) {
		var content = "<iframe frameborder='0' scrolling='no' style='width:99.8%;height:99.3%;padding:1px;' src=" + url + "></iframe>";
		tab.tabs("add", {
			title : node.text,
			iconCls : node.iconCls,
			closable : true,
			content : content
		});
	}
}

//展开当前树
function expand() {
	var node = $('#tree').tree('getSelected');
	$('#tree').tree('expand', node.target);
}

// 收缩当前树
function collapse() {
	var node = $('#tree').tree('getSelected');
	$('#tree').tree('collapse', node.target);
}

// 展开所有树
function expandAll() {
	$('#tree').tree('expandAll');
}

// 收缩所有树
function collapseAll() {
	$('#tree').tree('collapseAll');
}

// 新窗口打开
function newWin() {
	var node = $('#tree').tree('getSelected');
	window.open(node.attributes.url);
}



// 实例化menu的onClick事件
$("#tabsMenu").menu({
	onClick : function(item) {
		CloseTab(this, item.name);
	}
});

//几个关闭事件的实现
function CloseTab(menu, name) {
	var curTabTitle = $(menu).data("tabTitle");
	var tabs = $("#tabs");
	if (name == "close") {
		tabs.tabs("close", curTabTitle);
	}

	var allTabs = tabs.tabs("tabs");
	var closeTabsTitle = [];

	$.each(allTabs, function() {
		var opt = $(this).panel("options");
		if (opt.closable && opt.title != curTabTitle && name == "Other") {
			closeTabsTitle.push(opt.title);
		} else if (opt.closable && name == "All") {
			closeTabsTitle.push(opt.title);
		}
	});

	for (var i = 0; i < closeTabsTitle.length; i++) {
		tabs.tabs("close", closeTabsTitle[i]);
	}
}








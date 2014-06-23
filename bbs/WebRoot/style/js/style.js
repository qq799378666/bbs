// 将form表单中的值序列化成对象
var serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

var lessThenIE8 = function() {
	var UA = navigator.userAgent, isIE = UA.indexOf('MSIE') > -1, v = isIE ? /\d+/.exec(UA.split(';')[1]) : 'no ie';
	return v < 8;
}();

if (lessThenIE8) {
	$.messager.confirm("系统提示", "IE浏览器版本过低，将影响功能，请升级！", function(r) {
		if (r) {
			window.open("http://windows.microsoft.com/zh-CN/internet-explorer/products/ie/home");
		} else {
			window.open("http://windows.microsoft.com/zh-CN/internet-explorer/products/ie/home");
		}
	});
}
$(function() {
	// 回车键监听
	$("#fm").bind("keyup", function(event) {
		if (event.keyCode == 13) {
			save();
		}
	});
});

function show(msg){
	$.messager.show({
		title : "系统提示",
		msg : "<div style='font-size: 30px;text-align: center;'>"+msg+"</div>",
		showType : 'show'
	});
}
// 信息
function saveSuccess() {
	alert("弃用");
	$.messager.show({
		title : "系统提示",
		msg : "<div style='font-size: 30px;text-align: center;'>保存成功</div>",
		showType : 'show'
	});
}

function delSuccess() {
	alert("弃用");
	$.messager.show({
		title : "系统提示",
		msg : "<div style='font-size: 30px;text-align: center;'>删除成功</div>",
		showType : 'show'
	});
}

function loading() {
	$("#ok").linkbutton({
		iconCls : "icon-loading"
	});
}

function ok() {
	$("#ok").linkbutton({
		iconCls : "icon-ok"
	});
}

// 获取系统当前时间
function getDate() {
	var date = new Date();
	var str = date.getFullYear() + "-";
	str += date.getMonth() + 1 + "-";
	str += date.getDate() + " ";
	str += date.getHours() + ":";
	str += date.getMinutes() + ":";
	str += date.getSeconds();
	return str;
}

$(window).resize(function() {
	setTimeout(domresize, 0);
});

// 改变表格宽高
function domresize() {
	$("#dg").datagrid("resize", {
		height : $("body").height(),
		width : $("body").width()
	});
}
// 刷新
function reload() {
	location.reload();
}

// 跳转
function login() {
	location.replace("Login.jsp");
}

// 防止超出浏览器边界
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;



// easyui-tree扩展
$.fn.tree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

// easyui-combotree扩展
$.fn.combotree.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().tree.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

// easyui-treegrid扩展
$.fn.treegrid.defaults.loadFilter = function(data, parent) {
	var opt = $(this).data().treegrid.options;
	var idFiled, textFiled, parentField;
	if (opt.parentField) {
		idFiled = opt.idFiled || 'id';
		textFiled = opt.textFiled || 'text';
		parentField = opt.parentField;
		var i, l, treeData = [], tmpMap = [];
		for (i = 0, l = data.length; i < l; i++) {
			tmpMap[data[i][idFiled]] = data[i];
		}
		for (i = 0, l = data.length; i < l; i++) {
			if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
				if (!tmpMap[data[i][parentField]]['children'])
					tmpMap[data[i][parentField]]['children'] = [];
				data[i]['text'] = data[i][textFiled];
				tmpMap[data[i][parentField]]['children'].push(data[i]);
			} else {
				data[i]['text'] = data[i][textFiled];
				treeData.push(data[i]);
			}
		}
		return treeData;
	}
	return data;
};

// easyui-datetimeobx扩展，清空时间框
$.fn.datetimebox.defaults.buttons.splice(3, 0, {
	text : "清空",
	handler : function(target) {
		$(target).datetimebox("clear");
	}
});

// tab关闭时回收内存
$.fn.panel.defaults.onBeforeDestroy = function() {
	var frame = $('iframe', this);
	try {
		if (frame.length > 0) {
			frame[0].contentWindow.document.write('');
			frame[0].contentWindow.close();
			frame.remove();
			if ($.browser.msie) {
				CollectGarbage();
			}
		} else {
			$(this).find('.combo-f').each(function() {
				var panel = $(this).data().combo.panel;
				panel.panel('destroy');
			});
		}
	} catch (e) {
	}
};

// 自定义验证
$.extend($.fn.validatebox.defaults.rules, {
	minLength : { // 判断最小长度
		validator : function(value, param) {
			return value.length >= param[0];
		},
		message : '最少输入 {0} 个字符'
	},
	length : {
		validator : function(value, param) {
			var len = $.trim(value).length;
			return len >= param[0] && len <= param[1];
		},
		message : "内容长度介于{0}和{1}之间"
	},
	phone : {// 验证电话号码
		validator : function(value) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
		},
		message : '电话号码格式不正确（如020-88888888或0710-8888888）'
	},
	mobile : {// 验证手机号码
		validator : function(value) {
			return /^(13|15|18)\d{9}$/i.test(value);
		},
		message : '手机号码格式不正确（如：134XXXXXXXX）'
	},
	phoneOrMobile : {// 验证手机或电话
		validator : function(value) {
			return /^(13|15|18)\d{9}$/i.test(value) || /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
		},
		message : '号码格式不正确（如：13688888888或020-8888888）'
	},
	idCard : {// 验证身份证
		validator : function(value) {
			return /^\d{17}([0-9]|X)$/i.test(value);
		},
		message : '身份证号码格式不正确（如：420222199910101234）'
	},
	driverCard : {// 验证驾驶证
		validator : function(value) {
			return /^\d{17}([0-9]|X)$/i.test(value);
		},
		message : '驾驶证号码格式不正确（如：420222199910101234）'
	},
	floatOrInt : {// 验证是否为小数或整数
		validator : function(value) {
			return /^([1-9]\d*(\.\d{1,3}(,\d\d\d)*)?|\d+(\.\d+))?$/i.test(value);
		},
		message : '请输入整数或小数'
	},
	currency : {// 验证货币
		validator : function(value) {
			return /^d{0,}(\.\d+)?$/i.test(value);
		},
		message : '货币格式不正确'
	},
	qq : {// 验证QQ,从10000开始
		validator : function(value) {
			return /^[1-9]\d{4,9}$/i.test(value);
		},
		message : 'QQ号码格式不正确（如：453384319）'
	},
	integer : {// 验证整数
		validator : function(value) {
			return /^[+]?[1-9]+\d*$/i.test(value);
		},
		message : '请输入整数'
	},
	chinese : {// 验证中文
		validator : function(value) {
			return /^[\u0391-\uFFE5]+$/i.test(value);
		},
		message : '请输入中文'
	},
	english : {// 验证英语
		validator : function(value) {
			return /^[A-Za-z]+$/i.test(value);
		},
		message : '请输入英文'
	},
	unnormal : {// 验证是否包含空格和非法字符
		validator : function(value) {
			return /.+/i.test(value);
		},
		message : '输入值不能为空和包含其他非法字符'
	},
	username : {// 验证用户名
		validator : function(value) {
			return /^[a-zA-Z][a-zA-Z0-9_]{3,19}$/i.test(value);
		},
		message : '用户名不合法（字母开头，允许4-20字节，允许字母数字下划线）'
	},
	faxno : {// 验证传真
		validator : function(value) {
			return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
		},
		message : '传真号码格式不正确（如020-88888888或0710-8888888）'
	},
	zip : {// 验证邮政编码
		validator : function(value) {
			return /^[1-9]\d{5}$/i.test(value);
		},
		message : '邮政编码格式不正确（如：100000）'
	},
	ip : {// 验证IP地址
		validator : function(value) {
			return /d+.d+.d+.d+/i.test(value);
		},
		message : 'IP地址格式不正确（如：192.168.1.1）'
	},
	name : {// 验证姓名，可以是中文或英文
		validator : function(value) {
			return /^[\u0391-\uFFE5]+$/i.test(value) || /^[A-Za-z]+$/i.test(value);
		},
		message : '请输入合法姓名（如：杰克或Jack）'
	},
	carNo : {
		validator : function(value) {
			return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value);
		},
		message : '请输入合法车牌号码（如：京A88888）'
	},
	carenergin : {
		validator : function(value) {
			return /^[a-zA-Z0-9]{16}$/.test(value);
		},
		message : '发动机号为16位数字字母组合（如：A123BCS12345691234）'
	},
	insure : {
		validator : function(value) {
			return /^[A-Za-z]{4}\d{18}$/.test(value);
		},
		message : '保险单号不合法（如：PDAA201445010100000001）'
	},
	email : {
		validator : function(value) {
			return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
		},
		message : '请输入有效邮箱账号（如：abc@126.com）'
	},
	msn : {
		validator : function(value) {
			return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
		},
		message : '请输入有效msn账号（如：abc@hotnail(msn/live).com）'
	},
	same : {
		validator : function(value, param) {
			return value == $(param[0]).val();
		},
		message : '两次输入的密码不一致'
	},
	combox : {
		validator : function(value) {
			return value != '请选择...';
		},
		message : '该项为必选项'
	},
});
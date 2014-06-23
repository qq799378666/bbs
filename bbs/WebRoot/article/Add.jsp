
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/_header.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" charset="utf-8" src="style/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="style/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="style/ueditor/lang/zh-cn/zh-cn.js"></script>
<%@include file="/inc/_nav.jsp"%>
<div class="container">
	<ol class="breadcrumb">
		<li>当前位置：</li>
		<li><a href="/">首页</a></li>
		<li><a href="Article_list?m=${m }">${mrname==null?'帖子列表':mrname }</a></li>
		<li class="active">发布帖子</li>
	</ol>
	<div class="panel panel-default">
			<div class="input-group col-md-4">
				<span class="input-group-btn">
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
						${mrname }<span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<s:iterator value="moduleList">
							<li><a href="Article_toAdd?m=${id }">${rname }</a></li>
						</s:iterator>
					</ul>
				</span> <input type="hidden" name="module.id" id="moduleId" value="${m }" /> <input class="form-control" name="title" id="title" value="" placeholder="输入文章标题" />
			</div>
		<div class="edit"></div>
		<script id="editor" type="text/plain" style="width:100%;height:500px;"></script>

		<div id="submitCase" class="text-right">
			<button class="btn btn-default" id="submitBtn">发布</button>
		</div>
	</div>
</div>
<script>
	//实例化编辑器
	var ue = UE.getEditor('editor', {

	});
	$(function() {
		$("#submitBtn").on("click", function() {

			if ($("#submitCase img").size() > 0)
				return false;

			var title = $("#title").val();

			if ($.trim(title).length == 0) {
				alert("请添加标题");
				return false;
			}

			if (!window.confirm("确认发布吗?"))
				return false;

			var temp = $("#submitCase").html();
			$("#submitCase").html(" <img src='style/image/loading.gif'/> ");

			var data = {};

			var content = UE.getEditor('editor').getContent();
			var moduleId = $("#moduleId").val();
			data['title'] = title;
			data['content'] = content;

			if (moduleId != '') {//如果获取到的版块id为数字
				data['module.id'] = moduleId;
			}

			$.post("j_Article_add", data, function(json) {
				$("#submitCase").html(temp);
				if (json.success) {
					var s = 5;
					var f = function() {
						if (s <= 0) {
							$("#aimA").get(0).click();
							return;
						}
						window.location.href = "Article_success?id=" + json.id;
						//$("#submitCase").html("发布成功," + s + "<h3>秒后跳转到<a href='' id='aimA'>首页</a></h3>");
						s--;
						window.setTimeout(f, 1000);
					};
					f();
					return false;
				} else {
					alert(json.msg);
				}
			}, "json");

		});
	});
</script>
<%@include file="/inc/_footer.jsp"%>
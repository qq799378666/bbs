<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/_header.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="container">
	<h3 class="page-header">修改资料</h3>
	<div class="col-md-12">
		<form id="mainForm" class="form-horizontal" role="form">


			<div class="form-group">
				<label class="col-sm-1 control-label">性别: </label>
				<div class="col-sm-3">
					<label> <input class="radio-inline radio" type="radio" name="sex" value="男" <s:property value="model.sex.equals(\"男\")?'checked=checked':''" /> />男
					</label> <label> <input class="radio-inline radio" type="radio" name="sex" value="女" <s:property value="model.sex.equals(\"女\")?'checked=checked':''" /> />女
					</label>

				</div>
			</div>


			<div class="form-group">
				<label for="nickname" class="col-sm-1 control-label">昵称:</label>
				<div class="col-sm-3">
					<input class="form-control" id="nickname" type="text" name="nickname" placeholder="" value="${model.nickname }">
				</div>
				<span class="msg"></span>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10" id="submitCase">
					<button type="submit" class="btn btn-default">修改</button>
				</div>
			</div>
		</form>
		<script>
			$(function() {

				$("#mainForm").on("submit", function() {

					var data = $("#mainForm").serialize();
					var temp = $("#submitCase").html();
					$("#submitCase").html("<img src='style/image/loading.gif'/> ");
					$.post("j_User_alter", data, function(json) {
						$("#submitCase").html(temp);
						if (json.success) {
							var s = 5;
							var f = function() {
								if (s <= 0) {
									$("#aimA").get(0).click();
									return;
								}
								$("#submitCase").html("<h3>修改成功," + s + "秒后跳转到<a href='' id='aimA'>首页</a></h3>");
								s--;
								window.setTimeout(f, 1000);
							};
							f();
						} else {
							alert(json.msg);
						}
					}, "json");
					return false;
				});
			});
		</script>
	</div>
</div>


<%@include file="/inc/_footer.jsp"%>
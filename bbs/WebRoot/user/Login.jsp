<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/_header.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<input type="hidden" name="href" value="${href }" id="href">
<br/>
<br/>
<br/>
<div class="container">
  <div class="modal-content" style="width:500px; margin:auto;margin-top:10px; text-align:center;">
    <div class="modal-header">
      <h4>用户登录</h4>
    </div>
    <div class="modal-body container">
      <form class="form-horizontal" role="form" onSubmit="return false;"
					id="loginForm">
        <div class="input-group" style="width:240px;margin:4px;"> <span class="input-group-addon" style="background:#FFF;"> <i class="glyphicon glyphicon-user"></i> </span>
          <input class="form-control"
    id="username" type="text" name="userLoginForm.username"
    placeholder="用户名/邮箱/手机" value="">
        </div>
        <div class="input-group" style="width:240px;margin:4px;"> <span class="input-group-addon" style="background:#FFF;"> <i class="glyphicon glyphicon-lock"></i> </span>
          <input class="form-control" id="pwd"
    type="password" name="userLoginForm.pwd" placeholder="密码"
    value="">
        </div>
        <div class="input-group">
          <label for="rem">
            <input class="from-control" id="rem"
							type="checkbox" checked="checked" name="userLoginForm.rem"
							value="true">
            记住密码 </label>
        </div>
        <div class="input-group">
          <div id="submitCase">
            <button id="loginBtn" style="width:70px" class="btn btn-primary">登录</button>
          </div>
          <div class="pull-right"> <a href="user/Register.jsp">注册</a>|<a href="User_toFindPwd">找回密码</a> </div>
        </div>
      </form>
    </div>
    <div class="modal-footer"> 使用其他账号登陆:&nbsp;&nbsp; <a href="javascript:alert('功能尚未实现')"><img src="image/qq24X24.png" /></a> </div>
  </div>
</div>
<script>
		
		$(function() {
			$("#loginForm").submit(function() {
				if ($("#submitCase img").size() > 0)
					return false;
				var temp = $("#submitCase").html();
				$("#loginBtn").html(" <img src='style/image/loading.gif'/> ");
				$.post("j_User_login", $("#loginForm").serialize(), function(json) {
					$("#submitCase").html(temp);
					if (json.success) {
						$("#submitCase").html("<strong>登录成功</strong>,跳转中...");
						document.location.href = $("#href").val();
						return false;
					} else {
						alert(json.msg);
					}
				}, "json");
				return false;
			});

		});
		
		</script>
<%@include file="/inc/_footer.jsp"%>

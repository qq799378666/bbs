
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:set name="user" value="#session.user"></s:set>
<div class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">44MO.COM</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="#">首页</a></li>
				<s:if test="#session.user.postions.size!=0">
					<li class=""><a href="admin">管理</a></li>
				</s:if>
			</ul>

			<s:if test="#user==null">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#login" data-toggle="modal">登录</a></li>
					<li><a href="user/Register.jsp">注册</a></li>
				</ul>
			</s:if>
			<s:else>
				<ul class="nav navbar-nav navbar-right">
					<li><a class="dropdown-toggle" data-toggle="dropdown"
						href="User_getDetail.action?id=${user.id}">${user.nickname}<span
							class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a target="_blank" href="User?id=${user.id}">个人中心</a></li>
							<li><a href="User_logout.action">退出登录</a>
							</li>
						</ul>
						</li>
				</ul>
			</s:else>
		</div>
	</div>
<div class="modal fade" id="login">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<a href="#" class="close" data-dismiss="modal">×</a>
				<h4>用户登录</h4>
			</div>
			<div class="modal-body container">
				<form class="form-horizontal" role="form" onSubmit="return false;"
					id="loginForm">

					<div class="input-group" style="width:240px;margin:4px;">
    <span class="input-group-addon" style="background:#FFF;">
    <i class="glyphicon glyphicon-user"></i>
    </span>

     <input class="form-control"
							id="username" type="text" name="userLoginForm.username"
							placeholder="用户名/邮箱/手机" value="">

					</div>

					<div class="input-group" style="width:240px;margin:4px;">

    <span class="input-group-addon" style="background:#FFF;">
    <i class="glyphicon glyphicon-lock"></i>
    </span>

						 <input class="form-control" id="pwd"
							type="password" name="userLoginForm.pwd" placeholder="密码"
							value="">


					</div>

					<div class="input-group">
						<label for="rem"> <input class="from-control" id="rem"
							type="checkbox" checked="checked" name="userLoginForm.rem"
							value="true"> 记住密码
						</label>
					</div>

					<div class="input-group">
						<div id="submitCase">
							<button id="loginBtn" style="width:70px" class="btn btn-primary">登录</button>
						</div>
						<div class="pull-right">
							<a href="user/Register.jsp">注册</a>|<a href="User_toFindPwd">找回密码</a>
						</div>
					</div>
				</form>
			</div>

			<div class="modal-footer">
				使用其他账号登陆:&nbsp;&nbsp; <a href="javascript:alert('功能尚未实现')"><img src="image/qq24X24.png" /></a>
			</div>
		</div>
	</div>
</div>
</div>

<script type="text/javascript" src="style/js/nav.js"></script>

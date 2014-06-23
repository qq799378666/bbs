<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="row-fluid">
	<div class="span12">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="navbar">
						<div class="navbar-inner">
							<div class="container-fluid">
								<a class="btn btn-navbar"
									data-target=".navbar-responsive-collapse"
									data-toggle="collapse"></a> <a class="brand" href="#">44mo.com</a>
								<div class="nav-collapse collapse navbar-responsive-collapse">
									<ul class="nav">
										<li class="active"><a href="index">主页</a></li>
										<!--<li><a href="forum/main">论坛</a></li>
										<li><a href="shop/main">购物</a></li>
										<li class="dropdown"><a class="dropdown-toggle"
											data-toggle="dropdown" href="#">其它</a>
											<ul class="dropdown-menu">
												<li><a href="news/main">新闻</a></li>
												<li><a href="#">动态</a></li>
												<li><a href="#">其他</a></li>
												<li class="divider"></li>
												<li class="nav-header">标签</li>
												<li><a href="#">链接1</a></li>
												<li><a href="#">链接2</a></li>
											</ul></li>
									--></ul>

									<s:if test="#session.user==null">
										<ul class="nav pull-right">
											<li><a href="#login" data-toggle="modal">登录</a>
											<li class="divider-vertical"></li>
											<li><a href="user/register.html">注册</a></li>
										</ul>
										<div class="modal hide fade" id="login">
											<div class="modal-header">
												<a href="#" class="close" data-dismiss="modal">×</a>
												<h4>用户登录</h4>
											</div>
											<form class="form-horizontal" onSubmit="return false;"
												id="loginForm">
												<div class="modal-body">
													<div class="control-group">
														<label for="username" class="control-label">用户名</label>
														<div class="controls">
															<input id="username" type="text"
																name="userLoginForm.username" placeholder="用户名/邮箱/手机"
																value="admin">
														</div>
													</div>
													<div class="control-group">
														<label for="pwd" class="control-label">密码</label>
														<div class="controls">
															<input id="pwd" type="password" name="userLoginForm.pwd"
																placeholder="密码" value="123"> <a href="#">忘记密码?</a>
														</div>
													</div>

													<div class="control-group">
														<div class="controls">
															<label class="checkbox"> <input id="rem"
																type="checkbox" name="userLoginForm.rem" value="true">记住密码
															</label>
														</div>
													</div>
													<div class="control-group">
														<div class="controls">
															<button class="btn btn-primary">登录</button>
															<a href="user/register.html">注册</a>
														</div>
													</div>

												</div>
											</form>

											<div class="modal-footer">
												使用其他账号登陆:&nbsp;&nbsp; <a href="#"><img
													src="image/qq24X24.png" /></a>
											</div>
										</div>

									</s:if>
									<s:else>
										<ul class="nav pull-right">
											<li>
												<div class="btn-group">
													<a href="#" class="btn"><s:property
															value="#session.user.nickname" /></a> <a
														class="btn dropdown-toggle" data-toggle="dropdown"
														href="#"><span class="caret"></span></a>
													<ul class="dropdown-menu">
														<li><a href="User_getDetail.action?id=<s:property value="#session.user.id" />">个人中心</a></li>
														<li><a href="#">账号设置</a></li>
														<li><a href="javascript:location.href='User_quitLogin.action?href='+location.href;">退出登录</a></li>
													</ul>
												</div>
											</li>
										</ul>
									</s:else>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="style/js/nav.js"></script>

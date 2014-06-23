<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/_header.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="container">
	<h3 class="page-header">
		<s:property value="model.nickname" />
		的个人主页
	</h3>

	<ul class="nav nav-tabs">
		<li><a href="User?id=${id }">首页</a></li>
		<li class="active"><a href="User_detail?id=${id }">资料</a></li>
	</ul>
	<br>
	
	<div class="col-md-12">
		<p>
		<a href="User?id=${user.id}" target="_block">${model.nickname }
				</a>的资料
		</p>
			<p>金币:${model.money }</p>
			<p>等级:${model.rank }</p>
			<p>积分:${model.score }</p>
			<p>职位:
				<s:if test="model.postions.size==0">
					无
				</s:if>
				<s:else>
					<s:iterator value="model.postions">
						-${rname }
					</s:iterator>
				</s:else>
			</p>
			<p>性别:${model.sex }</p>
			<p>邮箱:${model.email }</p>
		<br>
		<s:if test="id==#session.user.id">
			<a href="User_toAlter">修改资料</a>
		</s:if>
	</div>
</div>


<%@include file="/inc/_footer.jsp"%>
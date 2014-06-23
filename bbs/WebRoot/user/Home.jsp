<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/_header.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="container">
	<h3 class="page-header">
		<s:property value="model.nickname" />
		的个人主页
	</h3>

	<div>
		<ul class="nav nav-tabs">
			<li class="active"><a href="User?id=${id }">首页</a></li>
			<li><a href="User_detail?id=${id }">资料</a></li>
		</ul>
	</div>
	<br>
	<div class="col-md-3">
		<div class="panel panel-default" style="height:100%;">
			<div class="panel-heading">
				<a href="User?id=${user.id}" target="_block">${model.nickname }
				</a>的资料
			</div>
			<div class="panel-body">
				<p>金币:${model.money }</p>
				<p>等级:${model.rank }</p>
				<p>积分:${model.score }</p>
				<p>性别:${model.sex }</p>
				<p>邮箱:${model.email }</p>
			</div>
		</div>
	</div>
	<div class="col-md-9">
		<div class="panel panel-default">
			<div class="panel-heading">最新帖子</div>
			<s:set name="articlePV" value="newestArticlePV"></s:set>
			<s:property value="articlePV.rows"/>
			<s:if test="#articlePV.rows.size==0">
				该家伙很懒,没有发表任何帖子
			</s:if>
			<s:else>
				<ul class="list-group">
					<s:iterator value="#articlePV.rows">
						<li class="list-group-item"><a href="Article?id=${id }"><s:property value="title" /></a></li>
					</s:iterator>
				</ul>
			</s:else>
		</div>
	</div>
</div>
<%@include file="/inc/_footer.jsp"%>
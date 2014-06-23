<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.vo.PageVO"%>
<%@page import="com.po.Article"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="/inc/_header.jsp"%>
<%@include file="/inc/_nav.jsp"%>
<div class="col-md-12">
	<div class="col-md-5">
		<div class="panel panel-default">
			<div class="panel-heading">最新帖子</div>
			<ul class="list-group">
				<s:iterator value="articlePV.rows">
					<li class="list-group-item"><a href="Article?id=${id }" target="_blank"><s:property value="title" /></a>
					 <span class="pull-right">
					 	<s:property value="@com.util.ViewUtil@test(time)"/> 
					 </span></li>
				</s:iterator>
			</ul>
		</div>
	</div>

	<div class="col-md-7">
		<div class="panel panel-default">
			<div class="panel-heading">版块</div>
			<div class="panel-body">
				<span class=""> <a href="Article_list">全部(<i style="color:red">${articlePV.total }</i>)
				</a>
					<hr />
				</span>
				<s:iterator value="moduleList" var="module">
					<span class="">
						<p>
							<a href="Article_list?m=${id }"> ${rname }(<i style="color:red">${count }</i>)</a>
						</p>
						<p>
						版主:<s:iterator value="moderators">
							<a target="_blank" href="User?id=${id }">${nickname }</a>
						</s:iterator>
						</p>
						<p>简介:${intro }</p>
						<p>
							最新文章:<a target="_blank" href="Article?id=${newestArticleId }">${newestArticleTitle }</a>
						</p>
					</span>
					<hr />
				</s:iterator>
			</div>
		</div>
	</div>
</div>
<div style="height:200px"></div>

<%@include file="/inc/_footerShow.jsp"%>
<%@include file="/inc/_footer.jsp"%>

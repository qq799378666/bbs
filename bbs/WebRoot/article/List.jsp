<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/_header.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:set name="p" value="articlePV.page"></s:set>
<s:set name="ps" value="articlePV.pageSize"></s:set>
<s:set name="total" value="articlePV.total"></s:set>
<s:set name="maxPage" value="#total%#ps==0?#total/#ps:(#total/#ps+1)"></s:set>
<%@include file="/inc/_nav.jsp"%>
<div class="container">
	<ol class="breadcrumb">
		<li>当前位置：</li>
		<li><a href="/">首页</a></li>
		<li class="active">${mrname==null?"所有版块":mrname }<!-- 将选择版块加在这里 --> ${g?'-精华帖':'' } <s:property value="(sw==null||sw.equals(''))?'':'-包含关键字:<i>'+sw+'</i>'" escape="false" />
		</li>
	</ol>
	<form action="Article_list" class="form-search">
		<div class="input-group col-md-3 pull-right">
			<span class="input-group-btn">
				<button title="点击切换版块" type="button" class="btn btn-default  dropdown-toggle" data-toggle="dropdown">
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<s:iterator value="moduleList">
						<li><a href="Article_list?p=${maxPage }&ps=${ps}&m=${id}">${rname }</a></li>
					</s:iterator>
				</ul>
			</span> 
			<input type="hidden" name="ps" value="${ps }" /> <input type="hidden" name="m" value="${m }" /> <input type="hidden" name="g" value="${g }" />
			<input class="form-control" type="search" name="sw" id="searchbox" placeholder="在☆${mrname==null?'所有版块':mrname }☆下搜索" value="${sw}" /> 
			<span class="input-group-btn">
				<button class="btn btn-default">
					<i title="搜索" class="glyphicon glyphicon-search"></i>
				</button>
			</span>
		</div>
	</form>

	<div class="page-header">
		<s:if test="#session.user!=null">
			<a target="_blank" class="btn btn-primary" href="Article_toAdd?m=${m }">发帖</a>
		</s:if>
		<s:else>
			<a title="请先登录" class="btn btn-primary disabled" href="Article_toAdd?m=${m }">发帖</a>
		</s:else>
		<s:if test="g">
			<a class="btn btn-primary" href="Article_list?m=${m }&sw=${sw}">所有帖子</a>
		</s:if>
		<s:else>
			<a class="btn btn-primary" href="Article_list?m=${m }&sw=${sw}&g=true">精华帖</a>
		</s:else>
	</div>
	<s:if test="articlePV.rows.size==0">
		<div class="panel-body text-center">
			<h3>没有找到</h3>
		</div>
	</s:if>
	<s:else>
		<ul class="list-group">
			<s:iterator value="articlePV.rows">
				<li class="list-group-item"><a target="_blank" href="Article?id=${id }"><s:property value="title" /></a>
				${good?'<span class="label label-success">精</span>':'' } <span class="pull-right"><s:property value="@com.util.ViewUtil@test(time)" /> </span></li>
			</s:iterator>
		</ul>
	</s:else>
</div>
<s:set name="g" value="g"></s:set>
<s:set name="urlL" value="'Article_list?'+(sw!=null&&!sw.equals('')?('&sw='+sw):'')+(m!=null?('&m='+m):'')+(#g==true?'&g=true':'')"></s:set>
<%@include file="/inc/_pagination.jsp"%>
<%@include file="/inc/_toTop.jsp"%>
<%@include file="/inc/_footer.jsp"%>
<link href="style/css/article/list.css" rel="stylesheet">


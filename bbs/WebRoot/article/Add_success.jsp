<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/_header.jsp"%>


<%@taglib prefix="s" uri="/struts-tags" %>
<h3>
	发布文章成功<a href="Article?id=${model.id }">点击查看</a>,
	<br>
	<a href="Article_toAdd">再发一贴</a>
	
</h3>

<a href="/">返回首页</a>

<%@include file="/inc/_footer.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/admin/inc/_header.jsp" %>
<%@include file="_manager.jsp" %>
<script type="text/javascript" src="style/js/postion/postionManager.js"></script>
<!-- 工具栏 -->
<div id="tb" style="display: none">
		<form id="form">
			名称:<input name="search['rname']" />
			<a onclick="query()" id="query" >查询</a>
			<a id="add" onclick="add()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> 
			<a id="modify" onclick="modify()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a> 
			<a id="del" onclick="del()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a> 
			<a onclick="reload()" class="easyui-linkbutton" iconCls="icon-reload" plain="true" style="float: right;">刷新</a>
		</form>
	
</div>
	<div id="dlg" style="padding-top: 10px;">
		<form id="fm">
			<table align="center" cellspacing="5px">
				<tr>
					<td>部门名称：</td>
					<td><input name="rname" id="rname" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td>授予权限：</td>
					<td><input name="ms.id" id="role" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dg" data-options="rownumbers:true"></div>
<%@include file="/admin/inc/_footer.jsp" %>
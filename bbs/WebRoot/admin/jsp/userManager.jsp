<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/admin/inc/_header.jsp" %>
<script type="text/javascript" src="style/js/user/userManager.js"></script>
<%@include file="_manager.jsp" %>
	<div id="stb">
		<form id="form">
			昵称:<input name="search['nickname']" />
			用户名:<input name="search['username']" />
			<a onclick="query()" id="query" >查询</a>
		<a id="del" onclick="del()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a> 
			<a onclick="reload()" id="reload" style="float: right;">刷新</a>
			<span style='display:none;'><input type="submit"></span>
		</form>
	</div>
	<div id="dlg" style="padding-top: 10px;">
		<form id="fm">
			<table align="center" cellspacing="5px">
				<tr>
					<td>用户：</td>
					<td><input name="nickname" id="nickname" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td>授予职位：</td>
					<td><input name="postion.id" id="postion" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<div id="dlg2" style="padding-top: 10px;">
		<form id="fm2">
			<table align="center" cellspacing="5px">
				<tr>
					<td>用户：</td>
					<td><input name="nickname" id="nickname" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td>选择版块：</td>
					<td><input name="module.id" id="module" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	
	<!-- 数据表格 -->
	<div id="dg" data-options="rownumbers:true"></div>
<%@include file="/admin/inc/_footer.jsp" %>
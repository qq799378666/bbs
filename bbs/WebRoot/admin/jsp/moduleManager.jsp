<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/admin/inc/_header.jsp" %>
<%@include file="_manager.jsp" %>
<script type="text/javascript" src="style/js/module/moduleManager.js"></script>
<div id="stb">
		<form id="form">
			名称:<input name="search['rname']" />
			<a onclick="query()" id="query" >查询</a>
			
						<a id="add" onclick="add()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> 
			<a onclick="modify()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a> 
			<a id="del" onclick="del()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a> 
			<a onclick="reload()" id="reload" style="float: right;">刷新</a>
		</form>
	</div>
	<div id="dlg" style="padding-top: 20px;">
		<form id="fm">
			<input type="hidden" name="id">
			<table align="center" cellspacing="5px">
				<tr>
					<td>版块名称：</td>
					<td><input name="rname" id="rname" style="width: 200px;" /></td>
				</tr>
				<tr>
					<td>简介:</td>
					<td><input name="intro" id="intro" style="width:200px" /></td>
				</tr>
			</table>
		</form>
	</div>
	
	
	<!-- 数据表格 -->
	<div id="dg" data-options="rownumbers:true"></div>
<%@include file="/admin/inc/_footer.jsp" %>
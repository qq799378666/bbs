<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/admin/inc/_header.jsp" %>
<%@include file="_manager.jsp" %>
<script type="text/javascript" src="style/js/article/articleManager.js"></script>
	<div id="stb">
		<form id="form">
			版块:<select id="module" name="module.id" class="easyui-combobox" style="width:100px;">
				</select>
			标题:<input name="search['title']" />
			类型:<select name="good" onchange="query()">
					<option value="">所有</option>
					<option value="true">精华</option>
					<option value="false">普通帖</option>
				</select>
			时间:<input type="hidden" name="tsfs[0].key" value="time">
			<input name="tsfs[0].startDate" id="startDate" />->
			<input name="tsfs[0].endDate" id="endDate" /> 
			<a onclick="query()" id="query" >查询</a>			
			
 		<a id="del" onclick="changeState()" class="easyui-linkbutton" iconCls="icon-tip" plain="true">转换</a> 
		<a id="del" onclick="del()" class="easyui-linkbutton" iconCls="icon-cancel" plain="true">删除</a> 
			<a onclick="reload()" id="reload" style="float: right;">刷新</a>
		<span style='display:none;'><input type="submit"></span>
		</form>
	</div>
	<!-- 数据表格 -->
	<div id="dg" data-options="rownumbers:true"></div>

<%@include file="/admin/inc/_footer.jsp" %>
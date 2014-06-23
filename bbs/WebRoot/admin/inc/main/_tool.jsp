<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 选项卡菜单 -->
<div id="tabsMenu" class="easyui-menu" style="width: 120px; display: none">
	<div name="close" iconCls="icon-cancel">关闭</div>
	<div class="menu-sep"></div>
	<div name="Other" iconCls="icon-cancel">关闭其他</div>
	<div name="All" iconCls="icon-cancel">关闭所有</div>
</div>

<!-- 树菜单 -->
<div id="treeMenu" class="easyui-menu" style="width: 120px; display: none">
	<div onclick="expand()" iconCls="icon-add">展开</div>
	<div onclick="collapse()" iconCls="icon-remove">收缩</div>
	<div class="menu-sep"></div>
	<div onclick="expandAll()" iconCls="icon-add">展开所有</div>
	<div onclick="collapseAll()" iconCls="icon-remove">收缩所有</div>
	<div class="menu-sep"></div>
	<div onclick="newWin()" data-options="iconCls:'icon-redo'">新标签打开</div>
</div>

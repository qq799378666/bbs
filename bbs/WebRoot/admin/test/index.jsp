<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'demo.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <a href="j_Menu_getMenusByCurUser">获取当前用户的菜单</a>
  <a href="j_Menu_getMenusByPostion?postion.id=1">根据职位获取菜单</a>
  <hr>
  <a href="j_Postion_getAll">获取职位列表</a>
  <hr>
  <a href="j_Article_getPageVOToSmart?good=true">获取文章列表</a>
  <hr>
  <a href="j_User_getPageVO">获取用户列表</a>
  <hr>
  <a href="j_Module_getPageVO">获取版块列表</a>
  <hr>
  <a href="j_Postion_getPageVO">获取职位列表</a>
  </body>
</html>

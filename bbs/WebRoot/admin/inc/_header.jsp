<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
 <base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>燎火,44mo.com</title>
<%@include file="main/Style.jsp" %>
<!--[if lt IE 9]>
<script src="style/js/html5.js"></script>
<![endif]-->
</head>
<body class="easyui-layout">



<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>出错啦</title>
  <link rel="stylesheet" href=<%=basePath + "css/error.min.css" %> />
  
</head>
<body>
	<div class="container">
		<h3 class="error-info">${message}</h3>
		<p class="error-help">请联系管理员:  laowang@qq.com</p>
		<a href="<%=basePath + "index.jsp" %>" class="btn-back">返回首页</a>
	</div>
</body>
</html>

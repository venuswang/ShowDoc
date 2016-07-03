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
	<title>操作成功啦</title>
	<link rel="stylesheet" href=<%=basePath + "css/success.min.css" %> />
</head>
<body>
	<div class="container">
		<h3 class="success-title">已成功注册啦!</h3>
		<p class="success-info"><span class="success-time" id="success-time">5</span>秒后自动跳转，若没有跳转，点以下按钮跳回首页</p>
		<a href=<%=basePath + "project/showProject.action" %> class="btn-back">返回个人首页</a>
	</div>
	<script>
		window.onload = function() {
			var url = window.location.protocol + "\/\/" + 
					window.location.host + "\/ShowDoc\/"  + "project/showProject.action";
			delayURL(url);
		};
		function delayURL(url) {
			var timer = parseInt(document.getElementById("success-time").innerHTML);
			if(timer > 1) {
				timer --;
				document.getElementById("success-time").innerHTML=timer;
			} else {
				window.location.href= url;
			}
		    setTimeout("delayURL('" + url + "')",1000);
		}
	</script>
</body>
</html>
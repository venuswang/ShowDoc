<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>showdoc创建项目</title>
	<link rel="stylesheet" href="../css/basic.min.css" />
	<link rel="stylesheet" href="../css/userProject.min.css" />
</head>
<body>
	<header id="header">
		<div class="common-left">
			<h1 class="left-title">ShowDoc</h1>
		</div>
		<div class="common-middle">
			<nav class="showdoc-nav">
				<ul class="nav-list">
					<li class="nav-item">
						<a href="javascript:void(0);" class="nav-btn">个人中心</a>
					</li>
					<li class="nav-item">
						<a href="javascript:void(0);" class="nav-btn">分享主页</a>
					</li>
					<li class="nav-item">
						<a href="javascript:void(0);" class="nav-btn">网站首页</a>
					</li>
					<li class="nav-item">
						<a href="javascript:void(0);" class="nav-btn">退出</a>
					</li>
				</ul>
			</nav>		
		</div>
	</header><!-- /header -->
	<div class="main">
		<ul class="project-list">
			<li class="project-item">
				<a href="" class="project-btn">新建项目<span class="add-project-icon">&#xe904;</span></a>
			</li>
		</ul>
	</div>
</body>
</html>
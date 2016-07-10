<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String local = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>showdoc已有项目展示</title>
	<link rel="stylesheet" href='<%=basePath + "css/basic.min.css" %>' />
	<link rel="stylesheet" href='<%=basePath + "css/editormd.min.css" %>' />
	<link rel="stylesheet" href='<%=basePath + "css/showproject.min.css" %>' />
	<script src="<%=basePath + "js/jquery-1.12.3.min.js" %>"></script>
	<script src="<%=basePath + "js/editormd.min.js" %>"></script>
	<script src="<%=basePath + "js/showproject.min.js" %>"></script>
</head>
<body>
	<header id="header">
		<div class="common-middle">
			<h1 class="middle-title">项目名称</h1>
		</div>
		<div class="common-right">
			<a href="javascript:void(0);" class="btn">项目管理</a>
		</div>
	</header><!-- /header -->
	<div id="main">
		<div class="sideNav">
			<div class="add-page-item">
				<a href="<%=basePath %>jsp/project/editproject.jsp" class="btn btn-add-page">新建页面</a>
			</div>
			<nav id="page-nav">
				<ul class="page-list">
					<li class="page-item">
						<a href="javascript:void(0);" class="btn btn-page active">页面标题1</a>
					</li>
					<li class="page-item">
						<a href="javascript:void(0);" class="btn btn-page">页面标题2</a>
					</li>
					<li class="page-item">
						<a href="javascript:void(0);" class="btn btn-page">页面标题3</a>
					</li>
				</ul>
			</nav>
		</div>
		<div class="show-content">
			<!-- 操作 -->
			<div class="content-operation">
				<a href="javascript:void(0);" class="btn btn-share">分享</a>
				<a href="javascript:void(0);" class="btn btn-copy">复制</a>
				<a href="javascript:void(0);" class="btn btn-edit">编辑</a>
				<a href="javascript:void(0);" class="btn btn-remove">删除</a>
			</div>
			<!-- 标题 -->
			<div class="content-title">
				<h2 class="title"></h2>
			</div>
			<!-- editor.md 初始化容器 -->
			<div id="test-editormd"></div>
			<!-- markdown 内容展示区 -->
			<div id="markdown-content">
					
			</div>
			</div>
		</div>
	</div>
</body>
</html>
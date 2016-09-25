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
	<link href="http://cdn.bootcss.com/layer/2.4/skin/layer.min.css" rel="stylesheet">
	
</head>
<body>
	<header id="header">
		<div class="common-middle">
			<a href="<%=basePath + "project/showProject.action" %>" class="btn-index">首页</a>
			<h1 class="middle-title"></h1>
		</div>
		<div class="common-right">
			<a href="javascript:void(0);" class="btn btn-manage">项目管理</a>
			<ul class="feature-list">
				<span class="top-triangle"></span>
				<li class="feature-item"><a href="javascript:void(0)" class="btn-feature btn-feature-member" data-item="member">成员管理</a></li>
				<li class="feature-item"><a href="javascript:void(0)" class="btn-feature btn-feature-delete" data-item="delete">删除项目</a></li>
				<!-- <li class="feature-item"><a href="javascript:void(0)" class="btn-feature btn-feature-project" data-item="project">返回项目首页</a></li> -->
			</ul>
		</div>
	</header><!-- /header -->
	<div id="main">
		<div class="sideNav">
			<div class="add-item-operations">
				<a href="javascript:void(0);" class="btn btn-add-page" title="新建页面">新建页面<span class="add-project-icon">&#xe904;</span></a>
				<a href="javascript:void(0);" title="新建目录" class="btn btn-add-dir">新建目录<span class="add-project-icon">&#xe907;</span></a>
			</div>
			<nav id="project-nav">
				<ul class="project-list" id="project-list">
					
				</ul>
			</nav>
		</div>
		<div class="show-content">
			<!-- 操作 -->
			<div class="content-operations">
				<a href="javascript:void(0);" class="btn btn-share" data-role="share">分享</a>
				<a href="javascript:void(0);" class="btn btn-copy" data-role="copy">复制</a>
				<a href="javascript:void(0);" class="btn btn-edit" data-role="edit">编辑</a>
				<a href="javascript:void(0);" class="btn btn-remove" data-role="remove">删除</a>
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
	<div id="mask" data-event="close"></div>
	<!-- bulid in project -->
	<script src="<%=basePath + "js/jquery-1.12.3.min.js" %>"></script>
	<script src="<%=basePath + "js/editormd.min.js" %>"></script>

	<!-- CDN resources -->
	<script src="http://cdn.bootcss.com/layer/2.4/layer.min.js"></script>
	<!-- end CDN resources -->

	<script src="<%=basePath + "js/showproject.min.js" %>"></script>
	<!-- end bulid in project -->

</body>
</html>
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
	<title>showdoc项目编辑</title>
	<link rel="stylesheet" href='<%=basePath + "css/basic.min.css" %>' />
	<link rel="stylesheet" href='<%=basePath + "css/editormd.min.css" %>' />
	<link rel="stylesheet" href='<%=basePath + "css/editproject.min.css" %>' />
	<link href="http://cdn.bootcss.com/layer/2.4/skin/layer.min.css" rel="stylesheet">
	
</head>
<body>
	<div class="contrainer">
		<!-- 表单 -->
		<form id="project-form">
			<div class="form-layout">
				<!-- 标题 -->
				<div class="form-items form-items-title">
					<label for="_pagetitle" class="items-label">页面标题</label>
					<input type="text" name="pagetitle" placeholder="请输入页面标题"  class="item-content" id="_pagetitle" />
				</div>
				<!-- 排序数字 -->
				<div class="form-items form-items-sort">
					<label for="_pagesortid" class="items-label">页面序号</label>
					<input type="number" name="pagesortid" placeholder="序号（可选）用于排序" class="item-content" id="_pagesortid" />
				</div>
				<!-- 表单操作 -->
				<div class="form-operations-item">
					<a href="javascript:void(0);" class="btn btn-submit">确定</a>
					<a href="javascript:void(0);" class="btn btn-cancel">取消</a>
				</div>
			</div>
			<!-- 目录 -->
			<div class="form-items">
				<label for="_pageparent" class="items-label">父级目录</label>
				<select name="patent" id="_pageparent" class="item-content">
					
				</select>
			</div>
			<!-- markdown 文档区 -->
			<div class="form-markdown-items">
				<!-- markdown 内容的相关来源 -->
				<div class="form-markdown-operations">
					<a href="javascript:void(0);" class="btn" data-role="API">插入API接口模板</a>
					<a href="javascript:void(0);" class="btn" data-role="data">插入数字字典模板</a>
					<a href="http://tool.oschina.net/codeformat/json/" class="btn">JSON转参数表格</a>
					<a href="javascript:void(0);" class="btn" data-role="http">在线HTTP请求测试</a>
				</div>
				<div class="form-markdown-editormd">
					<div id="markdown-editormd">
						
					</div>
				</div>
			</div>
		</form>
	</div>

	<!-- bulid in project -->
	<script src="<%=basePath + "js/jquery-1.12.3.min.js" %>"></script>
	<script src="<%=basePath + "js/editormd.min.js" %>"></script>

	<!-- CDN resources -->
	<script src="http://cdn.bootcss.com/layer/2.4/layer.min.js"></script>
	<!-- end CDN resources -->

	<script src="<%=basePath + "js/editproject.min.js" %>"></script>
	<!-- end bulid in project -->
</body>
</html>
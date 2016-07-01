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
	<title>ShowDoc首页</title>
	<link rel="stylesheet" href=<%=basePath + "css/basic.min.css" %> />
	<link rel="stylesheet" href=<%=basePath + "css/index.min.css" %> />
	<script src=<%=basePath + "js/jquery-1.12.3.min.js" %>></script>
	<script src=<%=basePath + "js/jquery-zcarousel.min.js" %>></script>
	<script src=<%=basePath + "js/index.min.js" %>></script>
</head>
<body>
	<div id="container" data-setting='{"width":"100%","height":"100%","speed":5000,"autoPlay":true,
			"animateTime":1500}'>
		<!-- 展示入口信息 -->
		<div class="login-container item">
			<div class="container-mask login-container-mask">
				<div class="login-content">
					<h1 class="simple-title">ShowDoc</h1>
					<p class="simple-summary">帮你解决前端后端开发文档的烦恼</p>
					<div class="login-operations">
						<a href="javascript:void(0);" class="btn btn-login">登录</a>
						<a href=<%=basePath + "jsp/login/register.jsp" %> class="btn btn-register">注册</a>
					</div>
				</div>
			</div>
		</div>

		<!-- 展示功能模块信息 -->
		<div class="function-container item">
			<div class="container-mask">
				<ul class="function-list">
					<li class="list-item"><span class="item-content" data-item="API">API文档</span></li>
					<li class="list-item"><span class="item-content" data-item="dictionary">数据字典</span></li>
					<li class="list-item"><span class="item-content" data-item="doc">说明文档</span></li>
					<li class="list-item"><span class="item-content" data-item="sharing">分享与导出</span></li>
					<li class="list-item"><span class="item-content" data-item="competence">权限管理</span></li>
					<li class="list-item"><span class="item-content" data-item="project">公开项目与私密项目</span></li>
					<li class="list-item"><span class="item-content" data-item="member">项目成员</span></li>
					<li class="list-item"><span class="item-content" data-item="edit">markdown编辑</span></li>
					<li class="list-item"><span class="item-content" data-item="template">模板插入</span></li>
					<li class="list-item"><span class="item-content" data-item="history">历史版本</span></li>
				</ul>
				<div class="tooltip">
					<span class="icon"></span>
					<span class="tooltip-content">这是提示啊</span>
				</div>
			</div>
		</div>

		<!-- 展示帮助信息 -->
		<div class="help-container item">
			<div class="container-mask">
				<div class="help-content">
					<h1 class="help-title">特别声明</h1>
					<p class="help-summary">本系统设计的思想源于下列信息,您也可以去更详细了解</p>
					<p class="help-item">
						<a href="http://www.showdoc.cc/" class="btn">网站</a>
						<span>http://www.showdoc.cc/</span>
					</p>
					<p class="help-item">
						<a href="http://blog.star7th.com/" class="btn">博客</a>
						<span>http://blog.star7th.com/</span>
					</p>
					<p class="help-item">
						<span>邮箱</span>
						<span>xing7th@gmail.com</span>
					</p>
					<p class="help-item">showdoc是采用PHP编写的,本站采用了JSP编写,以方便那些使用JSP的人群</p>
				</div>
			</div>
		</div>
		<div class="switch-operation">
			<a href="javascript:void(0);" class="btn active">传送区</a>
			<a href="javascript:void(0);" class="btn">功能区</a>
			<a href="javascript:void(0);" class="btn">信息区</a>
		</div>

		<div class="footer">
			<p>ShowDoc遵循Apache2开源协议发布,并提供免费使用 Copyright <span class="copyright">&#169; </span>2016 by Venus  members 王港加 and 张作豪</p>
		</div>
	</div>

	<div class="login-form-container">
		<div class="login-form-pos">
			<a href="javascript:void(0);" class="bnt btn-close">&#xe117;</a>
			<p class="login-error">账号或密码错误</p>
			<h2 class="login-title">登录ShowDoc</h2>
			<form action=<%=basePath + "voucher/checkVoucher.action" %> method="post" accept-charset="utf-8" class="login-form">
				<div class="login-item">
					<label for="login-username" class="login-label">&#xe908;</label>
					<input type="text" name="username" placeholder="账号" id="login-username" class="item-content" />
				</div>
				<div class="login-item">
					<label for="login-password" class="login-label">&#xe90a;</label>
					<input type="password" name="password" placeholder="密码" id="login-password" class="item-content" /> 
				</div>
				<div class="login-item login-reset-password">
					<a href="javascript:void(0);" class="btn btn-reset-password">忘记登录密码?</a>
				</div>
				<div class="login-item login-form-opeation">
					<input type="submit" name="login" value="登录" class="form-submit" />
				</div>
				<div class="login-item login-form-other">
					<a href="index.jsp" class="btn btn-back">返回首页</a>
					<a href=<%=basePath + "jsp/login/register.jsp" %> class="btn btn-register">注册</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
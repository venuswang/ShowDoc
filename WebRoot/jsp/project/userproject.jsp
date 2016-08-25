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
	<title>showdoc创建项目</title>
	<link rel="stylesheet" href='<%=basePath + "css/basic.min.css" %>' />
	<link rel="stylesheet" href='<%=basePath + "css/userProject.min.css" %>' />
	<link rel="stylesheet" href='<%=basePath + "css/sumoselect.min.css" %>' />
	<link rel="stylesheet" href='<%=basePath + "css/create.project.min.css" %>' />
	<script src="<%=basePath + "js/jquery-1.12.3.min.js" %>"></script>
	<script src="<%=basePath + "js/jquery.sumoselect.min.js" %>"></script>
	<script src="<%=basePath + "js/jquery.validate.min.js" %>"></script>
	<script src="<%=basePath + "js/user.project.min.js" %>"></script>
</head>
<body>
	<h1 class="modify-info-result">${requestScope.messages}</h1>
	<header id="header">
		<div class="common-left">
			<h1 class="left-title">ShowDoc</h1>
		</div>
		<div class="common-middle">
			<nav class="showdoc-nav">
				<ul class="nav-list">
					<li class="nav-item" id="user-center">
						<img src="<%=local %>${sessionScope.images}" alt="user logo" class="user-portraint" />
						<ul class="sub-nav">
							<li class="sub-item">
								<a href="javascript:void(0);" class="sub-btn" data-dist="person-info">个人信息</a>
							</li>
							<li class="sub-item">
								<a href="javascript:void(0);" class="sub-btn" data-dist="modify-person-info">修改信息</a>
							</li>
							<li class="sub-item">
								<a href="javascript:void(0);" class="sub-btn" data-dist="modify-person-password">修改密码</a>
							</li>
							<li class="sub-item">
								<a href=<%=basePath + "index.jsp" %> class="sub-btn">网站首页</a>
							</li>
						</ul>			
					</li>
					<li class="nav-item" id="user-exit">
						<a href="javascript:void(0);" class="nav-btn btn-exit">退出</a>
					</li>
				</ul>
			</nav>		
		</div>
	</header><!-- /header -->
	<div class="main" id="main">
		<ul class="project-list">
			<c:forEach items="${requestScope.projects}" var="project">
				<li class="project-item">
					<a href="<%=basePath %>jsp/project/showproject.jsp?id=${project.id}&projectname=${project.projectname}&check=${project.projectpassword}" class="project-btn btn-detail">
						<p class="item-content-project">${project.projectname }</p>
						<p class="item-content-author">项目所有者&nbsp;${project.authorname }</p>
					</a>
				</li>
			</c:forEach>
			<li class="project-item project-item-add" style="vertical-align: top;">
				<a href="javascript:void(0);" class="project-btn">新建项目<span class="add-project-icon">&#xe904;</span></a>
			</li>
		</ul>
	</div>
	<div id="mask-container">
		<!-- 个人信息 -->
		<div id="person-info" data-user="${sessionScope.userid}">
			<ul class="info-list">
				<li class="info-item">
					<span class="item-name-title">用户名</span>
					<span class="item-name-content">张作豪</span>
				</li>
				<li class="info-item">
					<span class="item-email-title">邮箱</span>
					<span class="item-email-content">2456898390@qq.com</span>
				</li>
				<li class="info-item-portraint">
					<img src="" alt="user portraint" class="item-portraint-content" />
				</li>
				<li class="info-item info-item-skill">
					
					
				</li>
			</ul>
			<div class="person-info-operation">
				<a href="javascript:void(0);" class="btn btn-back">返回</a>
			</div>
		</div>

		<!-- 修改信息 -->
		<div id="modify-person-info" data-user="${sessionScope.userid}">
			<form action="${pageContext.request.contextPath }/voucher/updateVoucherInfo.action" method="post"
					enctype="multipart/form-data" accept-charset="utf-8" id="update-info-form">
				<!-- 用户名 -->
				<div class="modify-info-items">
					<input type="text" name="username" placeholder="用户名" 
							id="modify-info-username" class="form-item" disabled="disabled" />
					<input type="hidden" name="voucherid" value="${sessionScope.userid}" />	
				</div>
				<!-- 邮箱 -->
				<div class="modify-info-items">
					<label for="modify-info-email" class="modify-label">邮箱</label>
					<input type="email" name="email" placeholder="邮箱" 
							id="modify-info-email" class="form-item" />	
				</div>
				<!-- 头像 -->
				<div class="modify-info-portraint">
					<label for="modify-picture" class="modify-label">上传头像</label>
					<div class="item-img-content">
						<input type="file" name="pictures" class="item-upimg" id="modify-picture" />
						<s class="item-upimg-logo">&#xe010;</s>
						<p class="item-upimg-name">选择图片</p>
					</div>
					<div class="item-img-show">
						<span class="item-show-title">图片预览</span>
						<img src="" alt="用户头像" class="item-img-result" />
					</div>
					<p class="valid-error"></p>
				</div>
				<!-- 技能 -->
				<div class="modify-info-skill">
					<label for="modify-skill" class="modify-label">编程技能</label>
					<select name="skills" multiple="multiple" id="modify-skill"  class="SlectBox">
						<option value="Java">Java</option>
						<option value="C++">C++</option>
						<option value="VB">VB</option>
						<option value="C">C</option>
						<option value="Javascript">Javascript</option>
						<option value="Html">Html</option>
						<option value="CSS">CSS</option>
						<option value="PHP">PHP</option>
						<option value="nodejs">Nodejs</option>
						<option value="C#">C#</option>
						<option value="ASP">ASP</option>
						<option value="MySql">MySql</option>
					</select>
				</div>
				<!-- 表单操作 -->
				<div class="modify-form-operations">
					<a href="javascript:void(0);" class="btn btn-submit">确定</a>
					<!-- <input type="submit" name="submit" value="确定" class="btn btn-submit" /> -->
					<a href="javascript:void(0);" class="btn btn-cancel">取消</a>
				</div>
			</form>
		</div>

		<!-- 修改密码 -->
		<div id="modify-person-password"  data-user="${sessionScope.userid}">
			<h3 class="modify-restul-info"></h3>
			<form action="#" method="POST" accept-charset="utf-8" id="update-password-form">
				<!-- 用户名 -->
				<div class="update-form-items">
					<input type="text" name="username" placeholder="用户名" 
							id="update-username" disabled="disabled" value="zzh" class="form-item" />	
				</div>
				<!-- 用户名 -->
				<div class="update-form-items">
					<input type="password" name="password" placeholder="原密码" 
							id="update-old-password" class="form-item" />	
				</div>
				<!-- 新密码 -->
				<div class="update-form-items">
					<input type="password" name="newPassword" placeholder="新密码" 
							id="update-new-password" class="form-item" />	
				</div>
				<!-- 确认新密码 -->
				<div class="update-form-items">
					<input type="password" name="newPasswordAgain" placeholder="确认新密码" 
							id="update-new-password-again" class="form-item" />	
				</div>
				<!-- 表单操作 -->
				<div class="update-form-operations">
					<a href="javascript:void(0);" class="btn btn-submit">确定</a>
					<a href="javascript:void(0);" class="btn btn-cancel">取消</a>
				</div>
			</form>
		</div>
	</div>
	<div id="create-project-container">
		<form action="#" id="create-project-form">
			<h1 class="create-project-title">编辑项目信息</h1>
			<!-- 项目名 -->
			<div class="project-form-item">
				<input type="text" class="form-item-content projectname" placeholder="项目名" name="projectname" />
			</div>
			<!-- 项目描述 -->
			<div class="project-form-item">
				<input type="text" class="form-item-content projectdesc" placeholder="项目描述" name="projectdesc" />
			</div>
			<!-- 项目索引值 -->
			<div class="project-form-item">
				<input type="number" class="form-item-content sortid" placeholder="项目索引值(数字)" name="sortid" />
			</div>
			<!-- 项目用户名 -->
			<div class="project-form-item">
				<input type="text" class="form-item-content username" placeholder="用户名" name="authorname" value="${sessionScope.username }" disabled="disabled" />
			</div>
			<!-- 项目访问密码 -->
			<div class="project-form-item">
				<input type="password" class="form-item-content password" placeholder="访问此项目的密码,不设密码默认所有人可访问" name="projectpassword" />
			</div>
			<!-- 项目操作 -->
			<div class="project-form-operations">
				<a href="javascript:void(0);" class="btn btn-submit">确定</a>
				<a href="javascript:void(0);" class="btn btn-cancel">取消</a>
			</div>
		</form>
	</div>
</body>
</html>
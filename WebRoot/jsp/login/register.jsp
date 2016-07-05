<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>个人注册</title>
	<link rel="stylesheet" href=<%=basePath + "css/basic.min.css" %> />
	<link rel="stylesheet" href=<%=basePath + "css/register.min.css" %> />
	<link rel="stylesheet" href=<%=basePath + "css/sumoselect.min.css" %> />
	<script src=<%=basePath + "js/jquery-1.12.3.min.js" %>></script>
	<script src=<%=basePath + "js/jquery.sumoselect.min.js" %>></script>
	<script src=<%=basePath + "js/jquery.validate.min.js" %>></script>
 	<script src=<%=basePath + "js/register.min.js" %>></script>   
</head>
<body>
	<div class="container">
		<div id="header">
			<div class="common-left">
				<h1 class="left-title">ShowDoc</h1>
			</div>
			<div class="common-middle">
				<h1 class="title">欢<span class="appearence1">迎</span>注<span class="appearence2">册</span></h1>
			</div>
		</div>
		<div id="main">
			<form name="form" action="${pageContext.request.contextPath }/voucher/insertVoucherInfo.action" method="post" enctype="multipart/form-data" class="register-form" id="register-form">
				<!-- username -->
				<div class="register-item border-item">
					<label for="register-username" class="register-label label-three">&#xe074;</label>
					<input type="text" name="voucher.username" value="${voucherVO.voucher.username}" placeholder="账号 6 ~ 15位" id="register-username" class="register-content-text" />
				</div>
				<!-- password -->
				<div class="register-item border-item">
					<label for="register-password" class="register-label">&#xe007;</label>
					<input type="password" name="voucher.password" value="${voucherVO.voucher.password}" placeholder="密码 6 ~ 40位" id="register-password" class="register-content-text" />
				</div>
				<!-- check password -->
				<div class="register-item border-item">
					<label for="register-checkpwd" class="register-label">&#xe007;</label>
					<input type="password" name="passwordAgain" value="${voucherVO.voucher.password}" placeholder="确认密码" id="register-checkpwd" class="register-content-text" />
				</div>
				<!-- email -->
				<div class="register-item border-item">
					<label class="register-label label-three" for="register-email">&#xe002;</label>
					<input type="email" name="voucherInfo.email" value="${voucherVO.voucherInfo.email}" id="register-email" placeholder="邮箱" class="register-content-text" />
				</div>
				<!-- logo -->
				<div class="register-logo-item border-item">
					<label for="register-picture" class="register-label">上传头像</label>
					<div class="item-img-content">
						<input type="file" name="pictures" class="item-upimg" id="register-picture" />
						<s class="item-upimg-logo">&#xe010;</s>
					</div>
					<div class="item-img-show">
						<span class="item-show-title">图片预览</span>
						<img src="" alt="用户头像" class="item-img-result" />
					</div>
				</div>
				<!-- skill -->
				<div class="register-item-select border-item">
					<label for="register-skill" class="register-label register-label-select">编程技能</label>
					<select name="voucherInfo.skills" multiple="multiple" id="register-skill"  class="SlectBox">
						<option value="Java">Java</option>
						<option value="C++">C++</option>
						<option value="VB">VB</option>
						<option value="C">C</option>
						<option value="Javascript">Javascript</option>
						<option value="Html">Html</option>
						<option value="CSS" selected="selected">CSS</option>
						<option value="PHP">PHP</option>
						<option value="nodejs">Nodejs</option>
						<option value="C#">C#</option>
						<option value="ASP">ASP</option>
						<option value="MySql">MySql</option>
					</select>
				</div>
				<!-- 验证码操作 -->
				<div class="register-item border-item">
					<input type="text" name="vcode" placeholder="验证码" class="register-content-text" 
							id="register-checkImg" />
					<span class="checkImg-icon">&#xe901;</span>
					<img src="" alt="验证码" name="checkImg" id="show-checkImg" />
					<p class="checkImg-error-info">验证码错误</p>
				</div>
				<!-- 已有账号的情况 -->
				<div class="register-content-index">
					<a href=<%=basePath + "index.jsp" %> class="btn btn-back">返回首页</a>
				</div>
				<!-- 表单操作 -->
				<div class="register-content-opera">
					<input type="submit" value="注册" class="form-opera" id="form-submit" />
				</div>
			</form>
		</div>
	</div>
</body>
</html>

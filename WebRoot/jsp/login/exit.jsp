<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imgSrc1 = basePath + "images/icon.fw.png";
String imgSrc2 = basePath + "images/logo_title.fw.png";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ShowDoc</title>
    <link rel="icon" href="<%=basePath + "images/icon.ico" %>" type="image/x-icon" />
        <!--title旁边的logo-->
    <link rel="stylesheet" href="<%=basePath + "css/basic.min.css" %>" />
    <link rel="stylesheet" href="<%=basePath + "css/index1.css" %>" />
    <link rel="stylesheet" href=<%=basePath + "css/success.min.css" %> /> 
</head>
<body>
    <div id="container">
    <!--container主体-->
        <div class="top">
            <div class="top_content">
                <div class="logo">
                    <div class="img_logo">
                        <img src="<%=imgSrc1 %>" id="id_logo1">
                    </div>
                    <div class="img_logo">
                        <img src="<%=imgSrc2 %>" id="id_logo2">
                    </div>
                </div>
            </div>
        </div>
        <div class="login">
            <div class="login_content">
            </div>           
        </div>       
    </div>
	<div class="container" style="background-color: rgba(255, 255, 255, 0.8);">
		<h3 class="success-title">成功退出啦!</h3>
		<p class="success-info"><span class="success-time" id="success-time">5</span>秒后自动跳转到网站首页，若没有跳转，点以下按钮跳回首页</p>
		<a href="<%=basePath %>" class="btn-back">ShowDoc</a>
	</div>
    <script>
    	window.onload = function() {
            var url = window.location.protocol + "//" + 
                    window.location.host + "/ShowDoc/";
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
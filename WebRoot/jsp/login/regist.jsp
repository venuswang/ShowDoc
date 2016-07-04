<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品信息</title>
<script src=<%=basePath + "js/jquery-1.12.3.min.js" %>></script>
</head>
<body> 
<c:forEach items="${errors}" var="error">
	<c:out value="${error }"></c:out>
</c:forEach>
<form id="itemForm" action="${pageContext.request.contextPath }/voucher/insertVoucherInfo.action" method="post" enctype="multipart/form-data">

<table width="100%" border=1>
<tr>
	<td>名称</td>
	<td><input type="text" name="voucher.username" value="${voucherVO.voucher.username}"/></td>
</tr>
<tr>
	<td>商品价格</td>
	<td><input type="text" name="voucher.password" value="${voucherVO.voucher.password}"/></td>
</tr>
<tr>
	<td>商品图片</td>
	<td>
		<c:if test="${voucherVO.voucherInfo.picture !=null}">
			<img src="${voucherVO.voucherInfo.picture}" width=100 height=100/>
			<br/>
		</c:if>
		<input type="file"  name="picture"/> 
	</td>
</tr>
<tr>
	<td>email</td>
	<td>
		<input type="text" name="voucherInfo.email" value="${voucherVO.voucherInfo.email}"/>
	</td>
</tr>
<tr>
	<td>验证码</td>
	<td>
		<img src="${pageContext.request.contextPath }/voucher/getCaptchar.action" alt="验证码" name="checkImg" id="checkImg" style="position:relative; top:5px; left:20px;"/>  
	
	</td>
	<td>${sessionScope.vcode }</td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>
</body>
<script type="text/javascript">
	$(function(){
		console.log("dd");
		var url = window.location.protocol + "\/\/" + window.location.host +
		"\/ShowDoc\/" + "voucher/getVcode.action";
		console.log( url );
		$.ajax({
			url: url,
			type: "GET",
			dataType: "text",
			success: function(data) {
				console.log("kkk");
				console.log( data );
			}
		});
		
		var $img = $('#checkImg').eq(0);
		$img.on('click', function(){
			
			var srcUrl = window.location.protocol + "\/\/" + window.location.host +
						"\/ShowDoc\/" + "voucher/getCaptchar.action?temp=" + 
						(new Date().getTime().toString(36)),
				url = window.location.protocol + "\/\/" + window.location.host +
						"\/ShowDoc\/" + "voucher/getVcode.action",
				$tmpImg = $('<img />');
			
			$tmpImg.on('load', function(){
				$img.attr("src", srcUrl);
				$.ajax({
					url: url,
					type: "GET",
					dataType: "text",
					success: function(data) {
						console.log( data );
					}
				});	
			});
			$tmpImg.attr('src', srcUrl);
				return false;
		});
	});
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改商品信息</title>

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
		<img src="${pageContext.request.contextPath }/voucher/getCaptchar.action" alt="验证码" name="checkImg" id="checkImg" style="position:relative; top:5px; left:20px; " onClick="document.getElementById('checkImg').src='${pageContext.request.contextPath }/voucher/getCaptchar.action?temp='+ (new Date().getTime().toString(36)); return false"/>  
	</td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="提交"/>
</td>
</tr>
</table>

</form>
</body>

</html>
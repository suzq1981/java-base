<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
	.error{color: red;}
</style>
</head>
<body>
	<h1>用户表单验证</h1>
	<fm:form action="${pageContext.request.contextPath }/user/update" modelAttribute="user" method="post">
		<fm:label path="id">用户编号：</fm:label><fm:input path="id" /><br />
		<fm:label path="username">姓名：</fm:label><fm:input path="username" /><fm:errors cssClass="error" path="username" /><br />
		<fm:label path="age">年龄：</fm:label><fm:input path="age" /><fm:errors cssClass="error" path="age" /><br />
		<fm:label path="email">邮箱：</fm:label><fm:input path="email" /><fm:errors cssClass="error" path="email" /><br >
		<fm:label path="phone">手机：</fm:label><fm:input path="phone" /><fm:errors cssClass="error" path="phone" /><br >
		<input type="submit" value="修改">
	</fm:form>
</body>
</html>
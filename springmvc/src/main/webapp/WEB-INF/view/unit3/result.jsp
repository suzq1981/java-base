<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<h1>itlike.jsp 你好 ${name }</h1>
	<hr>
	<h2>userinfo:${sessionScope.userinfo }</h2>
	<h3>Pet:${sessionScope.pet.name }</h3>
	<h3>Age:${sessionScope.age }</h3>
</body>
</html>
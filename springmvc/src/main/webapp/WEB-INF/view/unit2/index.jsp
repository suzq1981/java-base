<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<h1>Unit 2 第二单元</h1>
	<form action="${pageContext.request.contextPath }/unit2/convert">
		年龄：<input type="text" name="age"><br>
		日期：<input type="text" name="date"><br>
		<input type="submit" value="提交">
	</form>
	<br>
	<a href="${pageContext.request.contextPath }/unit2/headers">headers</a><br />
	<a href="${pageContext.request.contextPath }/unit2/antpath/0/invoke">ant path</a><br />
	<a href="${pageContext.request.contextPath }/unit2/rest/9001">Rest</a><br />
	<form action="${pageContext.request.contextPath }/unit2/delput/266478" method="post">
		<input type="hidden" name="_method" value="put">
		<input type="submit" value="Put-Delete">
	</form>
	<a href="${pageContext.request.contextPath }/unit2/reqheader">Request Header</a><br />
	<a href="${pageContext.request.contextPath }/unit2/cookie">Cookie</a>
</body>
</html>
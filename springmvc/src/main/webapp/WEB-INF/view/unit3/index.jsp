<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<h1>Unit 3第三单元</h1>
	<h2>${name }</h2>
	<hr>
	<a href="${pageContext.request.contextPath }/unit3/testSession">测试session</a>
	<br /><br />
	<form action="${pageContext.request.contextPath }/unit3/testModelAttribute" method="post">
		员工ID：<input type="text" name="empId" /><br />
		姓名：<input type="text" name="empName" /><br />
		<input type="submit" value="保存">
	</form>
</body>
</html>
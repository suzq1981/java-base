<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Spring Boot JSP View</title>
	</head>
	
	<body>
		<h1>网站功能列表</h1>
		<form action="${pageContext.request.contextPath }/myform" method="post">
			用户ID：<input type="text" name="userId" /><br />
			姓名：<input type="text" name="userName" /><br />
			性别：<input type="text" name="gender" /><br />
			<input type="submit" value="保存">
		</form>
		<br>
		<hr>
		<form action="${pageContext.request.contextPath }/convert" method="post">
			日期：<input type="text" name="date" /><br />
			<input type="submit" value="保存">
		</form>
		<br />
		<form action="${pageContext.request.contextPath }/delput/99809" method="post">
			<input type="hidden" name="_method" value="put">
			<input type="submit" value="Put-Delete">
		</form>
		<br />
		<a href="${pageContext.request.contextPath }/viewc">View Controller</a><br />
		<a href="${pageContext.request.contextPath }/viewc2">View Controller2</a><br />
	</body>
</html>
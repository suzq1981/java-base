<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<a href="${pageContext.request.contextPath }/first">跳转</a><br />
	<a href="${pageContext.request.contextPath }/second?empId=9008">第二个</a><br />
	<a href="${pageContext.request.contextPath }/third?empId=9008&empName=God's son">第三个</a><br />
	<form action="${pageContext.request.contextPath }/myform" method="post">
		员工ID：<input type="text" name="empId" /><br />
		姓名：<input type="text" name="empName" /><br />
		性别：<input type="text" name="gender" /><br />
		爱好：足球<input name="hobbies" type="checkbox" value="football" checked="checked">
			篮球<input name="hobbies" type="checkbox" value="basketball" checked="checked">
			<br />
	--------------------宠物--------------------
	<br>
	宠物名：<input type="text" name="pet.name"><br>
	颜色：<input type="text" name="pet.color"><br>
	--------------------宠物列表--------------------
	<br>
	宠物名：<input type="text" name="pets[0].name"><br>
	颜色：<input type="text" name="pets[0].color"><br>
	宠物名：<input type="text" name="pets[1].name"><br>
	颜色：<input type="text" name="pets[1].color"><br>
		
		<input type="submit" value="保存">
	</form>
	<br />
	<form action="${pageContext.request.contextPath }/myform2" method="post">
		足球<input name="hobbies" type="checkbox" value="football" checked="checked">
			篮球<input name="hobbies" type="checkbox" value="basketball" checked="checked">
			<br />
		<input type="submit" value="保存">
	</form>
</body>
</html>
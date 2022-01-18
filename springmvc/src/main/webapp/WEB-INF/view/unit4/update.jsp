<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<h1>第四单元 result.jsp 你好</h1>
	<fm:form action="${pageContext.request.contextPath }/unit4/update" modelAttribute="emp" method="post">
		<fm:label path="empId">员工编号：</fm:label><fm:input path="empId" /><br />
		<fm:label path="empName">员工姓名：</fm:label><fm:input path="empName" /><br />
		<fm:label path="gender">性别：</fm:label><fm:radiobutton path="gender" value="M" label="男"/><fm:radiobutton path="gender" value="F" label="女"/> <br />
		<fm:label path="gender">爱好：</fm:label><fm:checkboxes items="${hobbys}" path="hobbies" itemLabel="label" itemValue="value" /><br >
		<fm:label path="gender">宠物：</fm:label>
		<fm:select path="pet.petId">
			<fm:option value="0" label=""></fm:option>
			<fm:options items="${pets }" itemLabel="name" itemValue="petId"/>
		</fm:select> <br >
		<input type="submit" value="修改">
	</fm:form>
</body>
</html>
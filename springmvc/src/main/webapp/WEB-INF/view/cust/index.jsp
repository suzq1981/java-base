<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<h1>jstl core 标签</h1>
	<form action="${pageContext.request.contextPath }/cust/update" method="post">
		<label>编号：</label><input type="text" name="id" value="${customer.id }"><br>
		<label>姓名：</label><input type="text" name="name" value="${customer.name }"><br>
		<label>性别：</label>
		<input type="radio" name="gender" <c:if test="${customer.gender eq 'M'}">checked="checked"</c:if> value="M">男
		<input type="radio" name="gender" <c:if test="${customer.gender eq 'F'}">checked="checked"</c:if> value="F">女
		<br>
		<label>宠物：</label>
		<select name="pet.id" >
			<c:forEach items="${pets}" var="p">
				<option value="${p.id }" <c:if test="${customer.pet.id==p.id }">selected="selected"</c:if> > ${p.name }</option>
			</c:forEach>
		</select>
		<br>
		<label>爱好：</label>
		<c:forEach items="${hobbys }" var="h">
			<c:set var="cnt" value="false"></c:set>
			<c:forEach items="${customer.hobbys }" var="hobby">
				<c:if test="${h.value == hobby }">
					<c:set var="cnt" value="true"></c:set>
				</c:if>
			</c:forEach>
			<input type="checkbox" name="hobbys" <c:if test="${cnt}">checked="checked"</c:if> value="${h.value }">${h.label}
		</c:forEach>
		<br>
		<input type="submit" value="提交表单">
	</form>
</body>
</html>
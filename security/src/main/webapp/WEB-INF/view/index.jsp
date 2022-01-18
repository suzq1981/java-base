<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
	<head>
		<title>Spring Boot JSP View</title>
	</head>
	
	<body>
		<h1>网站功能列表</h1>
		<sec:authorize access="hasAuthority('/goods/add')">
		<a href="${pageContext.request.contextPath}goods/add">商品添加</a><br />
		</sec:authorize>
		<sec:authorize access="hasAuthority('/goods/delete')">
		<a href="${pageContext.request.contextPath}goods/delete">商品删除</a><br />
		</sec:authorize>
		<sec:authorize access="hasAuthority('/goods/update')">
		<a href="${pageContext.request.contextPath}goods/update">商品修改</a><br />
		</sec:authorize>
		<sec:authorize access="hasAuthority('/goods/list')">
		<a href="${pageContext.request.contextPath}goods/list">商品查询</a><br />
		</sec:authorize>
		<sec:authorize access="hasAuthority('/dba/add')">
		<a href="${pageContext.request.contextPath}dba/add">dba添加</a><br />
		</sec:authorize>
		<sec:authorize access="hasAuthority('/admin/add')">
		<a href="${pageContext.request.contextPath}admin/add">admin添加</a><br />
		</sec:authorize>
		<a href="${pageContext.request.contextPath}logout">退出</a><br />
	</body>
</html>
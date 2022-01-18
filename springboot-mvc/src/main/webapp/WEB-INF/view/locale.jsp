<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<a href="?language=zh_CN"><fmt:message key="language.cn" /></a>&nbsp;&nbsp;
	<a href="?language=en_US"><fmt:message key="language.en" /></a>
	<h1><fmt:message key="welcome" /></h1>
	<h2><fmt:message key="name" /></h2>
	<fmt:formatNumber maxFractionDigits="2" value="12.09987"></fmt:formatNumber>
	<br/>
</body>
</html>
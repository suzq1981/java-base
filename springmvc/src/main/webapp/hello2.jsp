<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/easyui/jquery.min.js"></script>
<!-- <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.serializejson.js"></script> -->
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/serializejson.js"></script>
</head>
<body>
	<h1>你好 ${username }</h1>
	<br/>
	<form action="${pageContext.request.contextPath }/userinfo/upload" enctype="multipart/form-data" method="post">
		<label>文件1：</label><input type="file" name="files"><br>
		<label>文件2：</label><input type="file" name="files"><br>
		<label>姓名：</label><input type="text" name="username"><br>
		<input type="submit" value="表单提交">
	</form>
	<br/>
</body>
</html>
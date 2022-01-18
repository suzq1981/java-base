<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<title>Spring Boot JSP View</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/easyui/jquery.min.js"></script>
	</head>
	
	<body>
		<h1>mvc登录页面</h1>
		<form id="fmLogin" method="post">
			用户名：<input type="text" name="username" /><br />
			密 码：<input type="password" name="password"><br />
			验证码：<input type="text" name="imageCode"><img src="/imageCode"><br />
			<!-- 记住我：<input type="checkbox" name="remember-me" checked="checked" /><br /> -->
			<input type="button" id="btnLogin" value="登录" />
		</form>
	</body>
	<script type="text/javascript">
	$(function(){
	    $('#btnLogin').click(function(){
	         $.ajax({
	             type: "POST",
	             url: "${pageContext.request.contextPath}/springLogin",
	             data: $("#fmLogin").serialize(),
	             success: function(data){
	             	if(data.success == true){
	             		window.location.href = "${pageContext.request.contextPath}/index"
	             	}else{
	             		alert(data.message);
	             		location.reload();
	             	}
	             }
	         });
	    });
	});
	</script>
</html>
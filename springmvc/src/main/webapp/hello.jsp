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
	<form id="fmUser">
		<label>用户编号：</label><input type="text" name="id" value="8806"><br />
		<label>用户名：</label><input type="text" name="username" value="苏知育"><br />
		<label>年龄：</label><input type="text" name="age" value="8"><br />
		<label>邮箱：</label><input type="text" name="email" value="suzy0405@qq.com"><br />
		<label>手机：</label><input type="text" name="phone" value="15121073881"><br />
		<!-- <label>爱好：</label><input type="checkbox"  name="hobby[]" value="篮球">篮球 <input type="checkbox" name="hobby[]" value="足球">足球<br /> -->
		<label>爱好：</label><input type="checkbox"  name="hobby" value="篮球">篮球 <input type="checkbox" name="hobby" value="足球">足球<br />
		<input type="button" id="btnUser" value="用户信息提交">
	</form>
	<br/>
	<form action="${pageContext.request.contextPath }/user/upload" enctype="multipart/form-data" method="post">
		<label>文件：</label><input type="file" name="file"><br>
		<label>姓名：</label><input type="text" name="username"><br>
		<label>年龄：</label><input type="text" name="age"><br>
		<input type="submit" value="表单提交">
	</form>
	<br/>
	<a href="${pageContext.request.contextPath }/user/download/202112/ubuntu首次设置root.txt">ubuntu首次设置root</a>
	<br/>
	<a href="${pageContext.request.contextPath }/exception">exception</a>
	<br/>
	<a href="${pageContext.request.contextPath }/locale">locale</a>
	<br/>
	<a href="${pageContext.request.contextPath }/locale2">locale2</a>
	<br/>
	
	<input type="button" id="btn" value="请求JSON数据">
	<br />
	<input type="button" id="gateway" value="跨域请求">
	<script type="text/javascript">
		$(function(){
			$("#gateway").click(function(){
				$.getJSON("http://192.168.0.103:1000/ums/user/1",function(data){
					alert(data.username);
				});
			});
			
			$("#btn").click(function(){
				$.getJSON("${pageContext.request.contextPath }/user/get/9008",function(data){
					if(data.length > 0){
						$.each(data, function(index, value){
							console.log(value);
						})
					}
				});
			});
			
			$("#btnUser").click(function(){
				/**$.post("${pageContext.request.contextPath}/user/put", $("#fmUser").serializeJSON(), function(data){
					console.log(data)
				});
				*/
				var formData = $("#fmUser").serializeJSON();
				if(typeof formData.hobby == "string"){
					formData.hobby = new Array(formData.hobby);
				}
				console.log(formData)
				$.ajax({
					url: "${pageContext.request.contextPath}/user/put",
					type: "post",
					contentType: "application/json",
					data: JSON.stringify(formData),
					success: function(data){
						console.log(data);
					}
				});
			});
		})
	</script>
</body>
</html>
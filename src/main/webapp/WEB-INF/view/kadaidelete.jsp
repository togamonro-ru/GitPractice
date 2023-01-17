<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="dto.kadaiDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>削除画面</h1>
	<form action="KadaiDeleteServlet" method="post">
		<p>削除したいアカウントの名前を入力してください</p>
		名前：<input type="text" name="name"><br>
		<input type="submit" value="削除">
	</form>
</body>
</html>
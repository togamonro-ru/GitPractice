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
  <%
    kadaiDTO name = (kadaiDTO)session.getAttribute("name");
  %>
<h1>ログイン成功！</h1><br>
<p>ようこそ<%=name.getName() %>さん！</p><br>
<a href="./">戻る</a>
</body>
</html>
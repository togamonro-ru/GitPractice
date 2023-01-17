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
	<p>下記の内容で登録します。よろしいですか？</p>
	<%
		kadaiDTO account = (kadaiDTO)session.getAttribute("data");
	%>
	名前：<%=account.getName() %><br>
	メール：<%=account.getMail() %><br>
	性別：<%=account.getGender() %><br>
	年齢：<%=account.getAge() %><br>
	電話番号：<%=account.getTell()%><br>
	パスワード：********<br>
	<a href="KadaiExecuteServlet">OK</a><br>
	<a href="Kadai1FormServlet">戻る</a>
</body>
</html>
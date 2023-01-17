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
		request.setCharacterEncoding("UTF-8");
		String errorCode = request.getParameter("error");
		if(errorCode != null && errorCode.equals("1")){
			kadaiDTO ac = (kadaiDTO)session.getAttribute("data");
	%>
		<p style="color:red">登録に失敗しました。</p>
		<h3>新規会員登録</h3>
		<form action="KadaiConfirmServlet" method="post">
			名前：<input type="text" name="name" value="<%=ac.getName()%>"><br>
			性別：<br>
		    <input type="radio" name="gender" value="男">男
		    <input type="radio" name="gender" value="女">女<br>
		    年齢：<input type="number" name="age" value="<%=ac.getAge() %>"><br>
			電話番号：<input type="tel" name="tell" value="<%=ac.getTell() %>"><br>
			メール：<input type="email" name="email" value="<%=ac.getMail()%>"><br>
			パスワード：<input type="password" name="pw"><br>
			<input type="submit" value="登録">
		</form>
	<%
		} else {
	%>
	<h3>新規会員登録</h3>
	<form action="KadaiConfirmServlet" method="post">
		名前：<input type="text" name="name"><br>
		性別：<br>
		<input type="radio" name="gender" value="男">男
		<input type="radio" name="gender" value="女">女<br>
		年齢：<input type="number" name="age"><br>
		電話番号：<input type="tel" name="tell">※例：0120-333-906<br>
		メール：<input type="email" name="email"><br>
		パスワード：<input type="password" name="pw"><br>
		<input type="submit" value="登録">
	</form>
	<%
		}
	%>
</body>
</html>
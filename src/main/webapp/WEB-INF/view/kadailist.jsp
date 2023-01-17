<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dto.kadaiDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>名前</th>
			<th>性別</th>
			<th>年齢</th>
			<th>電話番号</th>
			<th>メールアドレス</th>
		</tr>
	<%
	List<kadaiDTO> list = (ArrayList<kadaiDTO>)request.getAttribute("list");
	for(kadaiDTO s : list) {
	%>
		<tr>
			<td><%=s.getId() %></td>
			<td><%=s.getName() %></td>
			<td><%=s.getGender() %></td>
			<td><%=s.getAge() %></td>
			<td><%=s.getTell() %></td>
			<td><%=s.getMail() %></td>
		</tr>
	<%} %>
	</table>
	
	<a href="./">戻る</a>
</body>
</html>
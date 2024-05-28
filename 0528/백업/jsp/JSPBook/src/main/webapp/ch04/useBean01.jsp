<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="date" class="java.util.Date" />
	<p>
	<%
		java.util.Date date2 = new java.util.Date();
		out.print("오늘의 날짜 및 시간");
	%>
	</p>
	<p><%=date.toLocaleString() %>
	<p><%=date2.toLocaleString() %>
</body>
</html>








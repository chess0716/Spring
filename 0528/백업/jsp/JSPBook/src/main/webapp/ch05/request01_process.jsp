<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8"); // 한글이 깨지지않도록 인코딩 설정
		String userid = request.getParameter("id");
		String password = request.getParameter("pw");
	%>
	<p> 아 이 디 : <%=userid %>
	<p> 비밀번호 : <%=password %>
</body>
</html>
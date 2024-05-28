<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="bean" class="ch04.com.dao.Calculator" />
	<% 
		int m = bean.process(5);
		out.println("5의 3제곱 : "+m);
		
		ch04.com.dao.Calculator bean2 = new ch04.com.dao.Calculator();
		int m2 = bean.process(7);
		out.println("7의 3제곱 : "+m2);
	%>
</body>
</html>









<%@page import="java.text.NumberFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>현제 로케일의 국가 , 날짜, 통화</h3>
	<%
		//response.setHeader("content-Languge","es");
		//Locale locale = request.getLocale();
		Locale locale = new Locale("en");
		Date currentDate = new Date();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
		NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
	%>
	<p> 국가 : <%= locale.getDisplayCountry() %>
	<p> 국가 : <%= locale.getCountry() %>
	<p> 국가 : <%= locale.getDefault() %>
	<p> 날짜 : <%= dateFormat.format(currentDate) %>
	<p> 숫자(12345.67) : <%=numberFormat.format(12345.67) %> 
</body>
</html>












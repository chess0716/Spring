<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="person" class="ch04.com.dao.Person" scope="request"/>
	
	<jsp:setProperty name="person" property="id" value="20182005"/>
	<jsp:setProperty name="person" property="name" value="홍길동"/>
	<p> 아이디 : <jsp:getProperty name="person" property="id"/>
	<p> 이 름 : <jsp:getProperty name="person" property="name"/>
	
	<%-- <%
		person.setId(20182005);
		person.setName("홍길동");
	%>
	<p> 아이디 : <%=person.getId() %>
	<p> 이 름 : <%=person.getName() %>  --%>
</body>
</html>














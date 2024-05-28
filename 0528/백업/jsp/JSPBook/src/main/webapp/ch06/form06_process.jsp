<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>요청 파라미터 이름</th>
			<th>요청 파라미터 값</th>
		</tr>
		<% 
			request.setCharacterEncoding("UTF-8");
			Enumeration paramNames = request.getParameterNames();
			while(paramNames.hasMoreElements()){
				String name = (String)paramNames.nextElement();
				out.print("<tr><td>"+name+"</td>\n");
				String paramValue = request.getParameter(name);
				out.print("<td>"+paramValue+"</td></tr>\n");
			}
		%>
	</table>
	
</body>
</html>










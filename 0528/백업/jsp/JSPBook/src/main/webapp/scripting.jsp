<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%! //선언문태그 
int count = 3;
String makeItLower(String data){
	return data.toLowerCase();
}
%> 
<% //스크립틀릿 태그
	for(int i=1; i<=count; i++){
		out.println("Java Server Pages" + i + ".<br>");
	}
%>
<!-- 표현문 태그 : 세미콜론(;)을 적지 않는다-->
<%= makeItLower("Hello World") %>
</body>
</html>









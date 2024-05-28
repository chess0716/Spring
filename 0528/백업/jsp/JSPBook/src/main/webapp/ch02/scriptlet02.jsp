<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Scripting Tag</title>
</head>
<body>
	<%-- 0~10까지 짝수만 출력하는 반복문 --%>
	<%
		//0~10까지 짝수만 출력하는 반복문
		for(int i=0; i<=10; i++){
			if(i%2==0){
				out.println(i+"<br>");
			}
		}
	%>
</body>
</html>
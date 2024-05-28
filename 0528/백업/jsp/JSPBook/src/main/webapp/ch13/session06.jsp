<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>---세션을 삭제하기 전---</h4>
	<%
		String user_id = (String)session.getAttribute("userID");
		String user_pw = (String)session.getAttribute("userPW");
		
		out.println("설정된 세션의 이름 userID : "+ user_id + "<br>");
		out.println("설정된 세션의 값 userPW: "+ user_pw + "<br>");
		
		if(request.isRequestedSessionIdValid() == true){
			out.print("세션이 유효합니다.");
		}else{
			out.print("세션이 유효하지 않습니다.");
		}
		
		session.invalidate();
	%>
	<h4>---세션을 삭제한 후---</h4>
	<%
	user_id = (String)session.getAttribute("userID");
	user_pw = (String)session.getAttribute("userPW");
	out.println("설정된 세션의 이름 userID : "+ user_id + "<br>");
	out.println("설정된 세션의 값 userPW: "+ user_pw + "<br>");
		if(request.isRequestedSessionIdValid() == true){
			out.print("세션이 유효합니다.");
		}else{
			out.print("세션이 유효하지 않습니다.");
		}
	%>
</body>
</html>













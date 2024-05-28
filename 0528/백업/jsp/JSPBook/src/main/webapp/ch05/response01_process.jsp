<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8"); // 한글이 깨지지않도록 인코딩 설정
	String userid = request.getParameter("id");
	String password = request.getParameter("pw");
	if(userid.equals("관리자") && password.equals("1234")){
		response.sendRedirect("response01_success.jsp");
	}else{
		response.sendRedirect("response01_failed.jsp");
	}
%>
















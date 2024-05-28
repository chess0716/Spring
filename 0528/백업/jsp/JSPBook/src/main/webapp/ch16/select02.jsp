<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="dbconn.jsp" %>
	<table width="300" border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
		</tr>
		<%
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try{
			String sql = "SELECT * FROM member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				String id = rs.getString("id");
				String pw = rs.getString("passwd");
				String name = rs.getString("name");
		
		%>
		<tr>
			<td><%=id %></td>
			<td><%=pw %></td>
			<td><%=name %></td>
		</tr>
		<%
			}
		}catch(SQLException ex){
			out.println("Member 테이블 삽입이 실패했습니다.");
			out.println("SQLException : "+ex.getMessage());
		}finally{
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
		%>
	</table>
	
</body>
</html>











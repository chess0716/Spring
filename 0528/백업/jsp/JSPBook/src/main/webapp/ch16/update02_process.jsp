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
	<%@ include file="dbconn.jsp"%>

	<%
	request.setCharacterEncoding("UTF-8");
	
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
	String name = request.getParameter("name");
	
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	try {
		String sql = "SELECT id,passwd FROM member WHERE id = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();

		if(rs.next()) {
			String rId = rs.getString("id");
			String rPasswd = rs.getString("passwd");
			if(id.equals(rId) && passwd.equals(rPasswd)){
				sql = "UPDATE member SET name = ? WHERE id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,name);
				pstmt.setString(2,id);
				pstmt.executeUpdate();
				out.println("Member 테이블을 수정했습니다.");
			}else{
				out.println("일치하는 비밀번호가 아닙니다.");
			}
		}else{
			out.println("Member 테이블에 일치하는 아이디가 없습니다.");
		}
	} catch (SQLException ex) {
		out.println("SQLException : " + ex.getMessage());
	} finally {
		if(rs != null){
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
	%>

</body>
</html>









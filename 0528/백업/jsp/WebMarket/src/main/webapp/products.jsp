<%@page import="java.sql.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>상품 목록</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">상품 목록</h1>
		</div>
	</div>
	<div class="container">
		<div class="row" align="center">
	<%@ include file="dbconn.jsp" %>
	<%
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM product";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
	%>
			<div class="col-md-4">
				<img src="/upload/<%=rs.getString("p_filename") %>" style="width:100%">
				<h3><%=rs.getString("p_name") %></h3>
				<p><%=rs.getString("p_description")%></p>
				<p><%=rs.getString("p_unitPrice")%>원</p>
				<p><a href="./product.jsp?id=<%=rs.getString("p_id") %>"
					class="btn btn-secondary" role="button">상세 정보&raquo;</a></p>
			</div>
		<%  
			} 
			if(rs != null){
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		%>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>









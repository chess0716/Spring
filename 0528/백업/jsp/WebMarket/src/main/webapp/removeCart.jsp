<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Product"%>
<%@page import="dao.ProductRepository"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="dbconn.jsp" %>
<%
	String id = request.getParameter("id");
	if(id==null || id.trim().equals("")){
		response.sendRedirect("products.jsp");
		return;
	}
	
	String sql = "SELECT * FROM product WHERE p_id = ?";
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, id);
	ResultSet rs = pstmt.executeQuery();
	if(rs == null){
		response.sendRedirect("exceptionNoProductId.jsp");
	}
	
	ArrayList<Product> cartList = (ArrayList<Product>)session.getAttribute("cartlist");
	Product goodsQnt = new Product();
	for(int i=0; i<cartList.size(); i++){
		goodsQnt = cartList.get(i);
		if(goodsQnt.getProductId().equals(id)){
			cartList.remove(goodsQnt);
		}
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
	response.sendRedirect("cart.jsp");
%>
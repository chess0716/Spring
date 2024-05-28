<%@page import="dto.Product"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="dbconn.jsp" %>
<%
	String id = request.getParameter("id");
	if(id == null || id.trim().equals("")){
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
	
	sql = "SELECT * FROM product";
	pstmt = conn.prepareStatement(sql);
	rs = pstmt.executeQuery();
	Product goods = new Product();
	while(rs.next()){
		if(rs.getString("p_id").equals(id)){
			goods.setProductId(rs.getString("p_id"));
			goods.setPname(rs.getString("p_name"));
			goods.setUnitPrice(rs.getInt("p_unitPrice"));
			goods.setDescription(rs.getString("p_description"));
			goods.setCategory(rs.getString("p_category"));
			goods.setManufacturer(rs.getString("p_manufacturer"));
			goods.setUnitsInStock(rs.getLong("p_unitsInStock"));
			goods.setCondition(rs.getString("p_condition"));
			goods.setFilename(rs.getString("p_filename"));
			break;
		}
	}
	
	ArrayList<Product> list = (ArrayList<Product>)session.getAttribute("cartlist");
	if(list == null){
		list = new ArrayList<Product>();
		session.setAttribute("cartlist", list);
	}
	
	int cnt = 0;
	Product goodsQnt = new Product();
	
	for(int i=0; i<list.size(); i++){
		goodsQnt = list.get(i);
		if(goodsQnt.getProductId().equals(id)){
			cnt++;
			int orderQuantity = goodsQnt.getQuantity() + 1;
			goodsQnt.setQuantity(orderQuantity);
		}
	}
	
	if(cnt == 0){
		goods.setQuantity(1);
		list.add(goods);
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
	
	response.sendRedirect("product.jsp?id="+id);

%>

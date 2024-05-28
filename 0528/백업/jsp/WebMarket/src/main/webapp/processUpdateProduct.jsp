<%@page import="java.sql.*"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="dbconn.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");
	
	//String filename = "";
	String realFolder = "C:\\upload2";
	int maxSize = 5*1024*1024;
	String encType = "UTF-8";
	
	MultipartRequest multi = new MultipartRequest(request, 
			realFolder, maxSize, encType, new DefaultFileRenamePolicy());

	String productId = multi.getParameter("productId");
	String name = multi.getParameter("name");
	String unitPrice = multi.getParameter("unitPrice");
	String description = multi.getParameter("description");
	String manufacturer = multi.getParameter("manufacturer");
	String category = multi.getParameter("category");
	String unitsInStock = multi.getParameter("unitsInStock");
	String condition = multi.getParameter("productId");
	
	Integer price;
	if(unitPrice.isEmpty()){
		price=0;
	}else{
		price = Integer.valueOf(unitPrice);
	}
	
	long stock;
	if(unitPrice.isEmpty()){
		stock=0;
	}else{
		stock=Long.valueOf(unitsInStock);
	}
	
	Enumeration files = multi.getFileNames();
	String fname = (String)files.nextElement();
	String filename = multi.getFilesystemName(fname);
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = "SELECT * FROM product WHERE p_id = ?";
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, productId);
	rs = pstmt.executeQuery();
	if(rs.next()){
		if(filename != null){
			sql = "UPDATE product SET p_name=?, p_unitPrice=? ,p_description = ?,p_category = ?,p_manufacturer = ?,p_unitsInStock = ?,p_condition = ?,p_filename = ? WHERE p_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setInt(2,price);
			pstmt.setString(3,description);
			pstmt.setString(4,category);
			pstmt.setString(5,manufacturer);
			pstmt.setLong(6,stock);
			pstmt.setString(7,condition);
			pstmt.setString(8,filename);
			pstmt.setString(9,productId);
			pstmt.executeUpdate();
		}else{
			sql = "UPDATE product SET p_name=?, p_unitPrice=? ,p_description = ?,p_category = ?,p_manufacturer = ?,p_unitsInStock = ?,p_condition = ? WHERE p_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,name);
			pstmt.setInt(2,price);
			pstmt.setString(3,description);
			pstmt.setString(4,category);
			pstmt.setString(5,manufacturer);
			pstmt.setLong(6,stock);
			pstmt.setString(7,condition);
			pstmt.setString(8,productId);
			pstmt.executeUpdate();
		}
	}
	if(rs != null){
		rs.close();
	}
	if(pstmt != null){
		pstmt.close();
	}
	if(conn != null){
		conn.close();
	}
	
	response.sendRedirect("editProduct.jsp?edit=update");
	
%>









<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String shipping_cartId = "";
	String shipping_shippingDate = "";
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(int i=0; i<cookies.length; i++){
			Cookie thisCookie = cookies[i];
			String n = thisCookie.getName();
			if(n.equals("Shipping_cartId")){
				shipping_cartId = URLDecoder.decode(thisCookie.getValue(),"utf-8");
				thisCookie.setMaxAge(0);
			}
			if(n.equals("Shipping_name")){
				thisCookie.setMaxAge(0);
			}
			if(n.equals("Shipping_shippingDate")){
				shipping_shippingDate = URLDecoder.decode(thisCookie.getValue(),"utf-8");
				thisCookie.setMaxAge(0);
			}
			if(n.equals("Shipping_country")){
				thisCookie.setMaxAge(0);
			}
			if(n.equals("Shipping_zipCode")){
				thisCookie.setMaxAge(0);
			}
			if(n.equals("Shipping_addressName")){
				thisCookie.setMaxAge(0);
			}
			response.addCookie(thisCookie);
		}
	}
	session.invalidate();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>주문 완료</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">주문 완료</h1>
		</div>
	</div>
	<div class="container">
		<h2 class="alert alert-danger">주문해주셔서 감사합니다.</h2>
		<p> 주문은 <%=shipping_shippingDate %>에 배송될 예정입니다!
		<p> 주문번호 : <%=shipping_cartId %>
	</div>
	<div class="container">
		<p><a href="./products.jsp" class="btn btn-secondary"> &laquo; 상품 목록</a>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>


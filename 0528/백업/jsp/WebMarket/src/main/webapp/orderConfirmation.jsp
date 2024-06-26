<%@page import="dto.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String cartId = session.getId();
	
	String shipping_cartId = "";
	String shipping_name = "";
	String shipping_shippingDate = "";
	String shipping_country = "";
	String shipping_zipCode = "";
	String shipping_addressName = "";
	
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(int i=0; i<cookies.length; i++){
			Cookie thisCookie = cookies[i];
			String n = thisCookie.getName();
			if(n.equals("Shipping_cartId")){
				shipping_cartId = URLDecoder.decode(thisCookie.getValue(),"utf-8");
			}
			if(n.equals("Shipping_name")){
				shipping_name = URLDecoder.decode(thisCookie.getValue(),"utf-8");
			}
			if(n.equals("Shipping_shippingDate")){
				shipping_shippingDate = URLDecoder.decode(thisCookie.getValue(),"utf-8");
			}
			if(n.equals("Shipping_country")){
				shipping_country = URLDecoder.decode(thisCookie.getValue(),"utf-8");
			}
			if(n.equals("Shipping_zipCode")){
				shipping_zipCode = URLDecoder.decode(thisCookie.getValue(),"utf-8");
			}
			if(n.equals("Shipping_addressName")){
				shipping_addressName = URLDecoder.decode(thisCookie.getValue(),"utf-8");
			}
		}
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>주문 정보</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">주문 정보</h1>
		</div>
	</div>
	<div class="container col-8 alert alert-info">
		<div class="text-center">
			<h1>영수증</h1>
		</div>
		<div class="row justify-content-between">
			<div class="col-4" align="left">
				<strong>배송 주소</strong> <br> 
				성명 : <%=shipping_name%><br>
				우편번호 : <%=shipping_zipCode%><br>
				주소 : <%=shipping_addressName%><br>
				(<%=shipping_country %>)<br>
			</div>
			<div class="col-4" align="right">
				<p><em>배송일: <%=shipping_shippingDate %></em>
			</div>
		</div>
		<div>
			<table class="table table-hover">
				<tr>
					<th class="text-center">도서</th>
					<th class="text-center">#</th>
					<th class="text-center">가격</th>
					<th class="text-center">소계</th>
				</tr>
				<%
					int sum = 0;
					ArrayList<Product> cartList = (ArrayList<Product>)session.getAttribute("cartlist");
					if(cartList == null){
						cartList = new ArrayList<Product>();
					}
					for(int i=0; i<cartList.size(); i++){
						Product product = cartList.get(i);
						int total = product.getUnitPrice() * product.getQuantity();
						sum = sum+total;
					
				%>
				<tr>
					<td class="text-center"><em><%=product.getPname() %></em>
					<td class="text-center"><%=product.getQuantity() %>
					<td class="text-center"><%=product.getUnitsInStock() %>
					<td class="text-center"><%=total %>
				</tr>
				<%}%>
				<tr>
					<td></td>
					<td></td>
					<td class="text-right"><strong>총액: </strong></td>
					<td class="text-center text-danger"><strong><%=sum%></strong></td>
				</tr>
			</table>
			<a href="./shippingInfo.jsp?cartId=<%=shipping_cartId%>" class="btn btn-secondary" role="button"> 이전 </a>
			<a href="./thankCustomer.jsp" class="btn btn-success" role="button"> 주문 완료 </a>
			<a href="./checkOutCancelled.jsp" class="btn btn-secondary" role="button"> 취소 </a>		
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>










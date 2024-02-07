<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="include/header.jsp"%>
<div class="container mt-3">

	<c:forEach items="${fileArr }" var="file">
				fileName : ${file } <br />
	</c:forEach>
	
</div>




<%@ include file="include/footer.jsp"%>
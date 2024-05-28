<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="format" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p><jsp:useBean id="now" class="java.util.Date" />
	<p><format:formatDate value="${now}" type="date"/>
	<p><format:formatDate value="${now}" type="time"/>
	<p><format:formatDate value="${now}" type="both"/>
	<p><format:formatDate value="${now}" type="both" dateStyle="default" timeStyle="default"/>
	<p><format:formatDate value="${now}" type="both" dateStyle="short" timeStyle="short"/>
	<p><format:formatDate value="${now}" type="both" dateStyle="medium" timeStyle="medium"/>
	<p><format:formatDate value="${now}" type="both" dateStyle="long" timeStyle="long"/>
	<p><format:formatDate value="${now}" type="both" dateStyle="full" timeStyle="full"/>
	<p><format:formatDate value="${now}" type="both" pattern="yyyy년MM월dd일 HH시mm분ss초 E요일"/>
</body>
</html>





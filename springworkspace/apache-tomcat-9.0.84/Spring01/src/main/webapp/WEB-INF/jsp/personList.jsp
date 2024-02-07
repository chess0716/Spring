<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Person List</h3>
<c:forEach items="${plist }" var="person">
이름 : ${person.name } <br/>
아이디 : ${person.id } <br/>
성별 : ${person.gender } <br/>
직업 : ${person.job } <br/> <br/>
</c:forEach>
 <button type="button" onclick="location.href='personUpdateForm.sp?id=${person.id}'">수정폼</button>
        <button type="button" onclick="location.href='personDelete.sp?id=${person.id}'">삭제</button>


</body>
</html>
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
<h3>Person List(${count})</h3>
<form action="list">
    <select name="field">
        <option value="name">이름</option>
        <option value="job">직업</option>
    </select>
    <input type="text" name="word">
    <input type="submit" value="검색">
</form>
<c:forEach items="${plist}" var="person">
    이름 : ${person.name } <br/>
    아이디 : <a href="view?id=${person.id}">${person.id}</a><br/>
    성별 : ${person.gender } <br/>
    직업 : ${person.job } <br/> <br/>

</c:forEach>



</body>
</html>
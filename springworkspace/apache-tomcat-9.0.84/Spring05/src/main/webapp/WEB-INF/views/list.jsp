<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Guest List</title>
</head>
<body>

<h3>Guest List "${count}"</h3>
<form action="gList">
    <select name="field">
        <option value="name">이름</option>
        <option value="content">콘텐츠</option>
    </select>
    <input type="text" name="word">
    <input type="submit" value="검색">
</form>
<br/><br/>

<a href="gInsert">추가하기</a>
<a href="gList">목록</a><br/>
<c:forEach items="${gList}" var="guest">
    이름 : ${guest.name } <br/>
    아이디 : <a href="guest_view?id=${guest.num}">${guest.num}</a><br/>
    성적 : ${guest.grade } <br/>
    . : ${guest.created } <br/> <br/>
</c:forEach>

</body>
</html>

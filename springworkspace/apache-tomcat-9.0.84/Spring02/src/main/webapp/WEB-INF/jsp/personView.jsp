<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상세보기</title>
</head>
<body>
    <h3>상세보기</h3>
    

        아이디 : ${person.id }
        이름 : ${person.name } <br/> <br/>
        성별 : ${person.gender } <br/>
        직업 : ${person.job } <br/> <br/>
        <button type="button" onclick="location.href='person_updateForm.sp?id=${person.id}'">수정폼</button>
        <button type="button" onclick="location.href='person_delete.go?id=${person.id}'">삭제</button>

    
</body>
</html>

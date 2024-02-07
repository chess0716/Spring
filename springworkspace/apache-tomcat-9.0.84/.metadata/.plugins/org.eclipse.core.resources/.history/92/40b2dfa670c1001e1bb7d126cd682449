<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>	
</head>
<body>
<nav class="navbar navbar-expand-sm bg-danger navbar-dark">
  <div class="container-fluid">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="insert">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="boardInsert">BoardInsert</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="fileInsert">BoardFileInsert</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="fileList">BoardFileList</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="list">BoardList</a>
      </li>
      <li class="nav-item">
            <a class="nav-link" href="uploadFile">파일업로드</a>
          </li>
    </ul>
    
    <ul class="navbar-nav">
      <c:choose>
     	<c:when test="${empty sessionScope.sessionMember}">
          <li class="nav-item">
            <a class="nav-link" href="member/join">회원가입</a>
          </li>
    	  <li class="nav-item">
            <a class="nav-link" href="login">로그인</a>
          </li>
      </c:when>
      <c:otherwise>
          <li class="nav-item">
            <a class="nav-link" href="member/logout">로그아웃</a>
          </li>
    	  <li class="nav-item">
            <a class="nav-link" href="member/update">회원변경</a>
          </li>
          
      </c:otherwise>
       </c:choose>
    </ul>
  </div>
</nav>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-sm bg-success navbar-dark">
		<div class="container">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="/app09">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="/app09/board/insert">BoardInsert</a></li>
				<li class="nav-item"><a class="nav-link" href="/app09/board/list">BoardList</a></li>
			</ul>
			
			<sec:authentication property="principal" var="pinfo" />
			<ul class="navbar-nav">
				<sec:authorize access="isAnonymous()">
				<input type="hidden" id="prin" name="prin" value="null"  />
					<li class="nav-item"><a class="nav-link"
						href="/app09/customLogin">로그인</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
				<input type="hidden" id="prin" name="prin"  value="${pinfo.username}"  />
 					<li class="nav-item"><a class="nav-link"
						href="/app09/customLogout">로그아웃(${pinfo.username})</a></li>
				</sec:authorize>
			</ul>


		</div>
	</nav>
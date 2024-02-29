<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="include/header.jsp"%>
<div class="container mt-3">
	<div class="row">
		<c:forEach items="${farr}" var="item">
			<div class="card col-4 m-3" style="width: 400px">
				<img class="card-img-top" src="/images/${item.fileimage }"
					alt="Card image" style="width: 100%; height: 200px">
				<div class="card-body">
					<h4 class="card-title">${item.title }</h4>
					<p class="card-text">${item.writer }</p>
					<p class="card-text">${item.content }</p>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
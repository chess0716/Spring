<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../include/header.jsp"%>

<div class="container mt-3">
	<h2>BoardList(${count })</h2>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boards.content }" var="board" varStatus="st">
				<tr>
					<td>${board.num }</td>
					<%-- <td>${rowNo-st.index }</td> --%>
					<td><a href="view/${board.num }">${board.title }</a></td>
					<td>${board.user.username }</td>
					<td><fmt:formatDate value="${board.regdate }"
							pattern="yyyy-MM-dd" /></td>
					<td>${board.hitcount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="d-flex justify-content-between mt-5">
		<ul class="pagination">
			<!--   이전 -->
			<c:if test="${boards.first == false }">
				<li class="page-item"><a class="page-link"
					href="?page=${boards.number-1}">Previous</a></li>
			</c:if>
			<!-- 다음 -->
			<c:if test="${boards.last == false }">
				<li class="page-item"><a class="page-link"
					href="?page=${boards.number+1}">Next</a></li>
			</c:if>
		</ul>

		<form class="d-flex justify-content-between" action="list"
			method="get">
			<select class="form-select" name="field">
				<option value="content">내용</option>
				<option value="title">제목</option>
			</select> <input type="text" class="form-control" id="word"
				placeholder="Enter  Word" name="word">
			<button class='btn btn-secondary btn-sm'>Search</button>
		</form>
	</div>
</div>

</body>
</html>





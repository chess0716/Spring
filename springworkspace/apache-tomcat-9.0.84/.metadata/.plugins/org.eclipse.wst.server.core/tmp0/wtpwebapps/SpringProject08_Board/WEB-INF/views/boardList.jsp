<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="include/header.jsp"%>
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
			<c:forEach items="${boards }" var="board" varStatus="st">
				<tr>
					<%-- <td>${board.num }</td> --%>
					<td>${rowNo-st.index }</td>
					<td><a href="view/${board.num }">${board.title }</a></td>
					<td>${board.writer }</td>
					<td><fmt:formatDate value="${board.regdate }"
							pattern="yyyy-MM-dd" /></td>
					<td>${board.hitcount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="d-flex justify-content-between">
	<ul class="pagination">
		<!-- 이전 -->
		<c:if test="${ p.startPage > p.blockPage}">
			<li class="page-item"><a class="page-link"
				href="/list?pageNum=${p.startPage-p.blockPage }&field=${p.field}&word=${p.word}">Previous</a></li>
		</c:if>
		<!-- 페이지번호 -->
		<c:forEach begin="${p.startPage }" end="${p.endPage }" var="i">
			<c:if test="${ i == p.currentPage }">
				<!-- 현재페이지 -->
				<li class="page-item active"><a class="page-link" href="#">${i }</a></li>
			</c:if>
			<c:if test="${i != p.currentPage }">
				<!-- 현재페이지 아니라면-->
				<li class="page-item"><a class="page-link"
					href="/list?pageNum=${i }&field=${p.field}&word=${p.word}">${i }</a></li>
			</c:if>
		</c:forEach>

		<!-- 다음 -->
		<c:if test="${p.endPage < p.totPage }">
			<li class="page-item"><a class="page-link"
				href="/list?pageNum=${p.endPage+1}&field=${p.field}&word=${p.word}">Next</a></li>
		</c:if>

	</ul>
	<form  class="d-flex justify-content-between"  action="/list" method="get">
		<select class="form-select" name="field">
			<option value="writer">작성자</option>
			<option value="title">제목</option>
		</select>
		<input type="text"
				class="form-control" id="word" placeholder="Enter  Word"
				name="word" >
		<button class='btn btn-secondary btn-sm'>Search</button>		
	</form>
</div>



</div>









<%@ include file="include/footer.jsp"%>
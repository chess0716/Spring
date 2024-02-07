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
			<c:forEach items="${boards }" var="board" varStatus="st">
				<tr>
					<td>${board.num }</td>
					<%-- <td>${rowNo-st.index }</td> --%>
					<td><a href="view/${board.num }">${board.title }</a></td>
					<td>${board.writer }</td>
					<td><fmt:formatDate value="${board.regdate }"
							pattern="yyyy-MM-dd" /></td>
					<td>${board.hitcount }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</div>
</body>
</html>
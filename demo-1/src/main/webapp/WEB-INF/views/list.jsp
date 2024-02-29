<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
                <th>수정</th>
                <th>삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${boards }" var="board" varStatus="st">
                <tr>
                    <td>${board.num }</td>
                    <td><a href="view/${board.num }">${board.title }</a></td>
                    <td>${board.writer }</td>
                    <td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd" /></td>
                    <td>${board.hitcount }</td>
                     <td><a class="btn btn-secondary btn-sm" href="update/${board.num }">수정</a></td>
                    <td><button type="button" onclick="confirmDelete(${board.num})">삭제</button></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<script>
    function confirmDelete(num) {
        if (confirm('정말 삭제할까요?')) {
            $.ajax({
                type : "DELETE",
                url : "/delete/" + num,
                success : function(resp) {
                    alert(resp + "번 삭제 성공");
                    location.reload();
                },
                error : function(e) {
                    alert("삭제 실패 : " + e);
                }
            });
        }
    }
</script>
</body>
</html>
<%@ include file="include/footer.jsp"%>


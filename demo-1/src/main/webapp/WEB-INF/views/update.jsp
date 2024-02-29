<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<div class="container mt-3">
    <h3>글 수정</h3>
    <form id="updateForm" method="post" action="/update/${board.num}">
        <input type="hidden" name="num" value="${board.num}">
        <div class="mb-3 mt-3">
            <label for="title">제목:</label> 
            <input type="text" class="form-control" id="title" name="title" value="${board.title}">
        </div>
        <div class="mb-3 mt-3">
            <label for="writer">작성자:</label> 
            <input type="text" class="form-control" id="writer" name="writer" value="${board.writer}">
        </div>
        <div class="mb-3 mt-3">
            <label for="content">내용:</label>
            <textarea class="form-control" rows="5" id="content" name="content">${board.content}</textarea>
        </div>
        <div class="text-end">
            <button type="submit" class="btn btn-secondary btn-sm"onclick="return confirm('정말 수정할까요?')">수정</button>
            <button type="button" id="btnDelete" class="btn btn-danger btn-sm" onclick="confirmDelete()">삭제</button>
        </div>
    </form>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function confirmDelete() {
        if (confirm('정말 삭제할까요?')) {
          
            $.ajax({
                type : "DELETE", 
                url : "/delete/${board.num}",
                success : function(resp) {
                    alert(resp + "번 삭제 성공");
                    location.href = "/list";
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


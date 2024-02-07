<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container mt-3">
    <form action="insert" method="post">
        <div class="mb-3 mt-3">
            <label for="title">제목:</label> 
            <input type="text" class="form-control" id="title" placeholder="Enter title" name="title">
        </div>
        <div class="mb-3">
            <label for="writer">글쓴이:</label> 
            <input type="text" class="form-control" id="writer" value='<sec:authentication property="principal.username"/>' name="writer" readonly="readonly">
        </div>
        <div class="mb-3 mt-3">
            <label for="content">내용:</label>
            <textarea class="form-control" rows="5" id="content" name="content"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">글쓰기</button>
        <a href="list" class="btn btn-secondary">목록으로</a>
    </form>
</div>
</body>
</html>

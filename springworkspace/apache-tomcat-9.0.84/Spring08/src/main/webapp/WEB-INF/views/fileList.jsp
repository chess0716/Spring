<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="container">
    <h2>파일 목록</h2>
    <div class="row">
        <c:forEach items="${files}" var="file">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <img src="/resources/imgs/${file.fileimage}" class="card-img-top" alt="파일이미지">
                    <div class="card-body">
                        <h5 class="card-title">${file.title}</h5>
                        <p class="card-text">작성자: ${file.writer}</p>
                        <p class="card-text">내용: ${file.content}</p>
                        <p class="card-text">파일명: ${file.fileimage}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

</body>


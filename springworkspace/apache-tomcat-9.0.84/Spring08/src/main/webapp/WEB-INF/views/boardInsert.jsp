<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="include/header.jsp"%>

<div class="container mt-3">
    <h2>File Board List</h2>
    <table class="table">
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>내용</th>
                <th>파일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="board" items="${boardList}">
                <tr>
                    <td>${board.num}</td>
                    <td>${board.title}</td>
                    <td>${board.writer}</td>
                    <td>${board.content}</td>
                    <td>
                        <a href="fileDownload?num=${board.num}">
                            <img src="download.png" alt="Download" style="width:20px;height:20px;">
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="include/footer.jsp"%>

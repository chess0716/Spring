<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container mt-3">
	<h3>${board.user.username }의 수정하기</h3>
	<div class="mb-3 mt-3">
		<label for="num">Num:</label> <input type="text" class="form-control"
			id="num" value="${board.num }" readonly="readonly">
	</div>
	<div class="mb-3 mt-3">
		<label for="title">Title:</label> <input type="text"
			class="form-control" id="title" value="${board.title }"
			>
	</div>
	<div class="mb-3">
		<label for="writer">Writer:</label> <input type="text"
			class="form-control" id="writer" value="${board.user.username }"
			name="writer" readonly="readonly">
	</div>
	<div class="mb-3 mt-3">
		<label for="content">Content:</label>
		<textarea class="form-control" rows="5" id="content" name="content"
			>${board.content }</textarea>
	</div>
	<div class="">
		<button type="button" class="btn btn-secondary btn-sm" id="btnModify">수정</button>
		
	</div>
</div>

<script>
$("#btnModify").click(function(){
	data = {
			"num" : $("#num").val(),
			"title" : $("#title").val(),
			"content" :$("#content").val()
	}
	$.ajax({
		type : "put",
		url  : "/board/update",
		contentType : "application/json;charset=utf-8",
		data:   JSON.stringify(data),
		success:function(resp){
			alert(resp + "번 글 수정 성공")
			location.href="/board/list"
		},
		error : function(e){
			alert("수정실패 : " + e)
		}
	})  //ajax
	
})  //btnModify


</script>

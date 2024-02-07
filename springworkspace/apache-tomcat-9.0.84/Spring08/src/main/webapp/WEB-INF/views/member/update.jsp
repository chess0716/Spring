<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container mt-3">
	<form action="join" method="post">
		<div class="mb-3 mt-3">
			<label for="name">이름:</label> <input type="text"
				class="form-control" id="name" placeholder="Enter name" name="name" value="name">
		</div>
		<div class="row">
			<div class="mb-3 col">
				<label for="id">아이디:</label> <input type="text"
					class="form-control" id="id" placeholder="Enter id" name="id" value="id" readonly="readonly">
			</div>
		
		</div>
		<div class="mb-3">
			<label for="password">비밀번호:</label> <input type="password"
				class="form-control" id="password" placeholder="Enter password"
				name="password" value="password">
		</div>
		
		<div class="mb-3">
			<label for="addr">주소:</label> <input type="text"
				class="form-control" id="addr" placeholder="Enter Address"
				name="addr" value="addr">
		</div>
		<button type="button" class="btn btn-secondary" id="btnUpdate">수정</button>
	</form>
</div>
<script>
$("#btnUpdate").click(function(){
	$.ajax({
		type:"post",
		url : "member/update",
		contentType : "application/json;charset=utf-8",
		data :  JSON.stringify({
			id:$("#id").val(),
			password:$("#password").val()
			
		})
	})
	.done(function(resp){
	if(resp == "success"){
		alert("수정성공")
		location.href="/member/login"
	}
	})
	.fail(function(e){
		alert("수정 실패")
	})
})

</script>


<%@ include file="../include/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container mt-3">
	<form action="join" method="post">
		<div class="mb-3 mt-3">
			<label for="name">이름:</label> <input type="text"
				class="form-control" id="name" placeholder="Enter name" 
				name="name" value="${sMember.name }">
		</div>
		<div class="row">
			<div class="mb-3 col">
				<label for="id">아이디:</label> <input type="text"
					class="form-control" id="id" placeholder="Enter id" 
					name="id" value="${sMember.id }" readonly="readonly">
			</div>
<!-- 			<div class="col align-self-center">
				<span id="idcheck"></span>
			</div> -->
		</div>
		<div class="mb-3">
			<label for="password">비밀번호:</label> <input type="password"
				class="form-control" id="password" placeholder="Enter password"
				name="password" value="${sMember.password }">
		</div>
		<div class="mb-3">
			<label for="pass_check">비밀번호 확인 :</label> <input
				type="password" class="form-control" id="pass_check"
				placeholder="Enter password_check" name="pass_check">
		</div>
		<div class="mb-3">
			<label for="addr">주소:</label> <input type="text"
				class="form-control" id="addr" placeholder="Enter Address"
				name="addr" value="${sMember.addr }">
		</div>
		<button type="button" class="btn btn-secondary" id="btnUpdate">회원정보수정</button>
	</form>
</div>
<script>
$("#btnUpdate").click(function(){
	if($("#password").val()  !=  $("#pass_check").val()){
		alert("비밀번호를  맞지 않습니다.")
		$("#pass_check").focus();
		return false;
	}
	let 	data = {
			id : $("#id").val(),
			name : $("#name").val(),
			password: $("#password").val(),
			addr :  $("#addr").val()
	}
	$.ajax({
		type:"put",
		url : "/member/update",
		contentType : "application/json;charset=utf-8",
		data :  JSON.stringify(data)
	})  //ajax
	.done(function(resp){
		if(resp=="success"){
			alert("회원 정보 수정 성공")
			location.href="/member/login"
		}
	})
		.fail(function(e){
		alert("회원 정보 수정실패")
	}) //fail
})


</script>


<%@ include file="../include/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container mt-3">
	<form action="login" method="post">
		
		<div class="row">
			<div class="mb-3 col">
				<label for="id">아이디:</label> <input type="text"
					class="form-control" id="id" placeholder="Enter id" name="id">
			</div>
		
		</div>
		<div class="mb-3">
			<label for="password">비밀번호:</label> <input type="password"
				class="form-control" id="password" placeholder="Enter password"
				name="password">
		</div>

		
		<button type="button" class="btn btn-primary" id="btnLogin">로그인</button>
	</form>
</div>
<script>
$("#btnLogin").click(function(){
	$.ajax({
		type:"post",
		url : "member/login",
		contentType : "application/json;charset=utf-8",
		data :  JSON.stringify({
			id:$("#id").val(),
			password:$("#password").val()
			
		})
	})
	.done(function(resp){
	if(resp == "no"){
		alert("회원이 아닙니다")
		location.href="/member/join"
	}else if (resp=="success"){
		alert ("로그인 성공")
		location.href="list"
	}	
	})
	.fail(function(){
		alert("로그인 실패")
	})
})
</script>


<%@ include file="../include/footer.jsp"%>
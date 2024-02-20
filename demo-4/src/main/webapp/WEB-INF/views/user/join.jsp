<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<div class="container mt-3">
<form action="/join" method="post">
	<div class="mb-3 mt-3">
		<label for="username">User Name:</label> <input type="text"
			class="form-control" id="username" placeholder="Enter username"
			name="username">
	</div>
	<div class="mb-3 mt-3">
		<label for="password">Password:</label> <input type="password"
			class="form-control" id="password" placeholder="Enter password"
			name="password">
	</div>
	<div class="mb-3 mt-3">
		<label for="email">Email:</label> <input type="text"
			class="form-control" id="email" placeholder="Enter email"
			name="email">
	</div>
	<button type="button" class="btn btn-primary" id="btnJoin">회원가입</button>
	</form>
</div>

<script>
$("#btnJoin").click(function(){
	if($("#username").val()==""){
		alert("아이디를 입력하세요")
		$("#username").focus();
		return false;
	}
	if ($("#password").val() == "") {
		alert("비밀번호 입력하세요")
		$("#password").focus();
		return false;
	}
	if ($("#email").val() == "") {
		alert("이메일 입력하세요")
		$("#email").focus();
		return false;
	}
	let dataParam = {
			username : $("#username").val(),
			password : $("#password").val(),
			email :$("#email").val()	
	}
	$.ajax({
		type:'post',
		url : '/join',
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(dataParam)
	})
	.done(function(resp){
		if(resp=="success"){
			alert("회원가입성공")
			location.href="/login"
		}else if(resp=="fail"){
			alert("아이디 중복")
			$("#username").val("");
		}
		
	})
	.fail(function(){
		alert("회원가입실패")
	})
})

</script>
</body>
</html>









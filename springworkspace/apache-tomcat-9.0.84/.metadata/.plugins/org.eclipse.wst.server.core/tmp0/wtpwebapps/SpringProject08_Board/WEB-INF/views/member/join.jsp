<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container mt-3">
	<form action="join" method="post">
		<div class="mb-3 mt-3">
			<label for="name">이름:</label> <input type="text"
				class="form-control" id="name" placeholder="Enter name" name="name">
		</div>
		<div class="row">
			<div class="mb-3 col">
				<label for="id">아이디:</label> <input type="text"
					class="form-control" id="id" placeholder="Enter id" name="id">
			</div>
			<div class="col align-self-center">
				<span id="idcheck"></span>
			</div>
		</div>
		<div class="mb-3">
			<label for="password">비밀번호:</label> <input type="password"
				class="form-control" id="password" placeholder="Enter password"
				name="password">
		</div>
		<div class="mb-3">
			<label for="pass_check">비밀번호 확인 :</label> <input
				type="password" class="form-control" id="pass_check"
				placeholder="Enter password_check" name="pass_check">
		</div>
		<div class="mb-3">
			<label for="addr">주소:</label> <input type="text"
				class="form-control" id="addr" placeholder="Enter Address"
				name="addr">
		</div>
		<button type="button" class="btn btn-secondary" id="btnJoin">회원가입</button>
	</form>
</div>
<script>
$("#btnJoin").click(function(){
	// 유효성 검사
	if($("#name").val() == ""){
		alert("이름을 입력하세요")
		$("#name").focus();
		return false;
	}
	if($("#id").val() == ""){
		alert("아이디를 입력하세요")
		$("#id").focus();
		return false;
	}
	if($("#password").val() == ""){
		alert("비밀번호를 입력하세요")
		$("#password").focus();
		return false;
	}
	if($("#password").val()  !=  $("#pass_check").val()){
		alert("비밀번호를  맞지 않습니다.")
		$("#pass_check").focus();
		return false;
	}
	if($("#addr").val() == ""){
		alert("주소를 입력하세요")
		$("#password").focus();
		return false;
	}
	
	data = {
			id : $("#id").val(),
			name : $("#name").val(),
			password: $("#password").val(),
			addr :  $("#addr").val()
	}
	$.ajax({
		type:"post",
		url : "/member/join",
		contentType : "application/json;charset=utf-8",
		data :  JSON.stringify(data)
	})  //ajax
	.done(function(resp){
		if(resp=="success"){
			alert("회원가입을 축하합니다.")
			$("#idcheck").html("")
			location.href= "login"
			
		}else if(resp=="fail"){
			//alert("아이디 중복확인하세요");
			$("#idcheck").html("<b>아이디 중복확인 하세요</b>")
			$("#id").val("")
		}
	})  //done
	.fail(function(e){
		alert("회원가입실패")
	}) //fail
}) //btnJoin


</script>


<%@ include file="../include/footer.jsp"%>
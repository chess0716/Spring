<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="include/header.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("#submitBtn").click(function(){
        if($("#id").val() == ""){
            alert("아이디를 입력하세요");
            $("#id").focus();
            return false;
        }
        if($("#password").val() == ""){
            alert("비밀번호를 입력하세요");
            $("#password").focus();
            return false;
        }
        if($("#password").val() != $("#pass_check").val()){
            alert("비밀번호가 맞지 않습니다");
            $("#pass_check").focus();
            return false;
        }
        
        // 데이터 객체 생성
        var data = {
            id: $("#id").val(),
            name: $("#name").val(), // 이 부분에 사용자 이름 입력란의 ID를 넣으셔야 합니다.
            password: $("#password").val(),
            addr: $("#addr").val() // 이 부분에 주소 입력란의 ID를 넣으셔야 합니다.
        };
        
        // 여기서부터는 데이터를 서버로 보내는 코드를 추가하시면 됩니다.
        // 예를 들어, AJAX를 사용하여 서버로 데이터를 전송할 수 있습니다.
        // $.ajax({
        //     type: "POST",
        //     url: "your_backend_url",
        //     data: data,
        //     success: function(response){
        //         // 성공적으로 서버에 데이터를 보냈을 때 실행할 코드
        //     },
        //     error: function(){
        //         // 서버로의 요청이 실패했을 때 실행할 코드
        //     }
        // });
    });
});
</script>
</head>
<body>
    <form>
        <label for="id">아이디:</label>
        <input type="text" id="id" name="id"><br><br>
        
        <label for="name">이름:</label>
        <input type="text" id="name" name="name"><br><br>
        
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password"><br><br>
        
        <label for="pass_check">비밀번호 확인:</label>
        <input type="password" id="pass_check" name="pass_check"><br><br>
        
        <label for="addr">주소:</label>
        <input type="text" id="addr" name="addr"><br><br>
        
        <input type="button" value="제출" id="submitBtn">
    </form>
</body>
</html>


</body>
</html>
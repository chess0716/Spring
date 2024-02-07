<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>정보 수정</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
    <h2>정보 수정</h2>
    <form action="update" method="post">
        이름: <input type="text" name="name" value="${person.name}" ><br>
        아이디: <input type="text" name="id" value="${person.id}" readonly="readonly"><br>
        패스워드: <input type="password" name="password" value="${person.password}"><br>
        성별:
        <label for="man">남</label>
        <input type="radio" name="gender" id="man" value="남" checked>
        여 <input type="radio" name="gender" value="여"><br>
        직업:
        <select name="job" id="job">
            <option value="회사원">회사원</option>
            <option value="학생">학생</option>
            <option value="기타">기타</option>
        </select><br><br>
        <script>
        $("input:radio[value='${person.gender}']").prop("checked", true);
        $("#job option").each(function(){
            if($(this).val() == "${person.job}"){
                $(this).prop("selected", true);
            }
        });
        </script>
        <input type="submit" value="수정">
        <input type="reset" value="취소">
    </form>
</body>
</html>


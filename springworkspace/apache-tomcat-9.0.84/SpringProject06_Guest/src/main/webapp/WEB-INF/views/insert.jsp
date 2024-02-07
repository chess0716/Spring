<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
    $(function () {
        $("#send").click(function () {
            var postString = {
                "name": $("#name").val(),
                "content": $("#content").val(),
                "grade": $("input:radio[name='grade']:checked").val()
            };

            $.ajax({
                type: "POST",
                url: "insertForm", // 변경된 부분
                contentType: "application/json;charset=utf-8",
                data: JSON.stringify(postString),
                success: function (resp) {
                    alert(resp);
                },
                error: function (e) {
                    console.error(e);
                }
            });
        });
    });
</script>

</head>
<body>
<a href="list">전체보기</a>
<form action="insert" method="post">
    <table>
        <tr>
            <td>글쓴이</td>
            <td><input type="text" id="name" name="name"></td>
        </tr>
        <tr>
            <td>내 용</td>
            <td><input type="text" size="80" id="content" name="content"></td>
        </tr>
        <tr>
            <td>평가</td>
            <td>
                <input type="radio" name="grade" value="excellent" checked>아주잘함(excellent)
                <input type="radio" name="grade" value="good"> 잘함(good)
                <input type="radio" name="grade" value="normal"> 보통(normal)
                <input type="radio" name="grade" value="fail"> 노력(fail)
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="button" value="입력" id="send"> <!-- 변경된 부분: type="submit" 에서 type="button" 으로 변경 -->
            </td>
        </tr>
    </table>
</form>

</body>
</html>

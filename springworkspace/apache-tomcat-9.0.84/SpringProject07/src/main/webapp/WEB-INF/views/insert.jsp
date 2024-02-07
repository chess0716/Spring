<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Guest</title>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
        $(function(){
            $("#send").click(function(){
                var postString = {
                    "name" : $("#name").val(),
                    "content" : $("#content").val(),
                    "grade" : $("input:radio[name='grade']:checked").val()
                };

                $.ajax({
                    type: "post",
                    url : "insert",
                    contentType : "application/json;charset=utf-8",
                    data : JSON.stringify(postString),
                    beforeSend : showRequest,
                    success : function(resp){
                        alert(resp);
                        loadData();
                    },
                    error : function(e){
                        alert(e);
                    }
                });
            });

            // 삭제 버튼에 대한 클릭 이벤트 처리
            $("#result").on("click", ".delete-btn", function() {
                var num = $(this).data("num");
                deleteData(num);
            });

            // 찾기 버튼에 대한 클릭 이벤트 처리
            $("#btnSearch").click(function() {
                var field = $("#field").val();
                var word = $("#word").val();
                searchData(field, word);
            });
        });

        function loadData() {
            $.getJSON("list", function(resp) {
                var str = "";
                $.each(resp, function(key, val) {
                    str += val.num + " ";
                    str += "<a href='javascript:fview(" + val.num + ")'>" + val.name + "</a> ";
                    str += val.content + " ";
                    str += val.grade + " ";
                    // 삭제 버튼 추가
                    str += "<button class='delete-btn' data-num='" + val.num + "'>삭제</button>";
                    str += "<br/> ";
                });
                $('#cnt').text("개수:" + resp.count);
                $("#result").html(str);
            });
        }

        // 상세보기
        function fview(num) {
            $.getJSON("view?num=" + num, function(resp) {
                var str = "";
                str += "번호: " + resp.num + "</br>";
                str += "이름: " + resp.name + "</br>";
                str += "내용: " + resp.content + "</br>";
                str += "작성일: " + resp.created + "</br>";
                str += "평가: " + resp.grade + "</br>";
                $("#viewresult").html(str);
            });
        }

        // 찾기
        function searchData(field, word) {
            $.ajax({
                type: "post",
                url: "search",
                data: { "field": field, "word": word },
                success: function(resp) {
                    var str = "";
                    $.each(resp, function(key, val) {
                        str += val.num + " ";
                        str += "<a href='javascript:fview(" + val.num + ")'>" + val.name + "</a> ";
                        str += val.content + " ";
                        str += val.grade + " ";
                       
                        str += "<button class='delete-btn' data-num='" + val.num + "'>삭제</button>";
                        str += "<br/> ";
                    });
                    $('#cnt').text("개수:" + resp.count);
                    $("#result").html(str);
                },
                error: function(e) {
                    alert(e);
                }
            });
        }

        function showRequest(){
            if($("#name").val() === ""){
                alert("글쓴이를 입력하세요");
                $("#name").focus();
                return false;
            }
            if($("#content").val() === ""){
                alert("내용을 입력하세요");
                $("#content").focus();
                return false;
            }
            if($("input:radio[name=grade]:checked").length === 0){
                alert("평가하세요");
                return false;
            }
            return true;
        }

        // 삭제 요청을 서버로 전송하는 함수
        function deleteData(num) {
            $.ajax({
                type: "post",
                url: "delete?num=" + num,
                success: function(resp) {
                    alert(resp);
                    loadData(); // 삭제 후 데이터 다시 로드
                },
                error: function(e) {
                    alert(e);
                }
            });
        }
    </script>
</head>
<body>
    <a href="list">전체보기</a> <!-- 수정된 부분 -->
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
                    <input type="button" value="입력" id="send">
                </td>
            </tr>
        </table>
        <div align="right">
        <select name="field" id="field">
        <option value="name" >이름</option>
        <option value="content">내용</option>
        </select>
        <input type="text" name="word" id="word">
        <input type="button" value="찾기" id="btnSearch">
        </div>
    </form>
    <hr/>
    <div id=cnt></div>
    <div id="result"></div>
</body>
<div id="viewresult"></div>
</html>


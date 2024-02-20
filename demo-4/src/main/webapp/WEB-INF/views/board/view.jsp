<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container mt-3">
    <h3>${board.user.username }의 글 보기</h3>
    <div class="mb-3 mt-3">
        <label for="num">Num:</label> <input type="text" class="form-control" id="num" value="${board.num }" readonly="readonly">
    </div>
    <div class="mb-3 mt-3">
        <label for="title">Title:</label> <input type="text" class="form-control" id="title" value="${board.title }" readonly="readonly">
    </div>
    <div class="mb-3">
        <label for="writer">Writer:</label> <input type="text" class="form-control" id="writer" value="${board.user.username }" readonly="readonly" name="writer">
    </div>
    <div class="mb-3 mt-3">
        <label for="content">Content:</label>
        <textarea class="form-control" rows="5" id="content" name="content" readonly="readonly">${board.content }</textarea>
    </div>
    <c:if test="${board.user.username == principal.user.username }">
        <div class="text-end">
        	<button type="button" class="btn btn-success btn-sm" id="btnUpdate">수정</button>
           <button type="button" class="btn btn-danger btn-sm" id="btnDelete">삭제</button>
              
        </div>
    </c:if>
    <div class="mb-3 mt-3">
        <label for="msg">Comment:</label>
        <textarea class="form-control" rows="3" id="msg" name="msg"></textarea>
    </div>
    <button class="btn btn-success btn-sm" id="commentBtn">Comment Write</button> 
    <div class="mt-3">
        <h4>댓글 리스트</h4>
        <p>댓글 개수: ${board.replyCnt}</p>
        <ul id="commentList" class="list-group">
            <!-- 여기에 댓글이 동적으로 추가됩니다. -->
        </ul>
     
    </div>
    
    <div id="replyResult" class="mt-3"></div>
     
<script>
var init = function() {
    $.ajax({
        type: "get",
        url: "/reply/commentList/${board.num}"
    }).done(function(resp) {
        var str = "";
        $.each(resp, function(index, comment) {
            str += "<li class='list-group-item'>" + comment.user.username + ": " + comment.content + " <button type='button' class='btn btn-danger btn-sm' onclick='fdel(" + comment.cnum + ")'>삭제</button></li>";
        });
        $("#commentList").html(str);
    }).fail(function() {
        alert("댓글을 가져오는데 실패했습니다.");
    });
};

//댓글 삭제
function fdel(cnum) {
    $.ajax({
        type: "delete",
        url: '/reply/delete/' + cnum
    }).done(function(resp) {
        alert(resp + "번 삭제 성공")
        init()
    }).fail(function(e) {
        alert("삭제 실패 : " + e);
    })
}

//댓글 쓰기
$("#commentBtn").click(function() {
    if (${empty principal.user}) {
        alert("로그인하세요")
        location.href = "/login"
        return;
    }
    if ($("#msg").val() == "") {
        alert("댓글을 입력하세요")
        return;
    }
    let data = {
        "bnum": $("#num").val(),
        "content": $("#msg").val()
    }
    $.ajax({
        type: "POST",
        url: "/reply/commentInsert/" + $("#num").val(),
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(data),
        success: function(resp) {
            console.log("status:", status)
            alert("댓글 추가 성공");
            init()
        },
        error: function(e) {
            alert("댓글 추가 실패 : " + e);
        }
    });
});

//수정 폼
$("#btnUpdate").click(function() {
    if (!confirm('정말 수정할까요?')) {
        return false;
    }
    location.href = "/board/update/${board.num}";
});

//삭제
$("#btnDelete").click(function() {
    if (!confirm('정말 삭제할까요?')) {
        return false;
    }
    $.ajax({
        type: "DELETE",
        url: "/board/delete/${board.num}",
        success: function(resp) {
            alert(resp + "번 삭제 성공")
            location.href = "/board/list";
        },
        error: function(e) {
            alert("삭제 실패 : " + e);
        }
    });
});

init();
</script>
</body>
</html>



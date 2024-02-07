<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script   src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
<a href="gList">전체보기</a>
<form action="gUpdate" method="post">
<input type="hidden" name="num" value="${guest.num }" />
<table>
	<tr>
				<td >글쓴이</td>
				<td><input type="text" id="name" name="name" value="${guest.name }"></td>
			</tr>
			<tr>
				<td >내 용</td>
				<td><input type="text" size="80" id="content" name="content" value="${guest.content }">
				</td>
			</tr>
			<tr>
				<td >평가</td>
				<td>
				<input  type="radio" name="grade" value="excellent" >아주잘함(excellent)
				<input  type="radio" name="grade" value="good"> 잘함(good) 
				<input 	type="radio" name="grade" value="normal"> 보통(normal) 
				<input 	type="radio" name="grade" value="fail"> 노력(fail)</td>
			</tr>
			<tr>
				<td colspan="2">
				 <input type="submit" value="수정">
				 <input type="button" value="삭제" onclick="location.href='gDelete?num=${guest.num}'" />
				</td>
			</tr>
</table>
</form>
<script>
$("input:radio[value='${guest.grade}']").prop("checked", true);
</script>
</body>
</html>
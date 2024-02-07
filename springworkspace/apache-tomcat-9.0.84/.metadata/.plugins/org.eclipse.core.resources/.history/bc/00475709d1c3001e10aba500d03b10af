<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container mt-3">
	<form action="insert" method="post">
		<div class="mb-3 mt-3">
			<label for="title">Title:</label> <input type="text"
				class="form-control" id="title" placeholder="Enter title"
				name="title">
		</div>
		<div class="mb-3">
			<label for="writer">Writer:</label> <input type="text"
				class="form-control" id="writer"  
				value='<sec:authentication property="principal.username"/>'
				name="writer" readonly="readonly">
		</div>
		<div class="mb-3 mt-3">
			<label for="content">Content:</label>
			<textarea class="form-control" rows="5" id="content" name="content"></textarea>
		</div>
	
			<button type="submit" class="btn btn-primary">Submit</button>
	
	</form>
</div>
 </body>
 </html>
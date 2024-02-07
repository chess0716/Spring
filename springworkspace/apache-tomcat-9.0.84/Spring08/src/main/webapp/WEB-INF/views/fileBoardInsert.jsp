<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<div class="container mt-3">
	<form action="fileInsert" method="post" enctype="multipart/form-data">
		<div class="mb-3 mt-3">
			<label for="title">Title:</label> <input type="text"
				class="form-control" id="title" placeholder="Enter title"
				name="title">
		</div>
		<div class="mb-3">
			<label for="writer">Writer:</label> <input type="text"
				class="form-control" id="writer" name="writer">
		</div>
		<div class="mb-3 mt-3">
			<label for="content">Content:</label>
			<textarea class="form-control" rows="5" id="content" name="content"></textarea>
		</div>
		<div class="mb-3 mt-3">
				<label for="file">File:</label> <input type="file"
				class="form-control" id="file"
				name="upload"><br/>
		</div>

		<button type="submit" class="btn btn-primary btn-sm">File
			Submit</button>

	</form>
</div>


<%@ include file="include/footer.jsp"%>
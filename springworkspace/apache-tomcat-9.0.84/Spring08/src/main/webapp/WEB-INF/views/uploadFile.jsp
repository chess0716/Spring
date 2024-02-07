<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="include/header.jsp"%>
<div class="container mt-3">
	<form action="fileUpload" method="post" enctype="multipart/form-data">
		<div class="mb-3 mt-3">
			<label for="file">File1:</label> <input type="file"
				class="form-control" id="file"
				name="uploads"><br/>
				
				<label for="file1">File2:</label> <input type="file"
				class="form-control" id="file1" 
				name="uploads"><br/>
				
				<label for="file2">File3:</label> <input type="file"
				class="form-control" id="file2" 
				name="uploads"><br/>
		</div>

		<button type="submit" class="btn btn-secondary">FileSubmit</button>

	</form>
</div>


<%@ include file="include/footer.jsp"%>
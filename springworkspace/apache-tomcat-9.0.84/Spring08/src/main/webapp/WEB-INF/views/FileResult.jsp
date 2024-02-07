<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>File Upload Result</title>
</head>
<body>
    <h1>File Upload Result</h1>
  
    <ul>
        <%
            java.util.ArrayList<String> fileList = (java.util.ArrayList<String>) request.getAttribute("fileList");
            if (fileList != null) {
                for (String fileName : fileList) {
        %>
        <li><%= fileName %></li>
        <% 
                }
            } else {
        %>
        <li>No files uploaded</li>
        <% } %>
    </ul>
</body>
</html>

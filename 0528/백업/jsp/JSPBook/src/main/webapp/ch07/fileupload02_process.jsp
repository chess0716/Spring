<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MultipartRequest multi = new MultipartRequest(request, 
			"C:\\upload2", 5*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
	String name1 = multi.getParameter("name1");
	String subject1 = multi.getParameter("subject1");
	String name2 = multi.getParameter("name2");
	String subject2 = multi.getParameter("subject2");
	String name3 = multi.getParameter("name3");
	String subject3 = multi.getParameter("subject3");
	
	Enumeration files = multi.getFileNames();
	String file3 = (String) files.nextElement();
	String filename3 = multi.getFilesystemName(file3);
	String file2 = (String) files.nextElement();
	String filename2 = multi.getFilesystemName(file2);
	String file1 = (String) files.nextElement();
	String filename1 = multi.getFilesystemName(file1);
%>
<table border="1">
	<tr>
		<th width="100">이름</th>
		<th width="100">제목</th>
		<th width="100">파일</th>
	</tr>
	<tr>
		<td><%=name1 %></td>
		<td><%=subject1 %></td>
		<td><%=filename1 %></td>
	</tr>
	<tr>
		<td><%=name2 %></td>
		<td><%=subject2 %></td>
		<td><%=filename2 %></td>
	</tr>
	<tr>
		<td><%=name3 %></td>
		<td><%=subject3 %></td>
		<td><%=filename3 %></td>
	</tr>
</table>














<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="500" align="center">
	<c:forEach var="list" items="${list }">
		<tr>
			<td align="center">${list.number }</td>
			<td>${list.writer }</td>
			<td><a href="read?number=${list.number }">${list.subject }</a></td>
			<td>${list.created }</td>
		</tr>
	</c:forEach>
</table>
<a href="postform">글 쓰기</a>
</body>
</html>
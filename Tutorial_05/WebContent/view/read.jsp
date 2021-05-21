<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=1 width="500" align="center">
	<tr>
		<td>${board.number }</td>
		<td>${board.subject }</td>
		<td>${board.writer }</td>
		<td>${board.created }</td>
	</tr>
	<tr>
		<td colspan="4" align="center" height="300">${board.contents }</td>
	</tr>
</table>
<a href="delete?number=${board.number }">삭제</a>

<a href="updateform?number=${board.number }">수정</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="update" method="post">
<input type="hidden" name="number" value=${board.number } >
<table align="center">
	<tr>
	<td>작성자</td>
	<td><input type ="text" name="writer" value=${board.writer }></td>
	</tr>
	<tr>
	<td>제목</td>
	<td><input type ="text" name="subject" value=${board.subject }></td>
	</tr>
	<tr>
	<td>내용</td>
	<td><textarea rows="10" cols="50" name="contents">${board.contents }</textarea></td>
	</tr>
	<tr>
	<td colspan="4" align="right"><input type="submit" value="글 수정" ></td>
	</tr>
</table>

</form>
</body>
</html>
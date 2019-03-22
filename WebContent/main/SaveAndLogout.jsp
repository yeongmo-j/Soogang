<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.ded">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>최종 화면</title>
</head>
<body>
	<br>
	<br>
	<h2>최종 수강신청 목록</h2>
	<table border="1">
		<colgroup>
			<col>
			<col>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">신청과목코드</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="subject" items="${sessionScope.nowregister}">
				<tr>
					<td>${subject.sub_code}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input type='button' value='로그인화면으로'
		onclick='location.href="auth/login";'>
</body>
</html>
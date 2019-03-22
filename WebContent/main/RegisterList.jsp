<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.ded">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
</head>
<body>
<br>
<br>

	<h2>수강신청 목록</h2>
	<table border="1">
		<colgroup>
			<col>
			<col>
		</colgroup>
		<thead>
			<tr>
				<th scope="col">신청과목코드</th>
				<th scope="col">신청취소</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach var="subject" items="${sessionScope.nowregister}">
				<tr>
					<td>${subject.sub_code}</td>
					<td><center><input type='button' value='취소'
						onclick='location.href="cancel?code=${subject.sub_code}";'></center></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
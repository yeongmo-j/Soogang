<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.ded">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>수강신청 메인</title>
</head>
<body>
<jsp:include page="HowtoUse.jsp"/>
	<h2>강의 목록</h2>
	<form action="singleapply">
		<table border="1">
			<colgroup>
				<col>
				<col>
				<col>
				<col>
				<col>
				<col>
				<col>
				<col>
				<col>
			</colgroup>
			<thead>
				<tr>
					<th scope="col">다중신청</th>
					<th scope="col">과목코드</th>
					<th scope="col">과목이름</th>
					<th scope="col">교수명</th>
					<th scope="col">강의실</th>
					<th scope="col">선수과목</th>
					<th scope="col">시간1</th>
					<th scope="col">시간2</th>
					<th scope="col">개별신청</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="subject" items="${requestScope.subjectList}">
					<tr>
						<td><center><input type="checkbox" name="code"
							value=${subject.sub_code} ></center></td>
						<td>${subject.sub_code}</td>
						<td>${subject.sub_name}</td>
						<td>${subject.professor}</td>
						<td>${subject.classroom}</td>
						<td>${subject.pre}</td>
						<td>${subject.time1}</td>
						<td>${subject.time2}</td>
						<td><center><input type='button' value='개별신청'
							onclick='location.href="singleapply?code=${subject.sub_code}";'></center></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="submit" value="다중신청하기">
		<br>
		<br>
		<b>저장하고 종료 : <input type='button' value='저장' onclick='location.href="save";'></b>
	</form>
</body>
</html>
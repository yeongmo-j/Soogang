<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.ded">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>신청 결과</title>
</head>
<body>
	<c:if test="${requestScope.countmap.fail>0}">
		<h2>실패 목록</h2>
		<c:if test="${requestScope.countmap.alreadyRegisted>0}">
			<h3>이미 수강신청한 리스트</h3>
			<table>
				<colgroup>
					<col>
				</colgroup>
				<thead>
					<tr>
						<th scope="col">과목코드</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="subject"
						items="${requestScope.resultMap.alreadyRegisted}">
						<tr>
							<td>${subject}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${requestScope.countmap.creditFailList>0}">
			<h3>신청 학점 초과 리스트</h3>
			<table>
				<colgroup>
					<col>
				</colgroup>
				<thead>
					<tr>
						<th scope="col">과목코드</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="subject"
						items="${requestScope.resultMap.creditFailList}">
						<tr>
							<td>${subject}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${requestScope.countmap.timeFailList>0}">
			<h3>기존에 신청한 수업과 시간 겹침 리스트</h3>
			<table>
				<colgroup>
					<col>
				</colgroup>
				<thead>
					<tr>
						<th scope="col">과목코드</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="subject"
						items="${requestScope.resultMap.timeFailList}">
						<tr>
							<td>${subject}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${requestScope.countmap.preFailList>0}">
			<h3>선수과목 수강 안함 리스트</h3>
			<table>
				<colgroup>
					<col>
				</colgroup>
				<thead>
					<tr>
						<th scope="col">과목코드</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="subject"
						items="${requestScope.resultMap.preFailList}">
						<tr>
							<td>${subject}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</c:if>
	<br>
	<br>
	<br>
	<c:if test="${requestScope.countmap.success>0}">
		<h2>성공 목록</h2>
		<table>
			<colgroup>
				<col>
			</colgroup>
			<thead>
				<tr>
					<th scope="col">과목코드</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="subject"
					items="${requestScope.resultMap.successList}">
					<tr>
						<td>${subject}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
	<br>
	<br> 저장하러 가기
	<input type='button' value='저장' onclick='location.href="save";'>
	<br>
	<br>
	<br> 다시 메인으로 가
	<input type='button' value='메인으로' onclick='location.href="main";'>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.ded">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>신청 결과</title>
<style>
.succ {
	border: 25px solid green;
	padding: 25px;
	margin: 25px;
}

.fail {
	border: 25px solid red;
	padding: 25px;
	margin: 25px;
}

h2 {
	text-align: center;
}
</style>
</head>
<body>
	<c:if test="${requestScope.countmap.fail>0}">
		<h2>실패 목록</h2>
		<div class="fail">
			<c:if test="${requestScope.countmap.alreadyRegisted>0}">
				<h3>이미 수강신청한 리스트</h3>
				<ol>
				<c:forEach var="subject"
					items="${requestScope.resultMap.alreadyRegisted}">
					<li>${subject}</li>
				</c:forEach>
				</ol>
			</c:if>
			<c:if test="${requestScope.countmap.creditFailList>0}">
				<h3>신청 학점 초과 리스트</h3>
				<ol>
				<c:forEach var="subject"
					items="${requestScope.resultMap.creditFailList}">
					<li>${subject}</li>
				</c:forEach>
				</ol>
			</c:if>
			<c:if test="${requestScope.countmap.timeFailList>0}">
				<h3>기존에 신청한 수업과 시간 겹침 리스트</h3>
				<ol>
				<c:forEach var="subject"
					items="${requestScope.resultMap.timeFailList}">
					<li>${subject}</li>
				</c:forEach>
				</ol>
			</c:if>
			<c:if test="${requestScope.countmap.preFailList>0}">
				<h3>선수과목 수강 안함 리스트</h3>
				<ol>
				<c:forEach var="subject"
					items="${requestScope.resultMap.preFailList}">
					<li>${subject}</li>
				</c:forEach>
				</ol>
			</c:if>
		</div>
	</c:if>
	<br>
	<c:if test="${requestScope.countmap.success>0}">
		<h2>성공 목록</h2>
		<div class="succ">
			<ol>
			<c:forEach var="subject"
				items="${requestScope.resultMap.successList}">
				<li>${subject}</li>
			</c:forEach>
			</ol>
		</div>
	</c:if>
	<div class="succ">
		저장하러 가기 <input type='button' value='저장'
			onclick='location.href="save";'> <br> <br> <br>
		다시 메인으로 가기 <input type='button' value='메인으로'
			onclick='location.href="main";'>
	</div>

</body>
</html>

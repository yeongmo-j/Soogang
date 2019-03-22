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
	<h2>사용방법</h2>
	1. 강의 목록에 있는 '다중신청' 체크박스에 과목들을 체크한 후, 아래에 있는 다중신청하기 버튼을 누르면, 다중수강신청이
	됩니다.
	<br>2. 강의 목록 오른쪽에 있는 '개별신청' 버튼을 클릭하면, 해당 과목 1개가 수강신청 됩니다.
	<br>3. 다중신청 혹은 개별신청 한 후 수강신청 성공한 과목과, 실패한 과목과 그 사유가 화면에 출력됩니다. <br>
	4. 수강신청 목록에 있는 '취소' 버튼을 누르면, 해당 과목의 수강신청이 취소됩니다. <br>
	5. 수강신청을 전부 완료한 후, 메인화면 혹은 결과화면의 '저장' 버튼을 클릭해야 신청된 과목들이 저장됩니다.<br><b>버튼을 누르지 않고 종료할 경우 신청 정보는 마지막 저장
	시점으로 남아있습니다.</b><br>
	6. 강의 목록에서 표시되는 시간표는 다음의 시간표에 해당하는 수와 일치합니다.
	<table border="1">
		<colgroup>
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
				<th scope="col">교시</th>
				<th scope="col">월요일</th>
				<th scope="col">화요일</th>
				<th scope="col">수요일</th>
				<th scope="col">목요일</th>
				<th scope="col">금요일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="timeRow" begin="1" end="6">
				<tr>
					<td><center>${timeRow}</center></td>
					<c:forEach var="timeCol" begin="1" end="5">
						<td><center>${timeRow*10 + timeCol}</center></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
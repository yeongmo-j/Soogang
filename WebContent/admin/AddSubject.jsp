<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.ded">
<html>
<head>
<meta http-equiv="Content-Type" content = "text/html;charset=UTF-8">
<title>수업 등록</title>
</head>
<body>
<h1>수업 등록</h1>
<form action='addSbj' method='post'>
강의코드: <input type='text' name='code'><br>
강의명: <input type='text' name='name'><br>
교수명: <input type='text' name='profname'><br>
강의실: <input type='text' name='classroom'><br>
선수과목 : <input type='text' name='pre'><br>
시간1: <input type='text' name='time1'><br>
시간2: <input type='text' name='time2'><br>
<input type='submit' value='추가'>
<input type='reset' value='취소'>
</form>
</body>
</html>
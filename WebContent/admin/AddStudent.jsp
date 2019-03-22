<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.ded">
<html>
<head>
<meta http-equiv="Content-Type" content = "text/html;charset=UTF-8">
<title>학생 등록</title>
</head>
<body>
<h1>학생 등록</h1>
<form action='add' method='post'>
학번: <input type='text' name='code'><br>
이름: <input type='text' name='name'><br>
비밀번호: <input type='password' name='password'><br>
<input type='submit' value='추가'>
<input type='reset' value='취소'>
</form>
</body>
</html>
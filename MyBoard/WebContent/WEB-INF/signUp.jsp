<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<style>
.container {
	width: 500px;
	margin: 40px auto;
	line-height: 16px;
}

h5 {
	text-align: center;
}

h5 span {
	color: teal;
}

.n {
	font-size: 13px;
}

#signup {
	background-color: rgb(255, 80, 90);
	color: white;
	border: 0;
	border-radius: 5px;
	padding: 10px 224px;
}

.bottom input {
	background-color: white;
	border: 0;
	color: teal;
	font-size: 16px;
}

i {
	color: lightgray;
}

input {
	border: 1px solid lightgray;
	border-radius: 3px;
}
</style>
<title>회원 가입</title>
</head>
<body>
	<div class="container">
		<h5>
			<span>회원 가입</span> 페이지 입니다.
		</h5>
		<hr />
		<br />
		<form action="join.do" method="post">
			<input type="text" placeholder="아이디" name="id" required style="height: 30px; width: 495px" /><br />
			<br /> <input type="password" placeholder="비밀번호" name="password" required style="height: 30px; width: 495px" /><br />
			<br /> <input type="email" placeholder="이메일 주소" name="email" required style="height: 30px; width: 495px" /><br />
			<br /> <input type="text" placeholder="이름" name="name" required style="height: 30px; width: 495px" /><br />
			<p>
				<input type="submit" value="가입하기" id="signup" /><br />
				<br />
			</p>
		</form>
	</div>
	<hr />
</body>
<c:if test="${joinResult == 0 }">
	<script>
		alert("아이디가 중복됩니다.");
	</script>
</c:if>

</html>
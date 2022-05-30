<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
.container {
	width: 385px;
	line-height: 50px;
	margin: 40px auto;
}

h5 {
	text-align: center;
}

h5 span {
	color: teal;
}

.login {
	background-color: rgb(255, 80, 90);
	color: white;
	border-radius: 5px;
	border: 0;
	padding: 10px 172px;
}

#signup {
	background-color: white;
	color: teal;
	border: 0;
	font-size: 17px;
}

p {
	text-align: center;
}

i {
	color: lightgray;
}

input {
	border: 1px solid lightgray;
	border-radius: 3px;
}
</style>
<title>로그인</title>
</head>
<body>
	<c:if test="${sessionID != null }">
		<script>
			alert("이미 로그인 중입니다.");
			location.href = "home.do";
		</script>
	</c:if>

	<c:if test="${ loginResult == -1 || loginResult == 0 }">
		<script>
			alert("아이디 혹은 비밀번호가 틀렸습니다.");
		</script>
	</c:if>
	<div class="container">
		<h5>
			<span>로그인</span> 페이지입니다.
		</h5>
		<hr />
		<form action="login.do" method="post">
			<input type="text" placeholder="아이디" name="id" required style="height: 30px; width: 380px" /><br /> <input type="password" placeholder="비밀번호" name="password" required style="height: 30px; width: 380px" /><br /> <input type="submit" value="로그인" class="login" />
			<button onclick="location.href='home.do';" class="login">HOME</button>
		</form>
		<hr />
		<p>
			<a href="join.do"><input type="button" value="회원가입" id="signup" /></a>
		</p>
	</div>
</body>
</html>
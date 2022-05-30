<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
<p>메인 홈 입니다.</p>
<script>
	function btn(){
		alert("로그아웃 되었습니다.");
	}
</script>
<c:if test="${sessionID == null }">
	<a href="login.do"><button>로그인</button></a><br />
</c:if>
<c:if test="${sessionID != null }">
	${sessionID } 님 로그인 중<br />
	<a href="logout.do"><button onclick="javascript:btn()">로그아웃</button></a>
	<a href="bbs.do"><button>게시판</button></a><br />
</c:if>
</body>
</html>
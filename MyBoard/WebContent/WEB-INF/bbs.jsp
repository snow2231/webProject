<%@page import="java.sql.Timestamp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.board.DTO.BbsDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.board.DAO.BbsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
.controller {
	padding: 25px 0;
	margin: auto;
	width: 840px;
	text-align: center;
}

table {
	width: 840px;
	padding: 10px 0;
	border-collapse: collapse;
}

th {
	background-color: rgb(100, 100, 100);
	color: white;
}

button {
	margin: 4px 0;
	padding: 10px 0;
	width: 840px;
	background-color: rgb(255, 80, 80);
	color: white;
	border: none;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration-line: underline;
}
</style>
<title>게시판</title>
</head>
<body>
	<%
	    // BbsDAO 클래스에 생성한 selectList 메소드를 호출
	// 게시글 데이터가 저장되어 있는 리스트를 가져옴
	BbsDAO bd = BbsDAO.getInstance();
	List<BbsDTO> list = bd.selectList();
	%>

	<div class="controller">
		<table>
			<tr>
				<th width="40px">번호</th>
				<th width="150px">제목</th>
				<th>내용</th>
				<th width="100px">작성자</th>
				<th width="150px">날짜</th>
				<th width="40px">조회</th>
			</tr>
			<%
			    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss");
			int count = 0;
			for (BbsDTO b : list) {
			    String stDate = "";
			    Timestamp tDate = b.getBbsDate();
			    if (tDate != null) {
				stDate = sdf.format(tDate);
			    }
			%>
			<tr>
				<td><%=b.getBbsId()%></td>
				<td>
					<b><a href="bbsview.do?bbsId=<%=b.getBbsId()%> "><%=b.getBbsTitle()%></a></b>
				</td>
				<td><%=b.getBbsContent()%></td>
				<td><%=b.getId()%></td>
				<td><%=stDate%></td>
				<td><%=b.getBbsHit()%></td>
			</tr>
			<%
			    count++;
			}
			if (count == 0) {
			%>
			<tr>
				<td colspan="7">작성한 게시글이 없습니다.</td>
			</tr>
			<%
			    }
			System.out.println("현재 게시글 " + count + "개");
			%>
		</table>
		<p>
			<a href="write.do"><button>글쓰기</button></a><br /> <a href="home.do"><button>HOME</button></a>
		</p>
	</div>
</body>
</html>
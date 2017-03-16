<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 목록</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h1>회원목록</h1>
<p><a href='add.do'>신규 회원</a></p>
<c:forEach var="member" items="${members}">
${member.no},
<a href='update.do?no=${member.no}'>${member.no }</a>,
${member.email},
${member.createdDate}
<a href='delete.do?no=${member.no }'>[삭제]</a><br>
</c:forEach>

<%-- 
<%
for(Member member : members) {
%>
<%=member.getNo()%>,
<a href='update?no=<%=member.getNo()%>'><%=member.getName()%></a>,
<%=member.getEmail()%>,
<%=member.getCreatedDate()%>
<a href='delete?no=<%=member.getNo()%>'>[삭제]</a><br>
<%} %> --%>
<jsp:include page="/Tail.jsp"/>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jspf"%>

	<h1>Szczegoly zadania</h1>
	<table>
		<tr>
			<td>Tytul:</td>
			<td>${exercise.title}</td>
		</tr>
		<tr>
			<td>Opis:</td>
			<td>${exercise.description}</td>
		</tr>
	</table>
	<br>
	<table border="1">
		<tr>
			<td>Autor</td>
			<td>Data dodania</td>
			<td>Akcje</td>
		</tr>

		<c:forEach items="${sol}" var="vsol">
			<tr>
				<td>${vsol.getUser().username}</td>
				<td>${vsol.created}</td>
				<td><a href="SolutionDetails?id=${vsol.id}">Szczegoly</a></td>
			</tr>

		</c:forEach>
	</table>

	<%@include file="/WEB-INF/views/footer.jspf"%>
</body>
</html>
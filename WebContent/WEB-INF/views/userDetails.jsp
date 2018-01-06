<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Details</title>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jspf"%>

	<table>
		<tr>
			<td>Nazwa:</td>
			<td>${user.username}</td>
		</tr>
		<tr>
			<td>Email:</td>
			<td>${user.email}</td>
		</tr>
	</table>
	
	<a href="SolutionDetails?editId=0">Dodaj rozwiązanie</a>
	
	<p> Dodane rozwiazania</p> 
	<table border="1">
			<tr>
				<td>Tytul:</td>
				<td>Data:</td>
				<td>Akcja:</td>
			</tr>

			<c:forEach items="${solutions}" var="vsol">
				<tr>
					<td>${vsol.getExercise().title}</td>
					<td>${vsol.created}</td>
					<td>
					<a href="SolutionDetails?id=${vsol.id}">Szczegóły</a> 
					<a href="SolutionDetails?editId=${vsol.id}">Edycja</a> 
					<a href="SolutionDetails?delId=${vsol.id}">Usuń</a>
					</td>
				</tr>
			</c:forEach>
	</table>

	<%@include file="/WEB-INF/views/footer.jspf"%>
</body>
</html>
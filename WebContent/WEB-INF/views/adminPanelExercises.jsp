<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Panel Exercises</title>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jspf"%>
	
	<h1>Zarzadzanie zadaniami</h1>

	<a href="AdminPanelExercises?editId=0">Dodaj zadanie</a>
	
	<table border="1">
			<tr>
				<td>Tytul</td>
				<td>Opis</td>
				<td>Akcje</td>
			</tr>
			
			<c:forEach items="${ex}" var="vex">
				<tr>
					<td>${vex.title}</td>
					<td>${vex.description}</td>
					<td>
					<a href="AdminPanelExercises?showId=${vex.id}">Podgląd</a>
					<a href="AdminPanelExercises?editId=${vex.id}">Edycja</a> 
					<a href="AdminPanelExercises?delId=${vex.id}">Usuń</a> 
					</td>
				</tr>
			</c:forEach>
	</table>

	<%@include file="/WEB-INF/views/footer.jspf"%>
</body>
</html>
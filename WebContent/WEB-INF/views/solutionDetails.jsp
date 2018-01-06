<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Solution Details</title>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jspf"%>

	<h1>Szczegoly zadania</h1>
	<table>
		<tr>
			<td>Tytul:</td>
			<td>${details.getExercise().title}</td>
		</tr>
		<tr>
			<td>Autor rozwiazania:</td>
			<td>${details.getUser().username}</td>
		</tr>
		<tr>
			<td>Data dodania:</td>
			<td>${details.created}</td>
		</tr>
	</table>
	<p>Rozwiązanie zadania:</p>
	<p>${details.getDescription()}</p>

	<%@include file="/WEB-INF/views/footer.jspf"%>
</body>
</html>
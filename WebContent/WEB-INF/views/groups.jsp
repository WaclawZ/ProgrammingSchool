<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Groups</title>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jspf"%>

	<h1>Lista grup</h1>

	<table border="1">
			<tr>
				<td>Nazwa grupy</td>
				<td>Akcje</td>
			</tr>

			<c:forEach items="${groups}" var="vgroups">
				<tr>
					<td>${vgroups.name}</td>
					<td><a href="GroupUsers?id=${vgroups.id}">Uzytkownicy</a></td>
				</tr>
			</c:forEach>
	</table>

	<%@include file="/WEB-INF/views/footer.jspf"%>
</body>
</html>
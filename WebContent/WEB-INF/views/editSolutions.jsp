<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Solutions</title>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jspf"%>

	<form action="SolutionDetails" method="POST">
		<table>
			<tr>
				<td>Zadanie</td>
				<td><select name="exId">
						<c:forEach items="${ex}" var="vex">
							<option value="${vex.id}"
								<c:if test="${vex.id == solution.exercise_id}">
					selected
				</c:if>>${ex.title}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Uzytkownik</td>
				<td><select name="userId">
						<c:forEach items="${users}" var="user">
							<option value="${user.id}"
								<c:if test="${user.id == solution.user_id}">
					selected
				</c:if>>${user.username}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Rozwiazanie</td>
				<td><input type="text" name="sol"><br></td>
			</tr>
		</table>
		<input type="submit" value="Zapisz"> 
		<input type="hidden" name="id" value="${solution.id}">
	</form>

	<%@include file="/WEB-INF/views/footer.jspf"%>
</body>
</html>
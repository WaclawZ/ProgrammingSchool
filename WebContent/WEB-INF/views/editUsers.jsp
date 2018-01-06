<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Users</title>
</head>
<body>
	<%@include file="/WEB-INF/views/header.jspf"%>

	<form action="AdminPanelUsers" method="post">
		<table>
			<tr>
				<td>Nazwa</td>
				<td><input type="text" name="username" value="${user.username}"></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" value="${user.email}"></td>
			</tr>
			<tr>
				<td>Haslo</td>
				<td><input type="text" name="password"></td>
			</tr>
			<tr>
				<td>Grupa</td>
				<td><select name="groupId">
						<c:forEach items="${groups}" var="group">
							<option value="${group.id}"
								<c:if test="${group.id == user.user_group_id}">
					selected
				</c:if>>${group.name}</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="Zapisz"><br>
		<input type="hidden" name="id" value="${user.id}">
	</form>

	<%@include file="/WEB-INF/views/footer.jspf"%>
</body>
</html>
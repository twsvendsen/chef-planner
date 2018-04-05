<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Recipe</title>
</head>
<body>
	<form:form method="GET" modelAttribute="recipesList">
		<table>
			<tr>
				<td><hr/></td>
			</tr>
			<c:forEach var="recipe" items="${recipesList}" varStatus="status"> <!-- ${status.index} -->
				<tr>
					<th>${recipe.getName()}</th>
				</tr>
				<tr>
					<td>${recipe.getCookingHours()}</td>
					<td>${recipe.getCookingMinutes()}</td>
				</tr>
				<tr>
					<td>
						<a href="/${recipe.getId()}/recipeIngredients"/>
					</td>
				</tr>
				<tr>
					<td><hr/></td>
				</tr>
			</c:forEach>
		</table>
	</form:form>

</body>
</html>
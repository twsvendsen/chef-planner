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
	<form:form method="POST" modelAttribute="recipesList" >
		<table>
			<c:forEach var="recipe" items="${recipesList}" varStatus="status"> <!-- ${status.index} -->
				<tr>
					<td><hr/></td>
				</tr>
				<form:form method="POST" modelAttribute="recipe" >
					
					<tr>
						<th>${recipe.getName()}</th>
					</tr>
					<tr>
						<td>${recipe.getCookingHours()} h</td>
						<td>${recipe.getCookingMinutes()} m</td>
					</tr>
					<tr>
						<td><input type="submit" name="recipeChoice" value="${recipe.getId()}"/></td>
						<td style="left-margin:10px"><input type="submit" name="recipeDelete" value="${recipe.getId()}"/></td>
						
					</tr>
				</form:form>
			</c:forEach>
			<tr>
					<td><hr/></td>
			</tr>
			<tr/>
			<tr>
				<td><input type="submit" name="newRecipe" value="New Recipe"/></td>
			</tr>
		</table>
	</form:form>

</body>
</html>
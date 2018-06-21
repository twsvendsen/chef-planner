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
	<form:form method="POST" modelAttribute="ingredientsList">
		<table>
			<tr>
				<td><hr/></td>
			</tr>
			<c:forEach var="ingredient" items="${ingredientsList.getIngredients()}" >
				<tr>
					<th>${ingredient.getName()}</th>
				</tr>
				<tr>
					<td>${ingredient.getMeasurement()}</td>
					<td>${ingredient.getMeasurementUnit()}</td>
				</tr>
				<tr>
					<td>${ingredient.description}</td>
				</tr>
				<tr>
					<td><input type="submit" name="editIngredient" value="Edit"/></td>
					<td style="left-margin:10px"><input type="submit" name="deleteIngredient" value="Delete"/></td>
					<td><input type="hidden" name="ingredientId" value="${ingredient.getId() }" /></td>
				</tr>
				<tr>
					<td><hr/></td>
				</tr>
			</c:forEach>
		</table>
	</form:form>
	<form:form method="POST" modelAttribute="recipe" >
		<table>
				<tr>
					<td>
						<input type="submit" name="addIngredient" value="Add Ingredient"/>
					</td>
				</tr>
		</table>
	</form:form>
	<form:form method="GET" action="/ChefPlanner/recipesList.html">
		<table>
				<tr>
					<td>
						<input type="submit" value="Return to Recipes List"/>
					</td>
				</tr>
		</table>
	</form:form>
</body>
</html>
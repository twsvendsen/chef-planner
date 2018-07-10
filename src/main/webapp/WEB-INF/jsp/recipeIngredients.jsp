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
	<form:form method="POST" modelAttribute="recipe">
		<table>
			<tr>
				<td><hr/></td>
			</tr>
			<c:forEach var="ingredient" items="${recipe.getIngredientsMap()}" varStatus="iStatus">
				<tr>
					<th>${ingredient.value.getName()}</th>
				</tr>
				<tr>
					<td>${ingredient.value.getMeasurement()}</td>
					<td>${ingredient.value.getMeasurementUnit()}</td>
				</tr>
				<tr>
					<td>${ingredient.value.description}</td>
				</tr>
				<tr>
					<td><button name="editIngredient" value="${ingredient.key }">Edit</button></td>
					<td style="left-margin:10px"><button name="deleteIngredient" value="${ingredient.key }">Delete</button></td>
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
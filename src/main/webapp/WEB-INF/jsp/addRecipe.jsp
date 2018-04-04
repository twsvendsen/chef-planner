<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
				<td>
					<form:errors path="*" cssClass="errorblock" element="div" />
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="name">
						Enter recipe name:
					</form:label>
					<form:input path="name" />
				</td>
				<td>
					<form:label path="cookingHours">
						Enter hours:
					</form:label>
					<form:input path="cookingHours" />
				</td>
				<td>
					<form:label path="cookingMinutes">
						Enter minutes:
					</form:label>	
					<form:input path="cookingMinutes" />
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" class="btn" value="Submit"/>
				</td>
			</tr>
		</table>
	</form:form>

</body>
</html>
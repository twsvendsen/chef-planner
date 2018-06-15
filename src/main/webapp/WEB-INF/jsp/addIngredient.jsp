<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add Ingredient</title>
	<style>
		.error {
			color: #ff0000;
		}
		
		.errorblock {
			color: #000;
			background-color: #ffEEEE;
			border: 3px solid #ff0000;
			padding: 8px;
			margin: 16px;
		}
	</style>
</head>
<body>

	<form:form method="POST" modelAttribute="ingredient">
		<table>
			<tr>
				<form:errors path="*" cssClass="errorblock" element="div" />
			</tr>
			<tr>
				<td>
					<form:label path="name">
						Enter ingredient name:
					</form:label>
					<form:input path="name" value="${ingredient.getName() }"/>
				</td>
				<td>
					<form:label path="measurementUnit">
						Select measurement unit (if applicable):
					</form:label>
					<form:select path="measurementUnit">
						<form:option value="${ingredient.getMeasurementUnit() }" label="Please Select"/>
						<form:options items="${measurementUnitOptions}" />
					</form:select>
				</td>
				<td>
					<form:label path="measurement">
						Enter measurement:
					</form:label>
					<form:input path="measurement" value="${ingredient.getMeasurement() }" cssErrorClass="error" />
				</td>
				<td>
					<form:errors path="measurement" cssClass="error" />
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="description">
						Enter description (if applicable):
					</form:label>
					<form:input path="description" value="${ingredient.getDescription() }" cssErrorClass="error" />
				</td>
				<td>
					<form:errors path="description" cssClass="error" />
				</td>
				<!-- Below is WIP -->
				
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
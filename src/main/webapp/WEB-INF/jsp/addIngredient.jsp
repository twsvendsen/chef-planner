<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Ingredient</title>
</head>
<body>

	<form:form method="POST" modelAttribute="ingredient">
		<table>
			<tr>
				<td>
					<form:errors path="*" cssClass="errorblock" element="div" />
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="name">
						Enter ingredient name:
					</form:label>
					<form:input path="name" />
				</td>
				<td>
					<form:label path="measurementUnit">
						Select measurement unit (if applicable):
					</form:label>
					<form:select path="measurementUnit">
						<form:option value="-1" label="Please Select"/>
						<form:options items="${measurementUnitOptions}" />
					</form:select>
				</td>
				<td>
					<form:label path="measurement">
						Enter measurement:
					</form:label>
					<form:input path="measurement" />
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="description">
						Enter description (if applicable):
					</form:label>
					<form:input path="description" />
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
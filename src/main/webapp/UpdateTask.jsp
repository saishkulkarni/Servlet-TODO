<%@page import="java.time.Period"%>
<%@page import="dto.Task"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Task</title>
<style type="text/css">
body {
	background-image:
		url("https://img.freepik.com/free-vector/hand-painted-watercolor-pastel-sky-background_23-2148902771.jpg");
	background-size: cover;
}
</style>
</head>
<body>
	<%
	Task task = (Task) request.getAttribute("task");
	%>
	<h2>Enter Task Details</h2>
	<form action="updatetask" method="post">
		<input type="text" name="id" value="<%=task.getId() %>" hidden="hidden">
		<fieldset>
			<table>
				<tr>
					<th>Task Name:</th>
					<th><input type="text" name="name" value="<%=task.getName() %>" required/th>
				</tr>
				<tr>
					<th>Task Description:</th>
					<th><input type="text" name="description" value="<%=task.getDescription() %>" required></th>
				</tr>
				<tr>
					<th>Days Required:</th>
					<th><input type="number" name="days" value="<%=Period.between(task.getTaskDate(), task.getCompletetionDate()).getDays() %>" required></th>
				</tr>
				<tr>
					<th><button>Update</button></th>
					<th><a href="backtohome"><button type="button">Cancel</button></a></th>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
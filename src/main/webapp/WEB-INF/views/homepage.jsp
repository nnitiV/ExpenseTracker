<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="/" method="POST" modelAttribute="expense">
		<form:input path="expenseDescription" />
		<form:input path="amount" />
		<input type="submit" value="Save" />
	</form:form>
	<form action="/filter_tasks" method="POST">
		<input type="number" min="1" max="12" value="1" name="numberOfMonth"/>
		<input type="submit" value="filter">
	</form>
	<c:forEach items="${expenses}" var="expense">
		<form:form action="/update_task" method="POST" modelAttribute="expense">
			<form:hidden path="id" value="${expense.id}"/>
			<form:input path="expenseDescription" value="${expense.expenseDescription}" />
			<form:input path="amount" value="${expense.amount}"/>
			<p>Time of event: ${expense.dateOfEvent}</p>
			<input type="submit" value="Update"/>
			<a href="/delete_task?task_id=${expense.id}">Delete</a>
		</form:form>
	</c:forEach>
	<p>Total expenses: ${totalExpenses}</p>
</body>
</html>
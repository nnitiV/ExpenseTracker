<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" type="image/png" href="img/budget.png" />
<link rel="stylesheet" href="css/style.css" />
<title>Expense tracker</title>
</head>
<body>
	<header>
		<h1>Expense Tracker</h1>
	</header>
	<div id="search">
		<h1>Add new transaction:</h1>
		<form:form action="/" method="POST" modelAttribute="expense"
			id="saveTaskForm">
			<form:label path="expenseDescription">Name</form:label>
			<form:input path="expenseDescription"
				placeholder="Enter name of your expense..." />
			<form:label path="amount">Amount</form:label>
			<form:input path="amount" />
			<input type="submit" value="Save" class="button" />
		</form:form>
		<!-- <div id="filters">
			<form action="/filter_tasks" method="POST">
				<input type="number" min="1" max="12" value="1" name="numberOfMonth" />
				<input type="submit" value="filter">
			</form>
			<form action="/get_total_by_month" " method="POST">
				<input type="number" min="1" max="12" value="1" name="month" /> <input
					type="submit" value="Find total">
			</form>
		</div>-->
	</div>
	<h1 id="history">History</h1>
	<div id="expenses">
		<p id="total-expenses">${totalExpenses}</p>
		<c:forEach items="${expenses}" var="expense">
			<form:form action="/update_task" method="POST"
				modelAttribute="expense">
				<form:hidden path="id" value="${expense.id}" />
				<div class="labels">
					<form:label path="expenseDescription">Expense decription</form:label>
					<form:label path="amount">Amount</form:label>
				</div>
				<div class="inputs">
					<form:input path="expenseDescription"
						value="${expense.expenseDescription}" />
					<form:input path="amount" value="${expense.amount}" />
				</div>
				<p>Time of event: ${expense.dateOfEvent}</p>
				<div class="action-buttons">
					<input type="submit" value="Update" class="button" /> <a
						href="/delete_task?task_id=${expense.id}" class="button">Delete</a>
				</div>
			</form:form>
		</c:forEach>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="cf"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insurance Reports</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<h1 class="pb-3 pt-3">Report Application</h1>

		<form:form action="search" modelAttribute="search" >
			<table>
				<tr>
					<td>Plan Name:</td>
					<td><form:select path="planName">
							<form:option value="">--Select--</form:option>
							<form:options items="${Names}" />
						</form:select></td>

					<td>Plan Status:</td>
					<td><form:select path="planStatus">
							<form:option value="">--Select--</form:option>
							<form:options items="${Status}" />
						</form:select></td>
					<td>Gender:</td>
					<td><form:select path="gender">
							<form:option value="">--Select--</form:option>
							<form:option value="male">Male</form:option>
							<form:option value="female">Fe-Male</form:option>
						</form:select></td>
				</tr>

				<tr>
					<td>Start Date:</td>
					<td><form:input path="startDate" type="date"
							data-date-format="mm/dd/yyyy" /></td>
					<td>End Date:</td>
					<td><form:input path="endDate" type="date"
							data-date-format="mm/dd/yyyy" /></td>
				</tr>
				<tr>
					<td><a href="/" class="btn btn-secondary">Reset</td>
					<td><input type="submit" value="search"
						class="btn btn-primary"></td>
					<td><a href="/excel" class="btn btn-success">Excel</a>
					<td><a href="/pdf" class="btn btn-danger">Pdf</a>
				</tr>
			</table>
		</form:form>

		<hr />
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>S.No</th>
					<th>Citizen Name</th>
					<th>Gender</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>BenefitAmt</th>
				</tr>
			</thead>
			<tbody>
				<cf:forEach items="${plans}" var="plan">
					<tr>
						<td>${plan.citizenId }</td>
						<td>${plan.citizenName }</td>
						<td>${plan.gender}</td>
						<td>${plan.planName }</td>
						<td>${plan.planStatus }</td>
						<td>${plan.planStartDate }</td>
						<td>${plan.planEndDate }</td>
						<td>${plan.benefitAmt}</td>
					</tr>
				</cf:forEach>
				<cf:if test="${empty plans}">
					<td colspan="9" style="text-align: center;">
					No Records Found....</td>
				</cf:if>

			</tbody>
		</table>

		<hr />
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous" />

</body>
</html>
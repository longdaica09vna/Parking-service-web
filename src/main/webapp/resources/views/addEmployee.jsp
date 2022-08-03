<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="resources/css/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<link href="resources/css/css/myCSS.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">

</head>
<body>

	<%@include file="_header.jsp"%>
	<div class="row">
		<div class="col-lg-2 border sidebar">
			<%@include file="_sidebar.jsp"%>
		</div>

		<div class="col-lg-9">
			<c:if test="${errorAdd}">
				<div class="text-danger">Add fail</div>
			</c:if>
			<div class="card">
				<div class="card-header">
					<h1 class="card-title"><c:if test="${edit}">
										Edit Employee
									</c:if>
								<c:if test="${add}">
										Add Employee
									</c:if></h1>
				</div>
				<div class="card-body">
					<form action="<%=request.getContextPath()%>/AddEmployeeController"
						method="POST" onsubmit="return checkSubmitEmployee();">
						<input type="text" class="form-control" id="id" name="id"
							placeholder="Enter full name" value="${employee.employeeId }"
							hidden="true">
						<div class="row">
							<div class="col-md-3">
								<label for="title"><b>Full name <span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="employeename"
									name="name" placeholder="Enter full name"
									value="${employee.name }">
								<div class="invalid-feedback" id="invalid-employeename">Can
									not be null</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="phone"><b>Phone number<span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="tel" class="form-control" id="phone" name="phone"
									placeholder="Enter phone number" value="${employee.phone}">
								<div class="invalid-feedback" id="invalid-phone">Must be
									number (10-11 number)</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="dob"><b>Date of birth<span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="input-group col-md-7">
								<input type="date" class="form-control" id="dob" name="dob"
									placeholder="dd/mm/yyyy" value="${employee.birthdate}">
							</div>

							<div class="invalid-feedback" id="invalid-dob">Can not be
								null</div>
						</div>
						<p></p>
						<div class="row">
							<div class="col-md-3">
								<label for="sex"><b>Sex<span class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="radio" id="male" value="m" name="sex"
									${employee.sex eq "m" ? 'checked' : ''}> Male <input
									type="radio" id="female" value="f" name="sex"
									style="margin-left: 15px;"
									${employee.sex eq "f" ? 'checked' :''}> Female
									
									<div class="invalid-feedback" id="invalid-sex">Must be
								checked</div>
							</div>
							<div class="invalid-feedback" id="invalid-sex">Must be
								checked</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="address"><b>Address</b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="address"
									name="address" placeholder="Enter address"
									value="${employee.address}">
							</div>
						</div>

						<div class="row">
							<div class="col-md-3">
								<label for="address"><b>Email</b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="email" name="email"
									placeholder="Enter email" value="${employee.email}">
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="title"><b>Account<span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="account"
									name="account" placeholder="Enter account"
									value="${employee.account }">
								<div class="invalid-feedback" id="invalid-account">Can not
									be null</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="title"><b>Password<span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="Enter Password"
									value="${employee.password }">
								<div class="invalid-feedback" id="invalid-password">Can
									not be null</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="department"><b>Department<span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<select class="form-control" id="department" name="department">
									<option
										${employee.department eq "Employee" ? 'selected="selected"' : ''}>Employee</option>
									<option
										${"Parking" eq bookingOffice.place ? 'selected="selected"' : ''}>Parking</option>
									<option
										${"Service" eq bookingOffice.place ? 'selected="selected"' : ''}>Service</option>
								</select>
							</div>
						</div>

						<div class="text-center">
							<button type="button"
								onclick="location.href='<%=request.getContextPath()%>/ViewListEmployee'"
								class="btn btn-primary text-white">
								<i class="fas fa-backward"></i>&#160;Back
							</button>
							<button type="reset" class="btn btn-warning text-white">
								<i class="fas fa-undo"></i>&#160;Reset
							</button>
							<button type="submit" class="btn btn-success" id="button"
								name="button"
								<c:if test="${edit}">
										value = "Edit"
									</c:if>
								<c:if test="${add}">
										value="Add"
									</c:if>>
								<c:if test="${add}">
									<i class="fas fa-plus"></i>&#160;Add
									</c:if>
								<c:if test="${edit}">
									<i class="fas fa-edit"></i>&#160;Edit
									</c:if>
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
<script src="<%=request.getContextPath()%>/resources/js/myJavaScript.js"></script>
</html>
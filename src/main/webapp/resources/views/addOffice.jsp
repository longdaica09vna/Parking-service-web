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
			<c:if test="${errorSQL}">
				<div class="text-danger">Connect SQL Server fail</div>
			</c:if>
			<c:if test="${errorAdd}">
				<div class="text-danger">Add fail</div>
			</c:if>
			<div class="card">
				<div class="card-header">
					<h1 class="card-title"><c:if test="${edit}">
										Edit Booking Office
									</c:if>
								<c:if test="${add}">
										Add Booking Office
									</c:if></h1>
				</div>
				<div class="card-body">
					<form action="<%=request.getContextPath()%>/addOfficeController"
						method="POST" onsubmit="return checkSubmitOffice();">
						<input type="text" class="form-control" id="id" name="id"
							value="${bookingOffice.office_ID}" hidden="true">
						<div class="row">
							<div class="col-md-3">
								<label for="title"><b>Booking office name <span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="Enter booking office name"
									value="${bookingOffice.name }"
									<c:if test="${view}">
										readonly
									</c:if>>
								<div class="invalid-feedback" id="invalid-name">Can not be
									null</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="trip"><b>Trip<span class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<select class="form-control" id="trip" name="trip"
									<c:if test="${view}">
										disabled
									</c:if>>
									<c:forEach items="${trips}" var="trips">
										<option value="${trips.trip_ID}"
											${trips.trip_ID == bookingOffice.trip_ID ? 'selected="selected"' : ''}>${trips.destination }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="phone"><b>Phone number</b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="tel" class="form-control" id="phone" name="phone"
									placeholder="Enter phone number" value="${bookingOffice.phone}"
									<c:if test="${view}">
										readonly
									</c:if>>
								<div class="invalid-feedback" id="invalid-phone">Must be
									number (10-11 number)</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="place"><b>Place<span class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<select class="form-control" id="place" name="place"
									<c:if test="${view}">
										disabled
									</c:if>>
									<option
										${bookingOffice.place eq "Quay so 1" ? 'selected="selected"' : ''}>Quay
										so 1</option>
									<option
										${"Quay so 2" eq bookingOffice.place ? 'selected="selected"' : ''}>Quay
										so 2</option>
									<option
										${"Quay so 3" eq bookingOffice.place ? 'selected="selected"' : ''}>Quay
										so 3</option>
									<option
										${"Quay so 4" eq bookingOffice.place ? 'selected="selected"' : ''}>Quay
										so 4</option>
									<option
										${"Quay so 5" eq bookingOffice.place ? 'selected="selected"' : ''}>Quay
										so 5</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="price"><b>Price<span class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="number" name="price"
									placeholder="Enter price" value="${bookingOffice.price}"
									<c:if test="${view}">
										readonly
									</c:if>>
								<div class="invalid-feedback" id="invalid-number">Must be
									number</div>
							</div>
							<span class="col-md-1">&#160;(VND)</span>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label><b>Contract deadline<span class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="input-group col-md-7">
								<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-default">From
										Date</span>
								</div>
								<input type="date" class="form-control" aria-label="Default"
									aria-describedby="inputGroup-sizing-default" id="dateFrom"
									name="dateFrom" placeholder="dd/MM/yyyy"
									value="${bookingOffice.startContractDeadline}"
									<c:if test="${view}">
										readonly
									</c:if>>
								<div class="invalid-feedback" id="invalid-datefrom">Can
									not be null</div>
							</div>
						</div>
						<p></p>
						<div class="row">
							<div class="col-md-3"></div>
							<div class="input-group col-md-7">
								<div class="input-group-prepend">
									<span class="input-group-text" id="inputGroup-sizing-default">To
										Date</span>
								</div>
								<input type="date" class="form-control" aria-label="Default"
									aria-describedby="inputGroup-sizing-default" id="dateTo"
									name="dateTo" placeholder="dd/MM/yyyy"
									value="${bookingOffice.endContractDeadline}"
									<c:if test="${view}">
										readonly
									</c:if>>
								<div class="invalid-feedback" id="invalid-dateto">Can not
									be null</div>
							</div>
						</div>
						<p></p>
						<div class="text-center">
							<button type="reset" class="btn btn-warning text-white"
								<c:if test="${view}">
										disabled hidden="true"
									</c:if>>
								<i class="fas fa-undo"></i>&#160;Reset
							</button>
							<button type="submit" class="btn btn-success" name="button"
								<c:if test="${view}">
										disabled hidden="true"
									</c:if>
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
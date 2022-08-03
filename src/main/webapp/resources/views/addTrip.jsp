<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<link href="resources/css/css/myCSS.css" rel="stylesheet">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="resources/css/fontawesome-free/css/all.min.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<title>Add Trip</title>
</head>
<body>

	<%@include file="_header.jsp"%>
	<div class="row">
		<div class="col-lg-2 border sidebar">
			<%@include file="_sidebar.jsp"%>
		</div>

		<div class="col-lg-9">
			<div class="card">
				<div class="card-header">
					<h1 class="card-title"><c:if test="${edit}">
										Edit Trip
									</c:if>
								<c:if test="${add}">
										Add Trip
									</c:if></h1>
					<c:if test="${addFail}">
						<div class="text-danger">Add fail!</div>
					</c:if>
				</div>
				<div class="card-body">
					<form action="<%=request.getContextPath()%>/AddTripController"
						method="POST" onsubmit="return checkSubmitTrip();">
						<input type="text" class="form-control" id="id" name="id" value="${trip.trip_ID }" hidden="true">
						<div class="row">
							<div class="col-md-3">
								<label for="destination"><b>Destination <span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="destination"
									name="destination" placeholder="Enter destination"
									value="${trip.destination }"
									<c:if test="${view}">disabled</c:if>>
								<div class="invalid-feedback" id="invalid-destination">Can
									not be null</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-3">
								<label for="departureTime"><b>Departure time <span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="time" class="form-control" id="departureTime"
									name="departureTime" value="${trip.departureTime }"
									<c:if test="${view}">disabled</c:if>>
								<div class="invalid-feedback" id="invalid-time">Can
									not be null</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-3">
								<label for="driver"><b>Driver <span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="driver"
									name="driver" placeholder="Enter driver" value="${trip.driver}"
									<c:if test="${view}">disabled</c:if> >
									<div class="invalid-feedback" id="invalid-driver">Can
									not be null</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-3">
								<label for="carType"><b>Car type <span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="carType"
									name="carType" placeholder="Enter car type"
									value="${trip.carType}" <c:if test="${view}">disabled</c:if>>
									<div class="invalid-feedback" id="invalid-carType">Can
									not be null</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-3">
								<label for="maximumOnlineTicketNumber"><b>Maximum
										Online Booked ticket number <span class="text-danger">&#160;(*)</span>
								</b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="number" class="form-control"
									id="number" name="maximumOnlineTicketNumber"
									min="0" max="1000"
									value="${trip.maximumOnlineTicketNumber==null?0:trip.maximumOnlineTicketNumber}"
									<c:if test="${view}">disabled</c:if>>
									<div class="invalid-feedback" id="invalid-number">Can
									not be null</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-3">
								<label for="departureDate"><b>Departure date <span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="date" class="form-control" id="departureDate"
									name="departureDate" min="1900-01-01" max="2030-12-31"
									value="${trip.departureDate}"
									<c:if test="${view}">disabled</c:if>>
									<div class="invalid-feedback" id="invalid-date">Can
									not be null</div>
							</div>
						</div>
						<c:if test="${view}">
							<div class="row">
								<div class="col-md-3">
									<label><b>Booked Ticket Number</b></label>
								</div>
								<div class="input-group col-md-7">
									<input type="text" class="form-control" aria-label="Default"
										aria-describedby="inputGroup-sizing-default"
										id="bookedTicketNumber" name="bookedTicketNumber"
										value="${trip.bookedTicketNumber }" readonly>
								</div>
							</div>
						</c:if>
						<div class="text-center">
							<button type="reset" class="btn btn-warning text-white"
								<c:if test="${view}">
										disabled hidden="true"
									</c:if>>
								<i class="fas fa-undo"></i>&#160;Reset
							</button>
							<button type="submit" class="btn btn-success" id="button"
								name="button"
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
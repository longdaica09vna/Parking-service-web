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
<link href="resources/css/fontawesome-free/css/all.min.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">

</head>
<body>

	<%@include file="_header.jsp"%>
	
	<div class="page-row row page-row-expanded">
		<nav class="col-lg-2 border sidebar flex-column">
			<%@include file="_sidebar.jsp"%>
		</nav>

		<div class="col-lg-9 main">
			<c:if test="${errorSQL}">
				<div class="text-danger">Connect SQL Server fail</div>
			</c:if>

			<div class="card">
				<div class="card-header">
					<h1 class="card-title"><c:if test="${edit}">
										Edit Ticket
									</c:if>
								<c:if test="${add}">
										Add Ticket
									</c:if></h1>
					<c:if test="${errorAdd}">
						<div class="text-danger">Add fail</div>
					</c:if>
					<c:if test="${errorEdit}">
						<div class="text-danger">Edit fail</div>
					</c:if>
				</div>
				<div class="card-body">
					<form action="<%=request.getContextPath()%>/addTicketController"
						method="POST" onsubmit="return checkSubmitTicket();">
						<div class="row">
						<input type="text" class="form-control" id="id" name="id" value="${ticket.ticket_ID }" hidden="true">
							<div class="col-md-3">
								<label for="title"><b>Customer name <span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="customerName"
									name="customerName" placeholder="Enter customer name"
									value="${ticket.customerName }">
								<div class="invalid-feedback" id="invalid-customerName">Can not be null</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-3">
								<label><b>Booking time<span class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="input-group col-md-7">
								<input type="time" class="form-control timepicker"
									aria-label="Default"
									aria-describedby="inputGroup-sizing-default" id="booktime"
									name="booktime" placeholder="--:-- --"
									value="${ticket.bookingTime}">
								<div class="invalid-feedback" id="invalid-booktime">Can not be null</div>
							</div>
						</div>
						<p></p>
						<div class="row">
							<div class="col-md-3">
								<label for="trip"><b>Trip<span class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<select class="form-control" id="trip" name="trip">
									<c:forEach items="${trips}" var="trips">
										<option value="${trips.trip_ID}"
											${trips.trip_ID == ticket.trip_ID ? 'selected="selected"' : ''}>${trips.destination }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="licensePlates"><b>License Plate<span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<select class="form-control" id="licensePlates"
									name="licensePlates">
									<c:forEach items="${cars}" var="cars">
										<option
											${cars.licensePlate == ticket.licensePlate ? 'selected="selected"' : ''}>${cars.licensePlate}</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<c:if test="${view}">
							<div class="row">
								<div class="col-md-3">
									<label><b>Departure Date</b></label>
								</div>
								<div class="input-group col-md-7">
									<input type="text" class="form-control"
										aria-label="Default"
										aria-describedby="inputGroup-sizing-default" id="departureDate"
										name="departureDate"
										<c:forEach items="${trips}" var="trips">
											value="${trips.trip_ID == ticket.trip_ID ? df.format(trips.departureDate) : ''}"
										</c:forEach>
										readonly>
								</div>
							</div>
						</c:if>
						<p></p>
						<c:if test="${view}">
							<div class="row">
								<div class="col-md-3">
									<label><b>Departure Time</b></label>
								</div>
								<div class="input-group col-md-7">
									<input type="text" class="form-control"
										aria-label="Default"
										aria-describedby="inputGroup-sizing-default" id="departureTime"
										name="departureTime"
										<c:forEach items="${trips}" var="trips">
											value="${trips.trip_ID == ticket.trip_ID ? trips.departureTime : ''}"
										</c:forEach>
										readonly>
								</div>
							</div>
						</c:if>

						<p></p>
						<div class="text-center">
							<button type="reset" class="btn btn-warning text-white">
								<i class="fas fa-undo"></i>&#160;Reset
							</button>
							<button type="submit" class="btn btn-success" name="button"
								<c:if test="${edit}">
										value = "Edit"
									</c:if> <c:if test="${add}">
										value="Add"
									</c:if>><c:if test="${add}">
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
<link href="resources/css/css/myCSS.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/resources/js/myJavaScript.js"></script>
</html>
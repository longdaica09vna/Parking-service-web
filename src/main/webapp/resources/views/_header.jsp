<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<link href="resources/css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link href="resources/css/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<link href="resources/css/css/myCSS.css" rel="stylesheet">
</head>

<body>

	<nav class="navbar navbar-light bg-light static-top">
		<c:if test="${CarPage}">
			<a class="navbar navbar-brand" href="#"><i class="fas fa-car"></i>
				&#160;Car</a>
		</c:if>
		<c:if test="${EmployeePage}">
			<a class="navbar navbar-brand" href="#"><i class="fas fa-users"></i>
				&#160;Employee</a>
		</c:if>
		<c:if test="${TripPage}">
			<a class="navbar navbar-brand" href="#"><i
				class="fas fa-plane rotate-45-left"></i> &#160;Trip</a>
		</c:if>
		<c:if test="${ParkingLotPage}">
			<a class="navbar navbar-brand" href="#"> <i
				class="fas fa-map-marker-alt"></i> &#160;Parking lot
			</a>
		</c:if>
		<c:if test="${BookingOfficePage}">
			<a class="navbar navbar-brand" href="#"><i
				class="fas fa-shopping-cart"></i> &#160;Booking Office</a>
		</c:if>
		<c:if test="${TicketPage}">
			<a class="navbar navbar-brand" href="#"><i
				class="fas fa-ticket-alt rotate-45-left"></i> &#160;Ticket</a>
		</c:if>
		<c:if test="${IndexPage}">
			<a class="navbar navbar-brand" href="#"><i class="fas fa-car"></i>
				&#160;Car Park</a>
		</c:if>

		<c:if test="${LoggedIn != null}">
			<div class="navbar-right">
				<a href="#"><i
				class="fas fa-user"></i>&#160; ${LoggedIn.name}</a>&#160; &#160;<a
					href="http://localhost:8080/CarPark/logoutController"><i
					class="fas fa-sign-out-alt"></i>&#160;logout</a>
			</div>
		</c:if>
	</nav>

</body>
</html>
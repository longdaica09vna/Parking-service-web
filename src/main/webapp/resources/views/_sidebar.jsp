<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<link href="resources/css/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="resources/css/bootstrap/css/bootstrap.min.js"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.9.2/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link href="resources/css/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<link href="resources/css/css/myCSS.css" rel="stylesheet">
<link rel="stylesheet" href="resources/css/css/accordion.css">
<script src="resources/js/accordion.js"></script>

</head>

<body>
	<div class="container-fluid">
		<div class="accordion-started accordion-bral row">

			<div ${LoggedIn.department ne "Employee"?'hidden="true"':''}>

				<input class="ac-input" id="ac-1" name="accordion-1" type="radio"
					<c:if test="${EmployeePage}">checked=""</c:if>> <label
					class="ac-label" for="ac-1"><i class="fas fa-chart-bar"></i>&#160;Employee
					manager &#160;<i class="fas fa-angle-left"></i> </label>
				<div class="article ac-content">
					<div class="border-bottom">
						<a href="<%=request.getContextPath()%>/ViewListEmployee"><i
							class="fas fa-list"></i>&#160;Employee list</a>
					</div>
					<div class="border-bottom">
						<a href="<%=request.getContextPath()%>/AddEmployeeController"><i
							class="fas fa-plus"></i>&#160;Add employee</a>
					</div>
				</div>
			</div>


			<div ${LoggedIn.department ne "Parking"?'hidden="true"':''}>

				<input class="ac-input" id="ac-2" name="accordion-1" type="radio"
					<c:if test="${BookingOfficePage}">checked=""</c:if>> <label
					class="ac-label" for="ac-2"><i class="fas fa-shopping-cart"></i>&#160;Booking
					office manager &#160;<i class="fas fa-angle-left"></i> </label>
				<div class="article ac-content">
					<div class="border-bottom">
						<a href="<%=request.getContextPath()%>/bookingOfficeController"><i
							class="fas fa-list"></i>&#160;Booking office list</a>
					</div>
					<div class="border-bottom">
						<a href="<%=request.getContextPath()%>/addOfficeController"><i
							class="fas fa-plus"></i>&#160;Add booking office</a>
					</div>
				</div>
			</div>


			<div ${LoggedIn.department ne "Parking"?'hidden="true"':''}>

				<input class="ac-input" id="ac-3" name="accordion-1" type="radio"
					<c:if test="${ParkingLotPage}">checked=""</c:if>> <label
					class="ac-label" for="ac-3"> <i
					class="fas fa-map-marker-alt"></i>&#160;Parking lot manager
					&#160;&#160;<i class="fas fa-angle-left"></i></label>
				<div class="article ac-content">
					<div class="border-bottom">
						<a href="<%=request.getContextPath()%>/ViewParkingServlet"><i
							class="fas fa-list"></i>&#160;Parking lot list</a>
					</div>
					<div class="border-bottom">
						<a href="<%=request.getContextPath()%>/AddParkingServlet"><i
							class="fas fa-plus"></i>&#160;Add parking lot</a>
					</div>
				</div>
			</div>


			<div ${LoggedIn.department ne "Parking"?'hidden="true"':''}>

				<input class="ac-input" id="ac-4" name="accordion-1" type="radio"
					<c:if test="${CarPage}">checked=""</c:if>> <label
					class="ac-label" for="ac-4"><i class="fas fa-car-alt"></i>&#160;Car
					manager &#160;<i class="fas fa-angle-left"></i></label>
				<div class="article ac-content">
					<div class="border-bottom">
						<a href="<%=request.getContextPath()%>/DisplayListCarController"><i class="fas fa-list"></i>&#160;Car list</a>
					</div>
					<div class="border-bottom">
						<a href="<%=request.getContextPath()%>/AddCarController"><i class="fas fa-plus"></i>&#160;Add car</a>
					</div>
				</div>
			</div>

			<div ${LoggedIn.department ne "Service"?'hidden="true"':''}>

				<input class="ac-input" id="ac-5" name="accordion-1" type="radio"
					<c:if test="${TripPage}">checked=""</c:if>> <label
					class="ac-label" for="ac-5"><i
					class="fas fa-plane rotate-45-left"></i>&#160;Trip manager &#160;<i
					class="fas fa-angle-left"></i></label>
				<div class="article ac-content">
					<div class="border-bottom">
						<a href="<%=request.getContextPath()%>/TripListController"><i
							class="fas fa-list"></i>&#160;Trip list</a>
					</div>
					<div class="border-bottom">
						<a href="<%=request.getContextPath()%>/AddTripController"><i
							class="fas fa-plus"></i>&#160;Add trip</a>
					</div>
				</div>
			</div>
			<div ${LoggedIn.department ne "Service"?'hidden="true"':''}>

				<input class="ac-input" id="ac-6" name="accordion-1" type="radio"
					<c:if test="${TicketPage}">checked=""</c:if>> <label
					class="ac-label" for="ac-6"><i
					class="fas fa-ticket-alt rotate-45-left"></i>&#160;Ticket manager
					&#160;<i class="fas fa-angle-left"></i></label>
				<div class="article ac-content">
					<div class="border-bottom">
						<a href="<%=request.getContextPath()%>/ticketListController"><i
							class="fas fa-list"></i>&#160;Ticket list</a>
					</div>
					<div class="border-bottom">
						<a href="<%=request.getContextPath()%>/addTicketController"><i
							class="fas fa-plus"></i>&#160;Add ticket</a>
					</div>
				</div>
			</div>

		</div>
	</div>

	<script>
		
	</script>


</body>

</html>

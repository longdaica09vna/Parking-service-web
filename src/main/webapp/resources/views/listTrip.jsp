<%@page import="java.util.List"%>
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
			<h1 class="card-title">Trip List</h1>
			<div class="card">
				<div class="card-header">

					<form action="<%=request.getContextPath()%>/TripListController"
						method="POST" class=" float-right">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-search"></i></span>
							</div>
							
							<input type="search" id="gsearch" name="gsearch"
								placeholder="User Search" value="${gsearch}"> 
							<p>&#160;</p>
								<button type="submit" class="btn btn-primary text-white">
								Search</button>
							<p>&#160;</p>
							<select id="day" name="day">
								<option value="0" selected>--</option>
								<c:forEach var="a" begin="1" end="31">
									<option value="${a}" <c:if test="${sday==a}">selected</c:if>>
										<c:if test="${a<10}">0</c:if>${a}
								</c:forEach>
							</select> 
							<p>&#160;</p>
							<select id="month" name="month">
								<option value="0" selected>--</option>
								<c:forEach var="a" begin="1" end="12">
									<option value="${a}" <c:if test="${smonth==a}">selected</c:if>><c:if
											test="${a<10}">0</c:if>${a}</option>
								</c:forEach>
							</select>
							<p>&#160;</p>
							<select id="year" name="year">
								<option value="0" selected>----</option>
								<c:forEach var="a" begin="1900" end="2050">
									<option value="${a}" <c:if test="${syear==a}">selected</c:if>>${a}</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div>
			</div>

			<c:if test="${errorSQL}">
				<div class="text-danger">Connect SQL Server fail</div>
			</c:if>
			<div class="card-body">
				<!-- Search(word, date) -->
				<div></div>

				<table class="table table-striped table-bordered">
					<thead>
						<tr class="table-secondary">
							<th scope="col">No</th>
							<th scope="col">Destination</th>
							<th scope="col">Departure time</th>
							<th scope="col">Driver</th>
							<th scope="col">Car Type</th>
							<th scope="col">Booked ticket number</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pageList}" var="trip">
							<tr>
								<td>${trip.getTrip_ID()}</td>
								<td>${trip.getDestination()}</td>
								<td>${trip.getDepartureTimeString()}</td>
								<td>${trip.getDriver()}</td>
								<td>${trip.getCarType()}</td>
								<td>${trip.getBookedTicketNumber()}</td>
								<td><a href="ViewTripController?id=${trip.getTrip_ID()}"><i
										class="fa fa-eye"></i>&#160;View</a> <a
									href="AddTripController?id=${trip.getTrip_ID()}"><i
										class="fas fa-edit"></i>&#160;Edit</a> <a
									href="DeleteTripController?id=${trip.getTrip_ID()}"><i
										class="fas fa-trash-alt"></i>&#160;Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<nav aria-label="Page navigation example">
					<ul class="pagination">
						<li
							class="page-item <c:if test="${currentPage == 1}">disabled</c:if>"><a
							class="page-link"
							href="<%=request.getContextPath()%>/TripListController?page=${currentPage - 1}&gsearch=${gsearch}&day=${sday}&month=${smonth}&year=${syear}">Previous</a></li>
						<c:forEach begin="1" end="${noOfPages}" var="i">
							<li
								class="page-item <c:if test="${currentPage eq i}">active</c:if>"><a
								class="page-link"
								href="<%=request.getContextPath()%>/TripListController?page=${i}&gsearch=${gsearch}&day=${sday}&month=${smonth}&year=${syear}">${i}</a></li>
						</c:forEach>
						<li
							class="page-item <c:if test="${currentPage == noOfPages}">disabled</c:if>"><a
							class="page-link"
							href="<%=request.getContextPath()%>/TripListController?page=${currentPage + 1}&gsearch=${gsearch}&day=${sday}&month=${smonth}&year=${syear}">Next</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	  let selectYear = $("#year");
	  let selectMonth = $("#month");
	  let selectDay = $("#day");
	  let currentYear = new Date().getFullYear();

	  var month = ${month}
	  var year = ${year}
	  var day = ${day}
	  
	  let yearElem = document.createElement("option");
	  yearElem.value = 0;
	  yearElem.textContent = "----";
	  if(year == 0) {yearElem.selected=true;}
	  selectYear.append(yearElem);
	  
	  let monthElem = document.createElement("option");
	  monthElem.value = -1;
	  monthElem.textContent = "--";
	  if(month == -1) monthElem.selected=true;
	  selectMonth.append(monthElem);
	  
	  for (var y = 1900; y <=currentYear; y++) {
	    let date = new Date(currentYear);
	    let yearElem = document.createElement("option");
	    yearElem.value = y;
	    yearElem.textContent = y;
	    if(year == y) {yearElem.selected=true;}
	    selectYear.append(yearElem);
	  }
	
	  for (var m = 0; m <12; m++) {
	    let monthElem = document.createElement("option");
	    monthElem.value = m;
	    monthElem.textContent = m+1;
	    if(month == m) monthElem.selected=true;
	    selectMonth.append(monthElem);
	  }
	  
	  var dayDef = document.createElement("option");
	    dayDef.value = 0;
	    dayDef.textContent = "--";
	    if(day == 0) dayDef.selected=true;
	    selectDay.append(dayDef);

	  
	    
	  selectYear.val(year);
	  selectYear.on("change", AdjustDays);
	  selectMonth.val(month);
	  selectMonth.on("change", AdjustDays);

	  AdjustDays();
	  selectDay.val(day)
	
	  function AdjustDays() {
	    var year = selectYear.val();
	    var month = parseInt(selectMonth.val()) + 1;
	    selectDay.empty();
	    
	    var dayDef = document.createElement("option");
	    dayDef.value = 0;
	    dayDef.textContent = "--";
	    if(day == 0) dayDef.selected=true;
	    selectDay.append(dayDef);
	    
	    
	    	var days = new Date(year, month, 0).getDate();

	    	//lets create the days of that month
	    	for (var d = 1; d <= days; d++) {
	      	var dayElem = document.createElement("option");
	      	dayElem.value = d;
	      	dayElem.textContent = d;
	      	if(day==d) dayElem.selected = true;
	     	selectDay.append(dayElem);
	    	}
	    }
	  
	});
    </script>
<link href="resources/css/fontawesome-free/css/all.min.css" rel="stylesheet">

<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<link href="resources/css/css/landing-page.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">

</head>
<body>

	<%@include file="_header.jsp"%>
	<div class="row">
		<div class="col-md-2 border sidebar">
			<%@include file="_sidebar.jsp"%>
		</div>

		<div class="col-md-9">
			<c:if test="${errorSQL}">
				<div class="text-danger">Connect SQL Server fail</div>
			</c:if>

			<h1>Ticket List</h1>
			<c:if test="${deleteSuccess}">
				<div class="text-success">Delete successfully</div>
			</c:if>
			<c:if test="${deleteError}">
				<div class="text-danger">Delete fail</div>
			</c:if>
			<div class="card">
				<div class="card-header">
				<form action="<%=request.getContextPath()%>/ticketListController"
						method="POST" id="search" class=" float-right">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-search"></i></span>
							</div>
							<input type="text" placeholder="Booking office Search" name="search" value="${search}">
							<p>&#160;</p>
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-filter"></i></span>
							</div>
							<select class="form-control" id="filter" name="filter"
								<c:if test="${view}">
										disabled
									</c:if>>
								<option value="Trip"
									${"Trip" == filter ? 'selected="selected"' : ''}>Trip</option>
								<option value="License Plate"
									${filter == "License Plate" ? 'selected="selected"' : ''}>License Plate</option>
								<option value="Customer Name"
									${"Customer Name" == filter ? 'selected="selected"' : ''}>Customer Name</option>
							</select>
							<p>&#160;</p>
							<button type="submit" class="btn btn-primary text-white">
								Search</button>
							<p>&#160;</p>
							<select id="day" name="day">
							</select>
							<p>&#160;</p>
							<select id="month" name="month">
							</select>
							<p>&#160;</p>
							<select id="year" name="year">
							</select>
						</div>

					</form>
					
				</div>
				<div class="card-body" style="overflow-x: auto;">
					<table class="table table-striped table-bordered">
						<thead>
							<tr class="table-secondary">
								<th scope="col">No</th>
								<th scope="col">Trip</th>
								<th scope="col">Customer</th>
								<th scope="col">License plate</th>
								<th scope="col">Booking time</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${tickets}" var="tickets">
								<tr>
									<td>${tickets.ticket_ID}</td>
									<td>${tripDAO.getByID(tickets.trip_ID).getDestination()}</td>
									<td>${tickets.customerName }</td>
									<td>${tickets.licensePlate }</td>
									<td>${tickets.bookingTime }</td>
									<td><a href="<%=request.getContextPath()%>/addTicketController?id=${tickets.ticket_ID}"><i
											class="fas fa-eye"></i> &#160;View</a>
										<a href="<%=request.getContextPath()%>/deleteTicketController?id=${tickets.ticket_ID}"><i
											class="fas fa-trash-alt"></i> &#160;Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<li class="page-item <c:if test="${currentPage == 1}">disabled</c:if>" ><a class="page-link" href="<%=request.getContextPath()%>/ticketListController?page=${currentPage - 1}<c:if test="${isSearching}">&search=${search}&filter=${filter}</c:if><c:if test="${isSearchingDate}">&year=${year}&month=${month}&day=${day}</c:if>">Previous</a></li>
							 <c:forEach begin="1" end="${noOfPages}" var="i">
							 	<li class="page-item <c:if test="${currentPage eq i}">active</c:if>"><a class="page-link" href="<%=request.getContextPath()%>/ticketListController?page=${i}<c:if test="${isSearching}">&search=${search}&filter=${filter}</c:if><c:if test="${isSearchingDate}">&year=${year}&month=${month}&day=${day}</c:if>">${i}</a></li>
							 </c:forEach>
							<li class="page-item <c:if test="${currentPage == noOfPages}">disabled</c:if>" ><a class="page-link" href="<%=request.getContextPath()%>/ticketListController?page=${currentPage + 1}<c:if test="${isSearching}">&search=${search}&filter=${filter}</c:if><c:if test="${isSearchingDate}">&year=${year}&month=${month}&day=${day}</c:if>">Next</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>

</body>
<script src="<%=request.getContextPath()%>/resources/js/validation.js"></script>
</html>
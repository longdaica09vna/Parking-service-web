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
<link href="resources/css/css/myCSS.css" rel="stylesheet">
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

			<h1>Booking Office List</h1>
			<div class="card">
				<div class="card-header">
					<form
						action="<%=request.getContextPath()%>/bookingOfficeController"
						method="POST" id="search" class=" float-right">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-search"></i></span>
							</div>
							<input type="text" placeholder="Booking office Search"
								name="search" value="${search}">
							<p>&#160;</p>
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-filter"></i></span>
							</div>
							<select class="form-control" id="filter" name="filter">
								<option value="Name"
									${filter == "Name" ? 'selected="selected"' : ''}>Name</option>
								<option value="Trip"
									${"Trip" == filter ? 'selected="selected"' : ''}>Trip</option>
							</select>
							<p>&#160;</p>
							<button type="submit" class="btn btn-primary text-white">
								Search</button>
						</div>

					</form>
				</div>
				<div class="card-body" style="overflow-x: auto;">

					<table class="table table-striped table-bordered">
						<thead>
							<tr class="table-secondary">
								<th scope="col">ID</th>
								<th scope="col">Booking Office</th>
								<th scope="col">Trip</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${bookingOffices}" var="bookingOffices">
								<tr>
									<td>${bookingOffices.office_ID}</td>
									<td>${bookingOffices.name}</td>
									<td>${tripDAO.getByID(bookingOffices.trip_ID).getDestination() }</td>
									<td><a
										href="<%=request.getContextPath()%>/addOfficeController?id=${bookingOffices.office_ID}"><i
											class="fas fa-eye"></i> &#160;View&#160;</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<nav aria-label="Page navigation example">
						<ul class="pagination">
							<li
								class="page-item <c:if test="${currentPage == 1}">disabled</c:if>"><a
								class="page-link"
								href="<%=request.getContextPath()%>/bookingOfficeController?page=${currentPage - 1}<c:if test="${isSearching}">&search=${search}&filter=${filter}</c:if>">Previous</a></li>
							<c:forEach begin="1" end="${noOfPages}" var="i">
								<li
									class="page-item <c:if test="${currentPage eq i}">active</c:if>"><a
									class="page-link"
									href="<%=request.getContextPath()%>/bookingOfficeController?page=${i}<c:if test="${isSearching}">&search=${search}&filter=${filter}</c:if>">${i}</a></li>
							</c:forEach>
							<li
								class="page-item <c:if test="${currentPage == noOfPages}">disabled</c:if>"><a
								class="page-link"
								href="<%=request.getContextPath()%>/bookingOfficeController?page=${currentPage + 1}<c:if test="${isSearching}">&search=${search}&filter=${filter}</c:if>">Next</a></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>

</body>
<script src="<%=request.getContextPath()%>/resources/js/validation.js"></script>
</html>
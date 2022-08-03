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
<script
	src="<%=request.getContextPath()%>/resources/js/validateParking.js"></script>
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
			<h1 class="card-title">Parking lot list</h1>
			<div class="card">
				<div class="card-header">

					<form
						action="<%=request.getContextPath()%>/ViewParkingServlet"
						method="POST" id="search" class=" float-right">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-search"></i></span>
							</div>
							<input type="text" placeholder="Parking lot Search"
								name="keyword" value="${keyword}">
							<p>&#160;</p>
							<div class="input-group-prepend">
								<span class="input-group-text"><i class="fas fa-filter"></i></span>
							</div>
							<select class="form-control" id="type" name="type">
								<option value="place" ${type eq "place" ? 'selected="selected"' : ''}>Place</option>
								<option value="area" ${type eq "area" ? 'selected="selected"' : ''}>Area</option>
								<option value="price" ${type eq "price" ? 'selected="selected"' : ''}>Price</option>
								<option value="name" ${type eq "name" ? 'selected="selected"' : ''}>Parking lot</option>
							</select>
							<p>&#160;</p>
							<button type="submit" class="btn btn-primary text-white">
								Search</button>
						</div>

					</form>
				</div>
				<div class="card-body">
					<c:if test="${not empty noParking}">
						<div class="text-center">
							<a>${noParking}</a>
						</div>
					</c:if>
					<c:if test="${empty noParking}">
						<table class="table table-striped table-bordered">
							<thead>
								<tr class="table-secondary">
									<th scope="col">ID</th>
									<th scope="col">Parking lot</th>
									<th scope="col">Place</th>
									<th scope="col">Area</th>
									<th scope="col">Price</th>
									<th scope="col">Status</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${parkings}" var="p">
									<tr>
										<td>${p.id}</td>
										<td>${p.name}</td>
										<td>${p.place}</td>
										<td>${p.area}</td>
										<td>${p.price}</td>
										<td>${p.status}</td>
										<td><span><a
												href="AddParkingServlet?id=${p.id}"><i class="fas fa-edit"></i>&#160;Edit</a></span> <span><a
												href="DeleteParkingServlet?id=${p.id}"><i class="fas fa-trash-alt"></i>&#160;Delete</a></span></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<ul class="pagination">
							<li
								class="page-item <c:if test="${currentPage == 1}">disabled</c:if>"><a
								class="page-link"
								href="<%=request.getContextPath()%>/ViewParkingServlet?page=${currentPage - 1}<c:if test="${isSearching}">&keyword=${keyword}&type=${type}</c:if>">Previous</a></li>
							<c:forEach begin="1" end="${noOfPages}" var="i">
								<li
									class="page-item <c:if test="${currentPage eq i}">active</c:if>"><a
									class="page-link"
									href="<%=request.getContextPath()%>/ViewParkingServlet?page=${i}<c:if test="${isSearching}">&keyword=${keyword}&type=${type}</c:if>">${i}</a></li>
							</c:forEach>
							<li
								class="page-item <c:if test="${currentPage == noOfPages}">disabled</c:if>"><a
								class="page-link"
								href="<%=request.getContextPath()%>/ViewParkingServlet?page=${currentPage + 1}<c:if test="${isSearching}">&keyword=${keyword}&type=${type}</c:if>">Next</a></li>
						</ul>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
<script
	src="<%=request.getContextPath()%>/resources/js/validateParking.js"></script>
</html>
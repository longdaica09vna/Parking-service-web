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
			<c:if test="${not empty errorDatabase}">
				<div class="text-danger">${errorDatabase}</div>
			</c:if>
			<c:if test="${errorAdd}">
				<div class="text-danger">Add fail</div>
			</c:if>
			<c:if test="${errorEdit}">
				<div class="text-danger">Edit fail</div>
			</c:if>


			<div class="card">
				<div class="card-header">
					<h1 class="card-title">
						<c:if test="${edit}">
										Edit Parking Lot
									</c:if>
						<c:if test="${add}">
										Add Parking Lot
									</c:if>
					</h1>
				</div>
				<div class="card-body">
					<form action="<%=request.getContextPath()%>/AddParkingServlet"
						method="POST" id="addform"
						<c:if test="${edit}">
										 onsubmit="return checkInputEdit();"
									</c:if>
						<c:if test="${add}">
										onsubmit="return checkInputAdd();"
									</c:if>>
						<div class="row">
							<input type="text" class="form-control" id="id" name="id"
								value="${parkingLot.id }" hidden="true">
							<div class="col-md-3">
								<label for="title"><b>Parking name <span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="Enter parking name" value="${parkingLot.name }">
								<div class="invalid-feedback" id="invalid-name">Must have
									at least 4 character.</div>
							</div>

						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="trip"><b>Place<span class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<select class="form-control" name="place" id="place">
									<option value="Khu 1"
										${parkingLot.place eq "Khu 1" ? 'selected="selected"' : ''}>Khu
										1</option>
									<option value="Khu 2"
										${parkingLot.place eq "Khu 2" ? 'selected="selected"' : ''}>Khu
										2</option>
									<option value="Khu 3"
										${parkingLot.place eq "Khu 3" ? 'selected="selected"' : ''}>Khu
										3</option>
									<option value="Khu 4"
										${parkingLot.place eq "Khu 4" ? 'selected="selected"' : ''}>Khu
										4</option>

								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="phone"><b>Area</b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="number" class="form-control" id="area" name="area"
									placeholder="Enter area" value="${parkingLot.area }">
								<div class="invalid-feedback" id="invalid-area">Must have
									at least 2 digits.</div>
							</div>
							<label for="phone" class="text-danger"><b>&#160;(m2)</b></label>

						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="phone"><b>Price</b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="number" class="form-control" id="price"
									name="price" placeholder="Enter price"
									value="${parkingLot.price }">
								<div class="invalid-feedback" id="invalid-price">Must have
									at least 6 digits.</div>
							</div>
							<label for="number" class="text-danger"><b>&#160;(VND)</b></label>

						</div>
						<p></p>
						<div class="text-center">
							<button type="reset" class="btn btn-warning text-white">
								<i class="fas fa-undo"></i>&#160;Reset
							</button>
							<button type="submit" class="btn btn-success" name="button"
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
<script
	src="<%=request.getContextPath()%>/resources/js/validateParking.js"></script>

</html>
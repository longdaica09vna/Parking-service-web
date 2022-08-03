<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
			<c:if test="${errorAdd}">
				<div class="text-danger">Add fail</div>
			</c:if>
			<div class="card">
				<div class="card-header">
					<h1 class="card-title"><c:if test="${edit}">
										Edit Car
									</c:if>
								<c:if test="${add}">
										Add Car
									</c:if></h1>
				</div>
				<div class="card-body">
					<form name="addcar"
						action="<%=request.getContextPath()%>/AddCarController"
						method="POST" onsubmit="return checkSubmitCar();">
						<div class="row">
							<div class="col-md-3">
								<label for="license"><b>License Plate <span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="license"
									name="license" placeholder="Enter license plate"
									value="${car.licensePlate }" <c:if test="${edit}">
										readonly
									</c:if>>
								<div class="invalid-feedback" id="invalid-license">Can not
									be null</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="type"><b>Car Type</b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="type" name="type"
									placeholder="Enter car type" value="${car.type }">
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="color"><b>Car Color</b></label>
							</div>
							<div class="form-group col-md-7">
								<input type="text" class="form-control" id="color" name="color"
									placeholder="Enter car color" value="${car.color }">
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="company"><b>Company <span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<select class="form-control" id="company" name="company"
									<c:if test="${view}">
										disabled
									</c:if>>
									<option
										${car.company eq "Hoang Long" ? 'selected="selected"' : ''}>Hoang
										Long</option>
									<option
										${"Cam Van" eq car.company ? 'selected="selected"' : ''}>Cam
										Van</option>
									<option
										${"Phuong Trang" eq car.company ? 'selected="selected"' : ''}>Phuong
										Trang</option>
									<option
										${"Thanh Thuy" eq car.company ? 'selected="selected"' : ''}>Thanh
										Thuy</option>
									<option
										${"Phuc Loc" eq car.company ? 'selected="selected"' : ''}>Phuc
										Loc</option>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-3">
								<label for="parkingLot"><b>Parking Lot <span
										class="text-danger">&#160;(*)</span></b></label>
							</div>
							<div class="form-group col-md-7">
								<select class="form-control" name="parkingLot">
									<c:forEach items="${listParkinglot}" var="list">
										<option value="${list.getId()}"
											${list.getId() == car.park_ID ? 'selected="selected"' : ''}>${list.getName()}</option>
									</c:forEach>

								</select>
							</div>
						</div>
						<div class="text-center">
							<button type="reset" class="btn btn-warning text-white">
								<i class="fas fa-undo"></i>&#160;Reset
							</button>
							<button type="submit" class="btn btn-success" id="button"
								name="button"
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
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<link href="resources/css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
<link href="resources/css/fontawesome-free/css/all.min.css" rel="stylesheet">
<link href="resources/css/css/myCSS.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">


</head>

<body>
	<div class="container col-md-4">
		<c:if test="${errorSQL}">
			<div class="text-danger">Connect SQL Server fail</div>
		</c:if>

	</div>
	<div class="container col-md-4 offset">
		<div class="card login">
			<div class="card-header">
				<h3 class="card-title">Please sign in</h3>
			</div>
			<div class="card-body">

				<form action="<%=request.getContextPath()%>/loginController"
					method="POST" onsubmit="return checkLogin()">

					<div class="form-group">
						<input type="text" class="form-control" id="account"
							placeholder="Enter Account" name="account"
							value="${employee.account }">
						<div class="invalid-feedback" id="invalid-account">Can not
							be null</div>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="password"
							placeholder="Enter password" name="password"
							value="${employee.password }">
						<div class="invalid-feedback" id="invalid-password">Can not
							be null</div>
					</div>

					<button type="submit" class="btn btn-success btn-login">Submit</button>
				</form>
				<c:if test="${errorLogin}">
					<div class="text-danger">Incorrect Email or Password</div>
				</c:if>
			</div>
		</div>
	</div>


</body>
<script src="<%=request.getContextPath()%>/resources/js/myJavaScript.js"></script>
</html>
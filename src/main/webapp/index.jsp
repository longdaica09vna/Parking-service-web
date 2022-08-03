<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link href="resources/css/fontawesome-free/css/all.min.css" rel="stylesheet">
<link href="resources/css/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/bootstrap/js/bootstrap.min.js" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic"
	rel="stylesheet" type="text/css">
<link href="resources/css/css/myCSS.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">

</head>
<body class="index">
	<c:set var="IndexPage" value="true"></c:set>
	<%@include file="resources/views/_header.jsp"%>
	<div class="row">
		<div class="col-sm-2 border sidebar">
			<%@include file="resources/views/_sidebar.jsp"%>
		</div>

		<div class="col-sm-9">
			<c:if test="${errorSQL}">
				<h1 class="text-danger">Connect SQL Server fail</h1>
			</c:if>
			<c:if test="${LoggedIn != null}">
				<h1>Welcome ${LoggedIn.name }!</h1>
			</c:if>
		</div>
	</div>

</body>
</html>

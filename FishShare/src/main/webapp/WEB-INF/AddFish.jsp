<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Fish Share</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
				aria-controls="navbarNavAltMarkup" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse show" id="navbarNavAltMarkup">
				<div class="navbar-nav">
					<a class="nav-link active" aria-current="page" href="/dashboard">Home
						Page</a> <a class="nav-link" href="/my/fish">My Page</a> <a
						class="nav-link" href="/logout" tabindex="-1" aria-disabled="true">logout</a>
				</div>
			</div>
		</div>
	</nav>
		<h1 class="text-center">What Did You Catch?</h1>
		<form:form action="/fish/new" method="post" modelAttribute="newFish" class="new-fish">
			<div class="form-section">
				<form:label path="title" class="form-label">Name your post!</form:label>
				<form:errors path="title" class="text-danger" />
				<form:input path="title" class="form-control"/>
			</div>

			<div class="form-section">
				<form:label path="species" class="form-label">What type of fish did you catch?</form:label>
				<form:errors path="species" class="text-danger" />
				<form:input path="species" class="form-control"/>
			</div>
			<!-- backlog feature -->
			<!-- 		<div> -->
			<%-- 			<form:label path="lat">Lat</form:label> --%>
			<%-- 			<form:errors path="lat" class="text-danger" /> --%>
			<%-- 			<form:input path="lat" /> --%>
			<!-- 		</div> -->

			<!-- 		<div> -->
			<%-- 			<form:label path="lon">Lon</form:label> --%>
			<%-- 			<form:errors path="lon" class="text-danger" /> --%>
			<%-- 			<form:input path="lon" /> --%>
			<!-- 		</div> -->

			<div class="form-section">
				<form:label path="description" class="form-label">Tell us about it!</form:label>
				<form:errors path="description" class="text-danger" />
				<form:textarea rows="5" path="description"  class="form-control"/>

			</div>
			<td class=""><input type="submit" value="Submit" class="form-section btn btn-primary float-end"/></td>

		</form:form>
	</div>
</body>
</html>
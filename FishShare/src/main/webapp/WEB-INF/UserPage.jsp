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
						Page</a> <a class="nav-link" href="/fish/add">Add Fish</a> <a
						class="nav-link" href="/logout" tabindex="-1" aria-disabled="true">logout</a>
				</div>
			</div>
		</div>
	</nav>

	<div class="content-body">
		<h1 class="page-title text-center">My Fish</h1>
		<div class="d-flex justify-content-around">
			<div class="display-card card" id="dashboard-card">
				<div class="card-header text-center">Hi, ${user.userName }</div>
				<div
					class="card-body align-items-center d-flex justify-content-center">
					<h5 class="card-title">Catch fish and share with your friends!</h5>
					<img src="${ user.profilePic.url }" height="400" width="300"
						alt="dock" />
				</div>
				<div class="card-footer text-muted">
					<form action="/pictures/profile/add" method="post"
						enctype="multipart/form-data">
						<div class="form-data">
							Add a profile picture: <input type="file" name="url" id="" />
						</div>
						<input type="submit" value="Upload" class="btn btn-primary float-end"/>
					</form>
				</div>
			</div>
			<div class="feed-card card " id="dashboard-card">
				<div class="card-header text-center">My Fish</div>
				<div class="card-body overflow-auto">
					<c:forEach var="fish" items="${fish}">
						<p class="text-center card-header" id="post-header">${fish.creator.userName }'s catch!</p>
						<div class="card-content d-flex align-items-center">
							<p class="card-header">${fish.title}</p>
							 <a href="/fish/${ fish.id }"><img src="${ fish.pictures[0].url }" height="100" width="150"
								alt="" /></a>
							<div>
								<form:form action="/fish/edit/${ fish.id }" method="get">
									<input type="submit" value="Edit"  class="btn btn-dark"/>
								</form:form>
								<form:form action="/fish/delete/${ fish.id }" method="get">
									<input type="submit" value="Delete"  class="btn btn-danger"/>
								</form:form>
							</div>
						</div>

					</c:forEach>

				</div>
			</div>
		</div>
	</div>
</div>
<body>
</body>
</html>
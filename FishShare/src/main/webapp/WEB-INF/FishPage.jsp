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
							class="nav-link" href="/logout" tabindex="-1"
							aria-disabled="true">logout</a>
					</div>
				</div>
			</div>
		</nav>
		<h1 class="text-center">${ fish.title }</h1>
		<div class="d-flex flex-column align-items-center">
			<div class="d-flex">
				<c:forEach var="picture" items="${ fish.pictures }">
					<img src="${ picture.url }" height="250" width="250" alt="" />
				</c:forEach>
			</div>
			<p>${ fish.description }</p>

			<div class="card" id="comment-card">
				<div class="card-header text-center">Comments</div>
				<div
					class="card-body align-items-start d-flex overflow-auto"
					id="comments">

					<c:forEach var="comment" items="${ comments }">
						<div class="single-comment d-flex">
							<div class="d-flex align-items-center justify-content-start flex-column" id="user-card">
								<p class="comment-user">${ comment.commentUser.userName }</p>
								<img src="${ comment.commentUser.profilePic.url }" height="50"
									width="50" alt="" />
							</div>
							<p>${ comment.message }</p>
						</div>
					</c:forEach>

				</div>
				<div>
					<form:form action="/fish/${ fish.id }/comment" method="post"
						modelAttribute="newComment">

						<form:label path="message" class="form-label">Add a comment!</form:label>
						<form:errors path="message" class="text-danger" />
						<form:textarea path="message" class="form-control" />

						<input type="submit" value="Add Comment"
							class="btn btn-primary float-end" />

					</form:form>
				</div>
			</div>


			<c:if test="${ fish.creator.id == user.id }">
				<div class="card" id="dashboard-card">
					<div class="card-header text-center">Add a picture to your
						fish!</div>
					<form action="/pictures/create/${ fish.id }" method="post"
						enctype="multipart/form-data">
						<div
							class="card-body align-items-center d-flex justify-content-center">
							<div class="form-data">
								Select Picture: <input type="file" name="url" id="" />
							</div>
						</div>
						<div class="card-footer text-muted">
							<input type="submit" value="Upload"
								class="btn btn-primary float-end" />
						</div>
					</form>
				</div>
			</c:if>

		</div>
	</div>
</body>
</html>
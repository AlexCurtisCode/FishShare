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
<title>Fish Share Login</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container" id="login-background" style="background-image: linear-gradient(rgba(255,255,255,0.5), rgba(255,255,255,0.5)), url('/images/marlin.jpg');">
	
		<h1 class="text-center text-dark" id="login-head">Fish
			Share</h1>
		<div class="d-flex justify-content-end">
			<table class="login-table">
				<thead>
					<tr>
						<th class="text-center">Register</th>
					</tr>

				</thead>
				<tbody>
					<tr>
						<td><form:form action="/register" method="post"
								modelAttribute="newUser" class="submit-box">
								<div class="" id="register">
									<div class="form-group form-inline ">
										<form:label path="userName"
											class="col-5 offset-1 align-middle text-center">User Name </form:label>
										<form:errors path="userName" class="text-danger" />
										<form:input path="userName"
											class="col-5 align-middle text-center" />

									</div>
									<div class="form-group form-inline">
										<form:label path="email"
											class="col-5 offset-1 align-middle text-center">Email: </form:label>
										<form:errors path="email" class="text-danger" />
										<form:input path="email"
											class="col-5 align-middle text-center" />
									</div>
									<div class="form-group form-inline ">
										<form:label path="password"
											class="col-5 offset-1 align-middle text-center">Password: </form:label>
										<form:errors path="password" class="text-danger" />
										<form:input path="password" type="password"
											class="col-5 align-middle text-center" />
									</div>
									<div class="form-group form-inline ">
										<form:label path="confirm"
											class="col-5 offset-1 align-middle text-center">ConfirmPW: </form:label>
										<form:errors path="confirm" class="text-danger" />
										<form:input path="confirm" type="password"
											class="col-5 align-middle text-center" />
									</div>
								</div>
								<div class="submit">
									<input type="submit" value="Submit"
										class="btn btn-outline-secondary float-end" />
								</div>
							</form:form></td>
					</tr>
				<thead>
					<tr>
						<th class="text-center">Log in</th>
					</tr>
				</thead>
				<tr>
					<td><form:form action="/login" method="post"
							modelAttribute="newLogin" class="submit-box">
							<div id="login">
								<div class="form-group form-inline ">
									<form:label path="email"
										class="col-5 offset-1 align-middle text-center">Email: </form:label>
									<form:errors path="email" class="text-danger" />
									<form:input path="email" class="col-5 align-middle text-center" />
								</div>
								<div class="form-group form-inline">
									<form:label path="password"
										class="col-5 offset-1 align-middle text-center">Password: </form:label>
									<form:errors path="password" class="text-danger" />
									<form:input path="password" type="password"
										class="col-5 align-middle text-center" />
								</div>

							</div>
							<div class="submit">
								<input type="submit" value="Submit"
									class="btn btn-outline-secondary float-end" />
							</div>
						</form:form></td>
				</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
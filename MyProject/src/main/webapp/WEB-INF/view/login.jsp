<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>POS Home</title>

<script>
	window.menu = '$(title)';
	window.contextRoot = '${contextRoot}';
</script>

<!-- All the CSS goes here -->

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Theme goes here -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Data table Bootstrap CSS -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">


<!-- Custom styles for this template -->
<link href="${css}/myApp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" style="color: white"> <span
						style="font-weight: bold">ePOS</span>
					</a>
				</div>
			</div>
		</nav>

		<!-- Page Content -->
		<div class="content">
			<div class="container">

				<!-- Will be displayed if credentials are wrong -->
				<c:if test="${not empty message}">
					<div class="row">
						<div class="col-md-offset-3 col-md-6">

							<div class="alert alert-danger alert-dismissible">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
							${message}
							</div>
						</div>
					</div>
				</c:if>

				<!-- Will be displayed if user has logged out -->
				<c:if test="${not empty logout}">
					<div class="row">
						<div class="col-md-offset-3 col-md-6">

							<div class="alert alert-success alert-dismissible">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
							
								${logout}
							</div>
						</div>
					</div>
				</c:if>


				<div class="row">
					<div class="panel panel-primary">

						<div class="panel-heading">
							<h4 class="text-center">Login</h4>
						</div>

						<div class="panel-body">

							<div class="col-md-8">
									<c:forEach items="${users}" var="user">
										<button class="btn btn-success btn-lg" id="button">${user.firstName}</button>
									</c:forEach>
								
							</div>


							<div class="col-md-1 vertical-line"></div>
							<div class="col-md-3">

								<!-- Form Elements -->

								<form class="form-horizontal" action="${contextRoot}/login" method="POST" id="loginForm">
									
									<div class="form-group">
										<label class="control-label col-md-4" for="username">User Id:</label>
										<div class="col-md-8">
											<input type="text" name="username" id="username" class="form-control" />
										</div>
									</div>  

									<div class="form-group">
										<label class="control-label col-md-4" for="password">Password:</label>
										<div class="col-md-8">
											<input type="text" name="password" id="password" class="form-control" />
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-offset-4 col-md-8">
											<input type="submit" value="Login" class="btn btn-primary" />
											<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
										</div>
									</div>

								</form>


							</div>

						</div>

					</div>
				</div>


			</div>

		</div>

		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- jQuery -->
		<script src="${js}/jquery.js"></script>

		<!-- DataTable plugin -->
		<script src="${js}/jquery.dataTables.js"></script>


		<!-- Bootstrap core JavaScript -->
		<script src="${js}/bootstrap.min.js"></script>

		<!-- DataTable Bootstrap script-->
		<script src="${js}/dataTables.bootstrap.js"></script>

		<!-- Self coded javascript -->
		<script src="${js}/myapp.js"></script>
	</div>

</body>

</html>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="css/login.css" rel="stylesheet">
</head>

<body>

	<div class="wrapper">
		<c:if test="${not empty error}">
			<div class="error">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="msg">${msg}</div>
		</c:if>
	
		<form action="login" method="post" class="form-signin">
			<h2 class="form-signin-heading">Please login</h2>
			<input type="text" class="form-control" name="username"
				placeholder="Username" required="" autofocus="" /> <input
				type="password" class="form-control" name="password"
				placeholder="Password" required="" />
				</br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
			<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
			
		</form>
	</div>

</body>
</html>
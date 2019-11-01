<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>
</head>
<body>

	<section>
		<div>
			<h1>Prisijungimo langas!</h1>
			<p>Kas jūs toks?</p>
		</div>
	</section>

	<div>
		<h3>Prašome prisijungti:</h3>
	</div>

	<div>
		<c:url var="loginUrl" value="/login" />
		<form action="${loginUrl}" method="post">

			<c:if test="${param.error != null}">
				<div>
					<p>Invalid username and password.</p>
				</div>
			</c:if>

			<c:if test="${param.logout != null}">
				<div>
					<p>You have been logged out successfully.</p>
				</div>
			</c:if>

			<c:if test="${param.accessDenied != null}">
				<div>
					<p>Access Denied: You are not authorised!</p>
				</div>
			</c:if>

			<!-- Vartotojo vardo laukelis -->
			<div>				
				<input type="text" name="userId" placeholder="Enter Username" required>
			</div>

			<!-- Slaptažodžio laukelis -->
			<div>				
				<input type="password" name="password" placeholder="Enter Password" required>
			</div>

			<div>
				<input type="submit" value="Log in">
			</div>

		</form>
	</div>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- naudojame bootstrap stiliams -->
<link href="/eshop/bootstrap-4.3.1-dist/css/bootstrap.min.css" rel="stylesheet">
<title>Čia rodomi produktai</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Produktai</h1>
				<p>Tai ką mes turime sandėlyje:</p>
			</div>
		</div>
	</section>

	<section class="container">
		<div class="row">

			<c:forEach items="${produktai}" var="produktas">
			
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail">
						<h3>${produktas.name}</h3>
						<p>${produktas.description}</p>
						<p>${produktas.unitPrice}EUR</p>
						<p>Kiekis sandėlyje: ${produktas.unitsInStock}</p>
						<p>
							<a href=" <spring:url value="/market/product?id=${produktas.productId}" /> " class="btn btn-primary"><span class="glyphicon-info-sign glyphicon" /></span>Details</a>
						</p>
					</div>
				</div>
				
			</c:forEach>
			
		</div>
	</section>

</body>
</html>
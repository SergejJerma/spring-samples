<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- naudojame bootstrap stiliams -->
<link href="/eshop/bootstrap-4.3.1-dist/css/bootstrap.min.css" rel="stylesheet">
<title>Vienas produktas</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Product information</h1>
			</div>
		</div>
	</section>

	<section class="container">
		<div class="row">
			<div class="col-md-5">


				<h3>${product.name}</h3>

				<p>${product.description}</p>
				<p>
					<strong>Item Code: </strong> ${product.productId}
				</p>
				<p>
					<strong>Manufacturer: </strong> ${product.manufacturer}
				</p>
				<p>
					<strong>Category: </strong> ${product.category}
				</p>
				<p>
					<strong>Available units in stock: </strong> ${product.unitsInStock}
				</p>
				<h4>${product.unitPrice}EUR</h4>

				<a href="<spring:url value="/market/products" />" class="btn btn-primary"> <span class="glyphicon-hand-left glyphicon"></span> Back
				</a>

			</div>
		</div>
	</section>

</body>
</html>
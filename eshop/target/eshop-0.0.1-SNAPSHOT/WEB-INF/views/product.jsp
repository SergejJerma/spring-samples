<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vienas produktas</title>
</head>
<body>
	<h1>Product information</h1>


	<img src="<c:url value="/img/${product.productId}.png"></c:url>" alt="image" style="width: 30%" />

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

	<a href="<spring:url value="/market/products" />"> Back </a>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Čia rodomi produktai</title>
</head>
<body>

	<h1>Produktai</h1>
	<p>Tai ką mes turime sandėlyje:</p>

	<!-- Iš kontrolerio paimtas objektas, pasiekia laukus kreipiantis į juos tiesiogiai
	su sąlyga, kad seteriai ir geteriai yra standartiniai -->
	<%-- 	<h3>${produktas.name}</h3>
	<p>${produktas.description}</p>
	<p>${produktas.unitPrice}EUR</p>
	<p>Kiekis sandėlyje: ${produktas.unitsInStock}</p> --%>

	<c:forEach items="${produktai}" var="produktas">

		<h3>${produktas.name}</h3>
		<p>${produktas.description}</p>
		<p>${produktas.unitPrice}EUR</p>
		<p>Kiekis sandėlyje: ${produktas.unitsInStock}</p>
		<div>
			<img src="<c:url value="/img/${produktas.productId}.png"></c:url>" alt="image" style="width: 30%" />
		</div>

		<a href=" <spring:url value="/market/product?id=${produktas.productId}" /> ">Details</a>

	</c:forEach>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- naudojame bootstrap stiliams -->
<link href="/eshop/bootstrap-4.3.1-dist/css/bootstrap.min.css" rel="stylesheet">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"> -->
<title>Sveikiname mūsų parduotuvėje!</title>
</head>
<body>
	<!-- $ ir {} - nurodo, kad tai yra kintamasis
	Šie kintamieji bus rodomi puslapio atidarymo metu -->
	<div class="jumbotron">
		<h1>${pasisveikinimas}</h1>
		<p>${sloganas}</p>
	</div>
	
</body>
</html>
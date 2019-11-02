<html>
<head>
<title>Text Input layout</title>
<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div>Input text here:</div>

		<form action="/input" method="POST">
			<textarea name="text" rows="10" cols="50"></textarea>
			<input type="submit" class="btn btn-success" value="Submit" />
		</form>

	</div>
	<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>


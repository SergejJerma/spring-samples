<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Text Input layout</title>
<link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div>Output result</div>
		<textarea rows="20" cols="50">
<c:forEach items="${text.words}" var="todo">${todo.key} ${todo.value.size()} <c:forEach
					items="${todo.value}" var="word">${word} </c:forEach>
</c:forEach>
  </textarea>

		<div>
			<a type="button" class="btn btn-success" href="/input">Back to
				input</a>
		</div>
	</div>
	<script src="webjars/jquery/3.4.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
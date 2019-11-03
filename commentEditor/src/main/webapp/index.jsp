<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<?xml version="1.0" encoding="UTF-8"?>
    <html>
        <head>
            <title>GuestBook</title>
  <link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
        </head>
        <body>
        
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Navbar</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/login">Login <span class="sr-only">(current)</span></a>
      </li>
           <li class="nav-item active">
        <a class="nav-link" href="/">Reviews <span class="sr-only">(current)</span></a>
      </li>
    </ul>
  </div>
</nav>
        <div class="container">
            <h1>Post new review</h1>
		<form:form method="post" modelAttribute = "rv">
				<fieldset class="form-group">

				<form:label path="reviewText"></form:label>
				<form:input path="reviewText" type="text" class="form-control"
					required="required"/>
				<form:errors path="reviewText" cssClass="text-warning"/>

			</fieldset>
			<button type="submit" class="btn btn-success">Submit</button>
		</form:form>
            <hr/>
            <h1>Reviews</h1>
            <c:forEach items="${reviews}" var="review">
                <div>
                    <c:out value="${review.reviewText}"/><br/>
                    <small><fmt:formatDate pattern="dd/MM/yyyy hh:mm"
									value="${review.createDate}" /></small>
                    
                    <a type="button" class="btn btn-primary" 
								href="/update?id=${review.id}">Edit</a>
                    <a type="button" class="btn btn-danger" 
								href="/delete?id=${review.id}">Delete</a>
                </div>
                <br/>
            </c:forEach>
            </div>
    <script src="webjars/jquery/3.4.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        </body>
    </html>

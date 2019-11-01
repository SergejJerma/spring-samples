<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add product page</title>
</head>
<body>

	<section>
		<div>
			<a href="?language=en">English</a> | <a href="?language=lv">Latviešu</a> | <a href="?language=lt">Lietuvių</a> <a href="<c:url value="/logout" />">Logout</a>
		</div>
	</section>

	<section>
		<h1>Products</h1>
		<p>Add products</p>
	</section>


	<section>
		<form:form method="POST" modelAttribute="newProduct" enctype="multipart/form-data">
		<!-- Visos klaidos surenkamos iš žemiau ir rodomos šioje vietoje -->
		<form:errors path="*" element="div"/>
			<fieldset>
				<legend>Add new product:</legend>

				<div>
					<label><spring:message code="addProduct.form.productId.label" /></label>
					<form:input path="productId" type="text" />
					<form:errors path="productId" />
				</div>

				<div>
					<label><spring:message code="addProduct.form.productName.label" /></label>
					<form:input path="name" type="text" />
					<form:errors path="name" />
				</div>

				<div>
					<label><spring:message code="addProduct.form.productPrice.label" /></label>
					<form:input path="unitPrice" type="number" />
					<form:errors path="unitPrice" />
				</div>

				<div>
					<label><spring:message code="addProduct.form.productDescription.label" /></label>
					<form:textarea path="description" rows="2" />
				</div>
				
				<div>
					<label><spring:message code="addProduct.form.unitsInStock.label" /></label>
					<form:input path="unitsInStock" type="number" />
				</div>

				<div>
					<label><spring:message code="addProduct.form.productCondition.label" /></label>
					<div>
						<form:radiobutton path="condition" value="New" />
						New
						<form:radiobutton path="condition" value="Old" />
						Old
						<form:radiobutton path="condition" value="Refurbished" />
						Refurbished
					</div>
				</div>


				<div>
					<label><spring:message code="addProduct.form.productImage.label" /></label>
					<form:input path="productImage" type="file" />
				</div>


				<input type="submit" value="Add new product" />


			</fieldset>

		</form:form>
	</section>

</body>
</html>
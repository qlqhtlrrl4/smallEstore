<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container-wrapper">
	<div class="container">
		<h1>Update Product</h1>
		<p class="lead">Fill the below information to add a product:</p>

		<!-- modelAttribute의 값과 AdminController의 model.addAttribute("product", .. ) product 값(key)과 일치-->
		<sf:form
			action="${pageContext.request.contextPath }/admin/productInventory/updateProduct"
			method="post" modelAttribute="product">
			
			<sf:hidden path="id"/>
			
			<div class="form-group">
				<label for="name"><spring:message code="product.name" /></label>
				<sf:input path="name" id="name" class="form-control" />
				<sf:errors path="name" cssStyle="color:#ff0000;" />
			</div>

			<div class="form-group">
				<label for="category"><spring:message code="category" /> </label>
				<sf:radiobutton path="category" id="category" value="컴퓨터" />
				<spring:message code="product.category.computer" />
				<sf:radiobutton path="category" id="category" value="가전" />
				<spring:message code="product.category.homeappliances" />
				<sf:radiobutton path="category" id="category" value="잡화" />
				<spring:message code="product.category.stuff" />
			</div>

			<div class="form-group">
				<label for="description"><spring:message code="product.description" /></label>
				<sf:textarea path="description" id="description"
					class="form-control" />
			</div>

			<div class="form-group">
				<label for="price"><spring:message code="product.price" /></label>
				<sf:input path="price" id="price" class="form-control" />
				<sf:errors path="price" cssStyle="color:#ff0000;" />
			</div>

			<div class="form-group">
				<label for="unitInStock"><spring:message code="product.unitInstock" /></label>
				<sf:input path="unitInStock" id="unitInStock" class="form-control" />
				<sf:errors path="unitInStock" cssStyle="color:#ff0000;" />
			</div>

			<div class="form-group">
				<label for="manufacturer"><spring:message code="product.manufacture" /></label>
				<sf:input path="manufacture" id="manufacture" class="form-control" />
				<sf:errors path="manufacture" cssStyle="color:#ff0000;" />
			</div>

			<input type="submit" value="<spring:message code='static.submit'/>" class="btn btn-default">
			<a href="<c:url value="/admin/productInventory" />"
				class="btn btn-default"><spring:message code="static.cancel" /></a>
		</sf:form>
		<br />
	</div>
</div>
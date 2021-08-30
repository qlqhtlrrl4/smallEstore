<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	

<div class="container-wrapper">
	<div class="container">
		<h2><spring:message code="product.name" /></h2>
		<p><spring:message code="productInventory.Info" /></p>
		
		
		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th><spring:message code="product.name" /></th>
					<th><spring:message code="product.category" /></th>
					<th><spring:message code="product.price" /></th>
					<th><spring:message code="product.manufacture" /></th>
					<th><spring:message code="product.unitInstock" /></th>
					<th><spring:message code="product.description" /></th>
					<th> </th>

				</tr>
			</thead>
			<tbody>
			
				<c:forEach var="product" items="${products}">
					<tr>
						<td>${product.name}</td>
						<td>${product.category}</td>
						<td>${product.price}</td>
						<td>${product.manufacture }</td>
						<td>${product.unitInStock }</td>
						<td>${product.description }</td>
						<td>
							<a href="<c:url value="/admin/productInventory/deleteProduct/${product.id}"/>"><i class="fa fa-times"></i></a>
							<a href="<c:url value="/admin/productInventory/updateProduct/${product.id}"/>"><i class="fa fa-edit"></i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a class="btn btn-primary" href="<c:url value="productInventory/addProduct"/>">Add Product</a>
	</div>
</div>

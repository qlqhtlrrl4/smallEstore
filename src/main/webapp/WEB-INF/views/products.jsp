<%@ page import= "java.util.*" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<div class="container-wrapper">
	<div class="container">
		<h2><spring:message code="product.title" /></h2>
		<p>${param.lang}</p>
		
		<table class="table table-striped">
			<thead>
				<tr class="bg-success">
					<th><spring:message code="product.name" /></th>
					<th><spring:message code="product.category" /></th>
					<th><spring:message code="product.price" /></th>
					<th><spring:message code="product.manufacture" /></th>
					<th><spring:message code="product.unitInstock" /></th>
					<th><spring:message code="product.description" /></th>

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
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
	
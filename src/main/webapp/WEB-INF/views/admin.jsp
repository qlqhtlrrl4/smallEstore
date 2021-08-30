<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container-wrapper">

	<div class="container">
		<h2><spring:message code="admin.page" /></h2>
		<p class="lead"><spring:message code="admin.index" /></p>

	</div>
	
	<div class="container">
		<h2><a href="<c:url value="/admin/productInventory"/>"><spring:message code="admin.inventory" /></a></h2>
		<p class="lead"><spring:message code="admin.inventoryInfo" /></p>

	</div>
	
</div>


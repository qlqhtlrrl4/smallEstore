<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<header>
	<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
		<a class="navbar-brand" href="#">Carousel</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarCollapse" aria-controls="navbarCollapse"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="<c:url value="/"/>"> Home <span class="sr-only">(current)</span></a>
				</li>

				<li class="nav-item"><a class="nav-link"
					href="<c:url value = "/products"/>"><spring:message code="menu.product" /></a></li>
				<li class="nav-item"><a class="nav-link"
					href="<c:url value = "/admin"/>"><spring:message code="menu.admin" /></a></li>
				
				<c:if test="${pageContext.request.userPrincipal.name==null}">
				
				<li class="nav-item"><a class="nav-link"
					href="<c:url value = "/login"/>"><spring:message code="menu.login" /></a></li>
				</c:if>
				<c:if test="${pageContext.request.userPrincipal.name!=null}">
					<li class="nav-item"><a class="nav-link"
						href="<c:url value="/logout"/>"><spring:message code="menu.logout" /></a></li>
				</c:if>

			</ul>

			<form class="form-inline mt-2 mt-md-0">
				<div class="dropdown">
					<button class="btn btn-danger dropdown-toggle" type="button"
						id="dropdownMenuButton" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">
						<spring:message code="app.lang.title" />
					</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">


						<a class="dropdown-item" href="?lang=en"><spring:message
								code="app.lang.english" /></a> <a class="dropdown-item"
							href="?lang=ko"><spring:message code="app.lang.korea" /></a>


					</div>
				</div>
			</form>
		</div>
	</nav>
</header>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container-wrapper">
	<div class="container">
		<h1>Add Product</h1>
		<p class="lead">Fill the below information to add a product:</p>

		<!-- modelAttribute의 값과 AdminController의 model.addAttribute("product", .. ) product 값(key)과 일치-->
		<sf:form
			action="${pageContext.request.contextPath }/join"
			method="post" modelAttribute="userInfomation">

			<div class="form-group">
				<label for="id">id:</label>
				<sf:input path="id" id="id" class="form-control" />
				<sf:errors path="id" cssStyle="color:#ff0000;" />
			</div>


			<%-- <div class="form-group">
				<label for="enabled"></label>
				<sf:hidden path="enabled" id="enabled"
					class="form-control" />
			</div> --%>

			<div class="form-group">
				<label for="password">password</label>
				<sf:password path="password" id="password" class="form-control" />
				<sf:errors path="password" cssStyle="color:#ff0000;" />
			</div>

			<div class="form-group">
				<label for="email">Email</label>
				<sf:input path="email" id="email" class="form-control" />
				<sf:errors path="email" cssStyle="color:#ff0000;" />
			</div>
			
			<div class="form-group">
				<label for="name">Name</label>
				<sf:input path="name" id="name" class="form-control" />
				<sf:errors path="name" cssStyle="color:#ff0000;" />
			</div>

			<%-- <div class="form-group">
				<label for="userRole"></label>
				<sf:hidden path="userRole" id="userRole" class="form-control" />
				
			</div> --%>

			<input type="submit" value="submit" class="btn btn-default">
			<a href="<c:url value="/login" />"
				class="btn btn-default">Cancel</a>
		</sf:form>
		<br />
	</div>
</div>
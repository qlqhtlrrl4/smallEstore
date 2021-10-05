<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<header>
	
	<nav class="navbar navbar-expand-md bg-light navbar-light fixed-top">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<a class="navbar-brand" href="/">Navbar</a>

		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav mr-auto mt-1 mt-md-0">
				<li class="nav-item"><a class="nav-link" href="#"><small>메인</small></a>
				</li>

				<li class="nav-item"><a class="nav-link" href="#"><small>상세 데이터</small>
						</a></li>

				<li class="nav-item"><a class="nav-link" href="#"><small>지역별 현황</small></a>
				</li>

				<li class="nav-item"><a class="nav-link" href="#"><small>통계조회</small></a>
				</li>
			</ul>

			<ul class="navbar-nav ml-auto mt-1 mt-md-0">

				<li class="nav-link">site name</li>

				<c:if test="${pageContext.request.userPrincipal.name == null}">
					<li class="nav-item active"><a class="nav-link" href="/login"><small>로그인</small></a>
					</li>

					<li class="nav-item active"><a class="nav-link" href="/join"><small>회원가입</small></a>
					</li>
				</c:if>

				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<li class="nav-item active"><a class="nav-link" href="/logout"><small>로그아웃</small></a>
					</li>

					<li class="nav-item active"><a class="nav-link" href="#"><small>회원정보
							변경</small></a></li>
				</c:if>
			</ul>


		</div>

	</nav>



</header>
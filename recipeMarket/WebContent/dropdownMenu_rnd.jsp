<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!-- 드롭다운 메뉴 -->
<div class="dropdown">
	<c:choose>
		<c:when test="${empty sessionScope.loginInfo}">
			<!-- 로그인 버튼(누르면 드롭다운 메뉴 보이도록) -->
			<h1 class="account">로그인</h1>
			<!-- 드롭다운 메뉴 구성 (동적 생성 필요) -->
			<div class="dropdown-content">
				<a href="${contextPath}/static/login_rnd.html">로그인</a>
			</div>
		</c:when>
		<c:otherwise>
			<!-- 로그인 버튼(누르면 드롭다운 메뉴 보이도록) -->
			<h1 class="account">${sessionScope.userName}님</h1>
			<!-- 드롭다운 메뉴 구성 (동적 생성 필요) -->
			<div class="dropdown-content">
				<a href="${contextPath}/rnd/info?rd_email=${loginInfo}">내 정보 보기</a>
				<a href="${contextPath}/myRecipeList">내 레시피 목록</a>
				<a href="${contextPath}/rnd/search">통계</a>
				<a href="${contextPath}/logout/rnd">로그아웃</a>
			</div>
		</c:otherwise>
	</c:choose>
</div>
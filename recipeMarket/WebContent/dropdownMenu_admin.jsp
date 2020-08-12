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
				<a href="${contextPath}/static/login_admin.html">로그인</a>
			</div>
		</c:when>
		<c:otherwise>
			<!-- 로그인 버튼(누르면 드롭다운 메뉴 보이도록) -->
			<h1 class="account">${sessionScope.userName}님</h1>
			<!-- 드롭다운 메뉴 구성 (동적 생성 필요) -->
			<div class="dropdown-content">
				<a href="${contextPath}/rnd/list?currentPage=">R&D 계정 관리</a>
				<a href="${contextPath}/statistics/graph2?year=2020&count=10">통계</a>
				<a href="${contextPath}/logout/admin">로그아웃</a>
			</div>
		</c:otherwise>
	</c:choose>
</div>
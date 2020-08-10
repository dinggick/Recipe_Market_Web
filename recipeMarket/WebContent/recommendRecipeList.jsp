<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="list" value="${requestScope.recommendRecipeList}"/>

<%-- 한줄에 5개씩 열개를 보여준다 --%>
<section class="bestRecipeSection">
	<c:forEach begin="0" end="4" var="i">
		<div class="card">
			<img src="${list[i].imgUrl}">
			<div class="cardContainer">
				<h4><b>${list[i].recipeName}</b></h4>
				<p>${list[i].recipeSumm}</p>
				<img src="${contextPath}/img/heart.png" class="favorite"> 
				<img src="${contextPath}/img/dislike.png" class="dislike">
				<img src="${contextPath}/img/like.png" class="like">
				
				<input type="hidden" value="${list[i].recipeCode}">
			</div>
		</div>
	</c:forEach>
</section>
<section class="bestRecipeSection">
	<c:forEach begin="5" end="9" var="i">
		<div class="card">
			<img src="${list[i].imgUrl}">
			<div class="cardContainer">
				<h4><b>${list[i].recipeName}</b></h4>
				<p>${list[i].recipeSumm}</p>
				<img src="${contextPath}/img/heart.png" class="favorite"> 
				<img src="${contextPath}/img/dislike.png" class="dislike">
				<img src="${contextPath}/img/like.png" class="like">
				
				<input type="hidden" value="${list[i].recipeCode}">
			</div>
		</div>
	</c:forEach>
</section>
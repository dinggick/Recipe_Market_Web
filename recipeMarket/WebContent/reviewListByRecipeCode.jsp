<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!-- recipeInfo ReviewList 영역 -->
<div class="reviewSection">
	<h1>후기</h1>
	<ul>
	<c:if test="${empty requestScope.reviewListByRecipeCode}" > 
		<li>
			<fieldset>
				<span class="reviewContent">등록된 후기가 없습니다.</span>
			</fieldset>
		</li>
	</c:if>
	
	<c:forEach items="${requestScope.reviewListByRecipeCode}" var="review" varStatus="status">
		<li>
			<%-- 후기목록반복--%>
			<fieldset>
				<legend>${review.purchase.customerEmail.customerName}</legend>
				<span class="reviewContent">${review.reviewComment}</span>
			</fieldset>
		</li>
	</c:forEach>
	</ul>
</div>


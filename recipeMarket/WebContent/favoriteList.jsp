<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	page import="com.recipe.model.PageBean"%>
<%@	page import="com.recipe.vo.Favorite"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<c:set var="pb" value="${requestScope.pb}"/>
<c:set var="currentPage" value="${pb.currentPage}"/>
<c:set var="list" value="${pb.list}"/>
<c:set var="CNT_PER_PAGE" value="${PageBean.CNT_PER_PAGE}"/>
<c:set var="CNT_PER_PAGEGROUP" value="${PageBean.CNT_PER_PAGEGROUP}"/>
<c:set var="startRow" value="${pb.startRow}"/>
<c:set var="totalPage" value="${pb.totalPage}"/>
<c:set var="startPage" value="${pb.startPage}"/>
<c:set var="endPage" value="${pb.endPage}"/>
<c:set var="rowCnt" value="${pb.rowCnt}"/>
<c:set var="url" value="${pb.url}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Recipe Market - 오늘 뭐 먹지?</title>
<!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">
<link rel="icon" href="${contextPath}/img/titlecon.png">
<link rel="stylesheet" href="${contextPath}/css/header.css">
<link rel="stylesheet" href="${contextPath}/css/divContent.css">
<link rel="stylesheet" href="${contextPath}/css/footer.css">
<link rel="stylesheet" href="${contextPath}/css/favorite.css">

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- javaScript -->
<script src="${contextPath}/js/dropdownMenu.js"></script>
<script src="${contextPath}/js/favoriteBtn.js"></script>
<script src="${contextPath}/js/header.js"></script>
<script src="${contextPath}/js/footer.js"></script>


<script>
$(function()  {
	
	var $rightSectionObj = $("div.divContent>section.rightSection");
	var $recipeInfoObj = $rightSectionObj.find("div.recipeInfo");
	var $faovoriteListObj = $recipeInfoObj.find("table.favoriteList");
	
	// 즐겨찾기 페이징 처리

	
	// 즐겨찾기에서 상세 정보 or삭제이벤트
	$faovoriteListObj.on('click', 'tr', function(e){
		var recipeCode = $(this).find('input[name=recipeCode]').val();
			
			if ( e.target.className == 'delete' ) {
				$.ajax({
					url : '${contextPath}/favorite/remove' 
					, data : {"recipeCode" : recipeCode}
					, success : function(data) {
						if ( data.status == 'success') {
							location.reload();
						} else {
							alert("삭제에 실패했습니다.");
						}
						
					} //end of success
				}); //end of Ajax
				return false;
			} else if ( e.target.className == 'cartAdd' ) {
		    	$.ajax({
		    		url : "/recipeMarket/cartAdd",
		    		data : { recipeCode : recipeCode, quantity : 1 },
		    		success : (data, textStatus, jqXHR) => {
		    			if(data.status == "success") {
		    				alert("장바구니에 추가되었습니다.");
		    			} else {
		    				alert("장바구니 추가 실패 : " + msg);
		    			}
		    		}
		    	});// end of ajax
		    	return false;
			};
			
			
			
			var recipeCodeForm = $("div.divContent>section.rightSection>form");
			var recipeCodeObj = $('input[id=recipeCode]');
			recipeCodeObj.attr("value", recipeCode);
			
	    	var form = document.createElement("form");
	    	form.setAttribute("method", "POST");
	    	form.setAttribute("action", "/recipeMarket/recipeInfo");
	    	
	    	var input = document.createElement("input");
	    	input.setAttribute("type", "hidden");
	    	input.setAttribute("name", "recipeCode");
	    	input.setAttribute("value", recipeCode);
	    	form.appendChild(input);
	    	document.body.appendChild(form);
	    	form.submit();
	    	return false;
		});
	
	
}); // end of load
</script>
</head>

<body>
	<header id="header">
		<!-- 왼쪽 영역 -->
		<div class="headerLeftSection">
			<!-- 로고(홈 버튼) -->
			<h1 class="home">RECIPE MARKET</h1>
		</div>
		<!-- 오른쪽 영역 -->
		<div class="headerRightSection">
			<jsp:include page="/dropdownMenu.jsp"></jsp:include>
		</div>
	</header>
    <div class="divContent">
        <!-- 왼쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="leftSection">
            <!-- 화면 제목(또는 레시피 이름) -->
            <h1>나의 즐겨찾기</h1>
            <hr><br>
            <p> ${rowCnt} 건이 조회 되었습니다.<p>
        </section>
        <!-- 오른쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="rightSection">
	        <div class="recipeInfo">
		        <table class="favoriteList">
	        		<colgroup>
		        		<col width="10%"></col>
		        		<col width="20%"></col>
		        		<col width="20%"></col>
		        		<col width="*"></col>
		        		<col width="5%"></col>
		        		<col width="5%"></col>
		        	</colgroup>
		        	<thead>
		        	<tr>
			        	<td>NO</td>
			        	<td>사진</td>
			        	<td>상품명</td>
			        	<td>내용</td>
			        	<td></td>
		        	</thead>
		        	<tbody>
						<c:if test="${empty list}" > 
                	   		<tr> 
                	   			<td colspan='5'> 등록된 즐겨찾기 목록이 없습니다.</td>
							</tr>
						
						</c:if>
                	   <c:forEach items="${list}" var="favorite" varStatus="status">
                	   		<tr> 
                	   			<td> ${status.index+1} </td>
                	   			<td><img class="recipeImg" src="${favorite.recipeInfo.imgUrl}"></td>
                	   			<td> ${favorite.recipeInfo.recipeName} </td>
                	   			<td><span> ${favorite.recipeInfo.recipeSumm}</span> 
                	   				<input type="hidden" name="recipeCode" value="${favorite.recipeInfo.recipeCode}"/>
                	   			</td>
			        			<td><a><img src="${contextPath}/img/shopping-cart.png" class="cartAdd"/></a></td>
                	   			<td class="delete"><a><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a>
			        			</td>
							</tr>
                	   </c:forEach>
		        	</tbody>
		        </table>
		        
            <!-- 페이징 영역 Start-->
<%-- 			<div class="pagingSection">
				<c:if test="${startPage > 1}">
					<img src="${contextPath}/img/prev2.png" alt="prev2">
				</c:if>
				
				<c:if test="${currentPage > 1}">
					<img src="${contextPath}/img/prev1.png" alt="prev1">
				</c:if>
				
				<c:forEach begin="${startPage}" end="${endPage}" var="i">
				
					<c:choose>
						<c:when test="${currentPage == i}">
							<a href="${contextPath}/favorite/favoriteList?currentPage=${i}" style="color: #D2302C; font-weight: border"> ${i}</a>				
						</c:when>
						<c:otherwise>
							<a href="${contextPath}/favorite/favoriteList?currentPage=${i}">${i}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:if test="${totalPage > endPage}">
					<img src="${contextPath}/img/next1.png" alt="next1">
				</c:if>
				
				<c:if test="${endPage < totalPage}">
					<img src="${contextPath}/img/next2.png" alt="next2">
				</c:if>
			</div>
            <!-- 페이징 영역 End --> --%>

	        </div>
	        
	        
			<div class="pagingSection">
			<c:if test="${startPage > 1}">
			<img src="${contextPath}/img/prev2.png" alt="prev2">
			</c:if>
			<c:if test="${currentPage > 1}">
				<img src="${contextPath}/img/prev1.png" alt="prev1">
			</c:if>
			<c:forEach begin="${startPage}" end="${endPage}" var="i">
			<c:choose>
			<c:when test="${currentPage == i}">
				<a href="${contextPath}/favorite/favoriteList?currentPage=${i}" style=" color: #D2302C; font-weight: border"> ${i} </a>				
			</c:when>
			<c:otherwise>
				<a href="${contextPath}/favorite/favoriteList?currentPage=${i}">${i}</a>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			
			<c:if test="${totalPage > endPage}">
				<img src="${contextPath}/img/next1.png" alt="next1">
			</c:if>
			
			<c:if test="${endPage < totalPage}">
				<img src="${contextPath}/img/next2.png" alt="next2">
			</c:if>
				
			</div>
        </section>
    </div>
    
    <!-- footer 영역 -->
	<div class="footer">
		<jsp:include page="static/footer.html"></jsp:include>
	</div>
</body>
</html>
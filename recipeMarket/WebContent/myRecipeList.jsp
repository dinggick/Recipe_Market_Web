<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<c:set var="myReviewList" value="${requestScope.myReviewList}"></c:set>

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
<link rel="stylesheet" href="${contextPath}/css/myRecipeList.css">

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- javaScript -->
<script src="${contextPath}/js/dropdownMenu.js"></script>
<script src="${contextPath}/js/favoriteBtn.js"></script>
<%-- <script src="${contextPath}/js/header.js"></script> --%>
<script src="${contextPath}/js/header_rnd.js"></script>
<script src="${contextPath}/js/footer.js"></script>
<script>
var orderType = "${orderType}";
var pageNo = "${pageNo}";

//페이지 이동
function goPage(pNo) {
	if (pageNo != pNo)
		location.href = "${contextPath}/myRecipeList?pageNo=" + pNo + "&orderType=" + orderType;
}

$(function()  {
	var $rightSectionObj = $("div.divContent>section.rightSection");
	var $recipeInfoObj = $rightSectionObj.find("div.recipeInfo");
	var $myReviewListObj = $recipeInfoObj.find("table.myReviewList");
	
	// 후기 목록 중 <td>클릭시 레시피상세정보 보기
	$myReviewListObj.on('click', 'tr', function(e){
		var recipe_code = $(this).attr('data-recipe-code');
		console.log(recipe_code);
	});

	// 처음으로(1페이지로)
	$("img[alt=prev2]").click(function() {
		goPage(1);
	});
	// 이전그룹(이전그룹의 마지막번호 or 이전그룹이 없다면 )
	$("img[alt=prev1]").click(function() {
		goPage("${prevGroupLastNo}");
	});
	// 다음그룹
	$("img[alt=next1]").click(function() {
		goPage("${nextGroupStartNo}");
	});
	// 마지막으로
	$("img[alt=next2]").click(function() {
		goPage("${totalPage}");
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
			<jsp:include page="/dropdownMenu_rnd.jsp"></jsp:include>		
<!--             드롭다운 메뉴 -->
<!--             <div class="dropdown"> -->
<!--                 로그인 버튼(누르면 드롭다운 메뉴 보이도록) -->
<!--                 <h1 class="account">SIGN IN</h1> -->
<!--                 드롭다운 메뉴 구성 (동적 생성 필요) -->
<!--                 <div class="dropdown-content"> -->
<!--                     <a href="login.html">로그인</a> -->
<!--                     <a href="#">Menu 2</a> -->
<!--                     <a href="#">Menu 3</a> -->
<!--                 </div> -->
<!--             </div> -->
        </div>
    </header>
    <div class="divContent">
        <!-- 왼쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="leftSection">
            <!-- 화면 제목(또는 레시피 이름) -->
            <h1>내레시피보기</h1>
            <hr><br>
            <p><fmt:formatNumber value="${myRecipeCount}" pattern="#,###" /> 건이 조회 되었습니다.<p>
        </section>
        <!-- 오른쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="rightSection">
	        <div class="recipeInfo">
		        <table class="myReviewList">
		        	<colgroup>
		        		<col width="3%"></col>
		        		<col width="15%"></col>
		        		<col width="15%"></col>
		        		<col width="*"></col>
		        	</colgroup>
		        	<thead>
		        	<tr>
			        	<td>NO</td>
			        	<td>레시피명</td>
			        	<td>가격</td>
			        	<td>판매량 &nbsp<a href="/recipeMarket/myRecipeList?pageNo=${pageNo}&orderType=P" style="text-decoration: none;"> <img src="${contextPath}/img/sort.png" height="17px" alt="sort1"></a></td>
			        	<td>총매출액 &nbsp<a href="/recipeMarket/myRecipeList?pageNo=${pageNo}&orderType=T" style="text-decoration: none;"> <img src="${contextPath}/img/sort.png" height="17px" alt="sort2"></a></td>
		        	</thead>
		        	<tbody>
		        		<c:if test="${empty myRecipeListPage}">
		        		<tr>
		        			<td colspan="5">등록된 레시피가 없습니다.</td>
		        		</tr>
		        		</c:if>
		        		<c:if test="${!empty myRecipeListPage}">
		        			<c:forEach var="myRecipeInfo" items="${myRecipeListPage}" varStatus="status">
		        				<tr data-recipe-code="${myRecipeInfo.recipeCode}">
					        		<td>${myRecipeInfo.num}</td>
					        		<td>${myRecipeInfo.recipeName}</td>
					        		<td><fmt:formatNumber value="${myRecipeInfo.recipePrice}" pattern="#,###" />원</td>
					        		<td><fmt:formatNumber value="${myRecipeInfo.purchaseQuantity}" pattern="#,###" />건</td>
					        		<td><fmt:formatNumber value="${myRecipeInfo.totalAmount}" pattern="#,###" />원</td>
				        		</tr>
		        			</c:forEach>
		        		</c:if>
		        	</tbody>
		        </table>
		        <c:if test="${!empty myRecipeListPage}">
       	        <div class="pagingSection">
		            <img src="${contextPath}/img/prev2.png" alt="prev2">
		            <img src="${contextPath}/img/prev1.png" alt="prev1">
		            <c:forEach var="i" begin="${currentGroupStartNo}" end="${currentGroupEndNo}" step="1" varStatus="status">
		            	<span><a href="javascript:goPage(${i});" <c:if test="${currentPage eq i}">class="on"</c:if>>${i}</a></span>
		            </c:forEach>
		            <img src="${contextPath}/img/next1.png" alt="next1">
		            <img src="${contextPath}/img/next2.png" alt="next2">
	        	</div>
	        	</c:if>

	        </div>

        </section>
	    </div>
    
    <!-- footer 영역 -->
    <footer>
        <p>
            © 2020 RECIPE MARKET All rights reserved.
        </p>
        <a class="topBtn">&uarr;TOP</a>
    </footer>
</body>
</html>
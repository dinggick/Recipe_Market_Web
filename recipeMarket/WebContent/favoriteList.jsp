<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

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
	var $recipeInfoObj = $("div.divContent>section.rightSection>div.recipeInfo");
	var $favoriteListObj = $("div.divContent>section.rightSection>div.recipeInfo>table.favoriteList");
	
	// 즐겨찾기 목록 조회

	
	// 즐겨찾기 목록 중 <td>클릭시 레시피상세정보 보기
	
	
	// 즐겨찾기 삭제 이벤트

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
            <p> 10건이 조회 되었습니다.<p>
        </section>
        <!-- 오른쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="rightSection">
	        <div class="recipeInfo">
		        <table class="favoriteList">
	        		<colgroup>
		        		<col width="5%"></col>
		        		<col width="20%"></col>
		        		<col width="20%"></col>
		        		<col width="*"></col>
		        	</colgroup>
		        	<thead>
		        	<tr>
			        	<td>NO</td>
			        	<td>사진</td>
			        	<td>상품명</td>
			        	<td>내용</td>
		        	</thead>
		        	<tbody>
			        	<tr>
			        		<td>1</td>
			        		<td><img class="recipeImg" src="${contextPath}/img/148299577268400131.gif"></td>
			        		<td>당근찌개</td>
			        		<td>맛있어요! 굳<a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a></td>
		        		</tr>
			        	<tr>
				        	<td>2</td>
				        	<td><img class="recipeImg" src="${contextPath}/img/148299577268400131.gif"></td>
				        	<td>고구마찌개</td>
			        		<td>맛있어요! 굳<a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a></td>
			        	</tr>
			        	<tr>
				        	<td>3</td>
				        	<td><img class="recipeImg" src="${contextPath}/img/148299577268400131.gif"></td>
				        	<td>김치찌개</td>
			        		<td>맛있어요! 굳<a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a></td>
			        	</tr>
			        	<tr>
				        	<td>4</td>
				        	<td><img class="recipeImg" src="${contextPath}/img/148299577268400131.gif"></td>
				        	<td>어피치찌개</td>
			        		<td>맛있어요! 굳<a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a></td>
			        	</tr>
			        	<tr>
				        	<td>5</td>
				        	<td><img class="recipeImg" src="${contextPath}/img/148299577268400131.gif"></td>
				        	<td>라이언찌개</td>
			        		<td>맛있어요! 굳<a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a></td>
			        	</tr>
			        	<tr>
				        	<td>6</td>
				        	<td><img class="recipeImg" src="${contextPath}/img/148299577268400131.gif"></td>
				        	<td>라이언찌개</td>
			        		<td>맛있어요! 굳<a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a></td>
			        	</tr>
			        	<tr>
				        	<td>7</td>
				        	<td><img class="recipeImg" src="${contextPath}/img/148299577268400131.gif"></td>
				        	<td>라이언찌개</td>
			        		<td>맛있어요! 굳<a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a></td>
			        	</tr>
			        	<tr>
				        	<td>8</td>
				        	<td><img class="recipeImg" src="${contextPath}/img/148299577268400131.gif"></td>
				        	<td>라이언찌개</td>
			        		<td>맛있어요! 굳<a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a></td>
			        	</tr>
			        	<tr>
				        	<td>9</td>
				        	<td><img class="recipeImg" src="${contextPath}/img/148299577268400131.gif"></td>
				        	<td>라이언찌개</td>
			        		<td>맛있어요! 굳<a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a></td>
			        	</tr>
			        	<tr>
				        	<td>10</td>
				        	<td><img class="recipeImg" src="${contextPath}/img/148299577268400131.gif"></td>
				        	<td>라이언찌개</td>
			        		<td>맛있어요! 굳<a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a></td>
			        	</tr>
		        	</tbody>
		        </table>
		        
       	        <div class="pagingSection">
		            <img src="${contextPath}/img/prev2.png" alt="prev2">
		            <img src="${contextPath}/img/prev1.png" alt="prev1">
		            <span><a href="#">1</a></span>
		            <span><a href="#">2</a></span>
		            <span><a href="#">3</a></span>
		            <span><a href="#">4</a></span>
		            <span><a href="#">5</a></span>
		            
		            <img src="${contextPath}/img/next1.png" alt="next1">
		            <img src="${contextPath}/img/next2.png" alt="next2">
	        	</div>

	        </div>
        </section>
    </div>
    
    <!-- footer 영역 -->
	<div class="footer">
		<jsp:include page="static/footer.html"></jsp:include>
	</div>
</body>
</html>
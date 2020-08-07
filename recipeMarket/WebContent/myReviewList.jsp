<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
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
<link rel="stylesheet" href="${contextPath}/css/review.css">

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
	var $myReviewListObj = $recipeInfoObj.find("table.myReviewList");
	// 후기 목록 조회

	// 후기 목록 중 <td>클릭시 레시피상세정보 보기
	$myReviewListObj.on('click', 'tr', function(e){
	var recipe_code = $(this).find('td>input[name=recipe_code]').val();
		console.log(recipe_code);
		console.log($(this));
	});

	// 후기 삭제
	

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
            <!-- 드롭다운 메뉴 -->
            <div class="dropdown">
                <!-- 로그인 버튼(누르면 드롭다운 메뉴 보이도록) -->
                <h1 class="account">SIGN IN</h1>
                <!-- 드롭다운 메뉴 구성 (동적 생성 필요) -->
                <div class="dropdown-content">
                    <a href="login.html">로그인</a>
                    <a href="#">Menu 2</a>
                    <a href="#">Menu 3</a>
                </div>
            </div>
        </div>
    </header>
    <div class="divContent">
        <!-- 왼쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="leftSection">
            <!-- 화면 제목(또는 레시피 이름) -->
            <h1>나의후기</h1>
            <hr><br>
            <p> 10건이 조회 되었습니다.<p>
        </section>
        <!-- 오른쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="rightSection">
	        <div class="recipeInfo">
		        <table class="myReviewList">
		        	<thead>
		        	<tr>
			        	<td>NO</td>
			        	<td>레시피명</td>
			        	<td>구매일자</td>
			        	<td>내용</td>
			        	<td></td>
		        	</thead>
		        	<tbody>
			        	<tr>
			        		<td>1</td>
			        		<td>당근찌개</td>
			        		<td>2020/08/01</td>
			        		<td>맛있어요! 굳</td>
				        	<td><a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a>
				        		<input type="hidden" name="recipe_code" value="0001"/>
				        	</td>
		        		</tr>
			        	<tr>
				        	<td>2</td>
				        	<td>고구마찌개</td>
				        	<td>2020/07/31</td>
				        	<td>음~달아달아~~</td>
				        	<td><a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a>
				        		<input type="hidden" name="recipe_code" value="0002"/>
				        	</td>
			        	</tr>
			        	<tr>
				        	<td>3</td>
				        	<td>김치찌개</td>
				        	<td>2020/07/28</td>
				        	<td>뷁...ㅎ</td>
				        	<td><a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a>
				        		<input type="hidden" name="recipe_code" value="0003"/>
				        	</td>
			        	</tr>
			        	<tr>
				        	<td>4</td>
				        	<td>어피치찌개</td>
				        	<td>2020/07/25</td>
				        	<td>잔인해...ㅎ</td>
				        	<td><a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a>
				        		<input type="hidden" name="recipe_code" value="0004"/>
				        	</td>
			        	</tr>
			        	<tr>
				        	<td>5</td>
				        	<td>라이언찌개</td>
				        	<td>2020/07/20</td>
				        	<td>사자고기가 정말 맛있어요</td>
				        	<td><a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a>
				        		<input type="hidden" name="recipe_code" value="0005"/>
				        	</td>
			        	</tr>
			        	<tr>
				        	<td>6</td>
				        	<td>라이언찌개</td>
				        	<td>2020/07/20</td>
				        	<td>사자고기가 정말 맛있어요</td>
				        	<td><a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a>
				        		<input type="hidden" name="recipe_code" value="0006"/>
				        	</td>
			        	</tr>
			        	<tr>
				        	<td>7</td>
				        	<td>라이언찌개</td>
				        	<td>2020/07/20</td>
				        	<td>사자고기가 정말 맛있어요</td>
				        	<td><a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a>
				        		<input type="hidden" name="recipe_code" value="0007"/>
				        	</td>
			        	</tr>
			        	<tr>
				        	<td>8</td>
				        	<td>라이언찌개</td>
				        	<td>2020/07/20</td>
				        	<td>사자고기가 정말 맛있어요</td>
				        	<td><a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a>
				        		<input type="hidden" name="recipe_code" value="0008"/>
				        	</td>
			        	</tr>
			        	<tr>
				        	<td>9</td>
				        	<td>라이언찌개</td>
				        	<td>2020/07/20</td>
				        	<td>사자고기가 정말 맛있어요</td>
				        	<td><a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a>
				        		<input type="hidden" name="recipe_code" value="0009"/>
				        	</td>
			        	</tr>
			        	<tr>
				        	<td>10</td>
				        	<td>라이언찌개</td>
				        	<td>2020/07/20</td>
				        	<td>사자고기가 정말 맛있어요</td>
				        	<td><a href="#"><img class="delete" src="${contextPath}/img/delete.png" class="remove-button" /></a>
				        		<input type="hidden" name="recipe_code" value="0010"/>
				        	</td>
			        	</tr>
		        	</tbody>
		        </table>
   		        

	        </div>
	        <div class="pagingSection">
		            <img src="${contextPath}/img/pre2.png" alt="prev2">
		            <img src="${contextPath}/img/pre.png" alt="prev1">
		            <span><a href="#">1</a></span>
		            <span><a href="#">2</a></span>
		            <span><a href="#">3</a></span>
		            <span><a href="#">4</a></span>
		            <span><a href="#">5</a></span>
		            
		            <img src="${contextPath}/img/next.png" alt="next1">
		            <img src="${contextPath}/img/next2.png" alt="next2">
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
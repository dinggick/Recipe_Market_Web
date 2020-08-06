<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recipe Market - 오늘 뭐 먹지?</title>
    <link rel="icon" href="${contextPath}/img/titlecon.png">
    <link rel="stylesheet" href="${contextPath}/css/customScrollBar.css">
	<link rel="stylesheet" href="${contextPath}/css/header.css">
    <link rel="stylesheet" href="${contextPath}/css/contents.css">
    <link rel="stylesheet" href="${contextPath}/css/index.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${contextPath}/js/dropdownMenu.js"></script>
    <script src="${contextPath}/js/favoriteBtn.js"></script>
    <script src="${contextPath}/js/card.js"></script>
    <script src="${contextPath}/js/footer.js"></script>
    <script src="${contextPath}/js/index.js"></script>
    <script>
    </script>
</head>

<body>
<<<<<<< HEAD
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
			<h1 class="account">Sign in</h1>
			<!-- 드롭다운 메뉴 구성 (동적 생성 필요) -->
			<div class="dropdown-content">
				<a href="/recipeMarket/static/login.html">로그인</a>
				<a href="#">Menu 2</a> 
				<a href="#">Menu 3</a>
			</div>
		</div>
	</div>
</header>
    <!-- 메인 메뉴의 컨텐츠 영역 -->
    <div class="mainContent">
        <div class="middleSection">
            <h1>당신의 냉장고에 있는 <b style="color: #D2302C;">[재료]</b>로</h1>
            <h1>지금 검색해보세요</h1>
            <h2>A space where you can experience the joy of various recipes</h2>
            <!-- 검색 -->
            <div class="searchBlock">
                <form method="POST" action="#">
                    <label>
                        <!-- 검색어 입력창 -->
                        <input type="text" class="searchText" size="40">
                        <span class="fa fa-search searchIcon"></span>
                    </label>
                </form>
            </div>
        </div>
        <!-- 추천 레시피 영역 -->
        <section class="bottomSection">
            <h2>실시간 베스트 10</h2>
            <hr>
            <!-- 한줄에 5개씩 열개를 보여준다 -->
            <section class="bestRecipeSection">
                <div class="card">
                    <img src="${contextPath}/img/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 1</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="${contextPath}/img/heart.png" class="favorite">
                        <img src="${contextPath}/img/dislike.png" class="dislike">
                        <img src="${contextPath}/img/like.png" class="like">
                    </div>
                </div>
                <div class="card">
                    <img src="${contextPath}/img/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 2</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="${contextPath}/img/heart.png" class="favorite">
                        <img src="${contextPath}/img/dislike.png" class="dislike">
                        <img src="${contextPath}/img/like.png" class="like">
                    </div>
                </div>
                <div class="card">
                    <img src="${contextPath}/img/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 3</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="${contextPath}/img/heart.png" class="favorite">
                        <img src="${contextPath}/img/dislike.png" class="dislike">
                        <img src="${contextPath}/img/like.png" class="like">
                    </div>
                </div>
                <div class="card">
                    <img src="${contextPath}/img/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 4</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="${contextPath}/img/heart.png" class="favorite">
                        <img src="${contextPath}/img/dislike.png" class="dislike">
                        <img src="${contextPath}/img/like.png" class="like">
                    </div>
                </div>
                <div class="card">
                    <img src="${contextPath}/img/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 5</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="${contextPath}/img/heart.png" class="favorite">
                        <img src="${contextPath}/img/dislike.png" class="dislike">
                        <img src="${contextPath}/img/like.png" class="like">
                    </div>
                </div>
            </section>
            <section class="bestRecipeSection">
                <div class="card">
                    <img src="${contextPath}/img/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 6</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="${contextPath}/img/heart.png" class="favorite">
                        <img src="${contextPath}/img/dislike.png" class="dislike">
                        <img src="${contextPath}/img/like.png" class="like">
                    </div>
                </div>
                <div class="card">
                    <img src="${contextPath}/img/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 7</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="${contextPath}/img/heart.png" class="favorite">
                        <img src="${contextPath}/img/dislike.png" class="dislike">
                        <img src="${contextPath}/img/like.png" class="like">
                    </div>
                </div>
                <div class="card">
                    <img src="${contextPath}/img/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 8</b></h4>
                        <p>레시피 한 줄 요약aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</p>
                        <img src="${contextPath}/img/heart.png" class="favorite">
                        <img src="${contextPath}/img/dislike.png" class="dislike">
                        <img src="${contextPath}/img/like.png" class="like">
                    </div>
                </div>
                <div class="card">
                    <img src="${contextPath}/img/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 9</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="${contextPath}/img/heart.png" class="favorite">
                        <img src="${contextPath}/img/dislike.png" class="dislike">
                        <img src="${contextPath}/img/like.png" class="like">
                    </div>
                </div>
                <div class="card">
                    <img src="${contextPath}/img/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 10</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="${contextPath}/img/heart.png" class="favorite">
                        <img src="${contextPath}/img/dislike.png" class="dislike">
                        <img src="${contextPath}/img/like.png" class="like">
                    </div>
                </div>
            </section>
        </section>
    </div>
    <jsp:include page="/static/footer.html"></jsp:include>
</body>

</html>
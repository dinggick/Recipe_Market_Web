<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
    <link rel="stylesheet" href="${contextPath}/css/customScrollBar.css">
    <link rel="stylesheet" href="${contextPath}/css/divContent.css">
    <link rel="stylesheet" href="${contextPath}/css/contents.css">
    <link rel="stylesheet" href="${contextPath}/css/footer.css">
    <link rel="stylesheet" href="${contextPath}/css/recipeInfo.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${contextPath}/js/dropdownMenu.js"></script>
    <script src="${contextPath}/js/favoriteBtn.js"></script>
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/footer.js"></script>
    <script src="${contextPath}/js/recipeInfo.js"></script>
</head>

<body>
    <header>
        <!-- 왼쪽 영역 -->
        <div class="headerLeftSection">
            <!-- 뒤로 가기 버튼 -->
            <a class="glyphicon glyphicon-chevron-left"></a>
            <!-- 로고(홈 버튼) -->
            <h1 class="home">RECIPE MARKET</h1>
        </div>
        <!-- 오른쪽 영역 -->
        <div class="headerRightSection">
            <jsp:include page="dropdownMenu.jsp"></jsp:include>
        </div>
    </header>
    <div class="divContent">
        <section class="leftSection">
            <div class="remoteControl">
                <ul>
                    <li id="remoteBtnTop">TOP</li>
                    <li id="remoteBtnIng">재료</li>
                    <li id="remoteBtnProcess">과정</li>
                    <li id="remoteBtnReview">후기</li>
                </ul>
            </div>
            <%-- <div class="ad">
                <img src="${contextPath}/img/ad1.jpg" class="adImg">
                <img src="${contextPath}/img/ad2.jpg" class="adImg">
                <img src="${contextPath}/img/ad3.jpg" class="adImg">
            </div>--%>
        </section>
        <!-- 오른쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="rightSection">
            <div class="recipeInfo">
                <div class="basicInfo">
                    <img src="${requestScope.recipeInfo.imgUrl}" class="recipeImg">
                    <h1 style="padding-top: 30px; font-size: xx-large;">${requestScope.recipeInfo.recipeName}</h1>
                    <p>${requestScope.recipeInfo.recipeSumm}</p>
                    <p><span class="recipePrice">${requestScope.recipeInfo.recipePrice}</span> 원</p>
                    <div class="buttonSection">
                        <label class="labelLike"><img src="${contextPath}/img/like.png" class="like"> ${requestScope.recipeInfo.point.likeCount}</label>
                        <label class="labelDisLike"><img src="${contextPath}/img/dislike.png" class="dislike"> ${requestScope.recipeInfo.point.disLikeCount}</label>
                        <img src="${contextPath}/img/heart.png" class="favorite">
                        <button class="cartBtn">장바구니 추가</button>
                        <button class="purchaseBtn">구매하기</button>
                        <input type="number" name="quantity" value="1">
                        <input type="hidden" value="${requestScope.recipeInfo.recipeCode}">
                    </div>
                </div>
                <div class="ingredientSection">
                    <h1>재료</h1>
                    <p>${requestScope.ingredients}</p>
                </div>
                <div class="recipeProcessSection">
                    <h1>과정</h1>
                    <ul>
                    	<c:forEach items="${requestScope.process}" var="p">
                        	<li>${p}</li>
                    	</c:forEach>
                    </ul>
                </div>
                <div class="reviewSection">
                    <h1>후기</h1>
                    <ul>
                        <li>
                            <fieldset>
                                <legend>최종국</legend>
                                <span class="reviewContent">맛있어요</span>
                            </fieldset>
                        </li>
                        <li>
                            <fieldset>
                                <legend>최종국</legend>
                                <span class="reviewContent">맛있어요</span>
                            </fieldset>
                        </li>
                        <li>
                            <fieldset>
                                <legend>최종국</legend>
                                <span class="reviewContent">맛있어요</span>
                            </fieldset>
                        </li>
                        <li>
                            <fieldset>
                                <legend>최종국</legend>
                                <span class="reviewContent">맛있어요</span>
                            </fieldset>
                        </li>
                        <li>
                            <fieldset>
                                <legend>최종국</legend>
                                <span class="reviewContent">맛있어요</span>
                            </fieldset>
                        </li>
                        <li>
                            <fieldset>
                                <legend>최종국</legend>
                                <span class="reviewContent">맛있어요</span>
                            </fieldset>
                        </li>
                        <li>
                            <fieldset>
                                <legend>최종국</legend>
                                <span class="reviewContent">맛있어요</span>
                            </fieldset>
                        </li>
                    </ul>
                </div>
            </div>
        </section>

    </div>
    <footer>
        <p>
            © 2020 RECIPE MARKET All rights reserved.
        </p>
        <a class="topBtn">&uarr;TOP</a>
    </footer>
</body>

</html>
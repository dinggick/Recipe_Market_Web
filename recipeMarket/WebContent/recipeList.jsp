<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="list" value="${requestScope.recipeList}"/>
<c:set var="favoriteCheckList" value="${requestScope.favoriteCheckList}"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recipe Market - 오늘 뭐 먹지?</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="icon" href="./images/titlecon.png">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/customScrollBar.css">
    <link rel="stylesheet" href="css/divContent.css">
    <link rel="stylesheet" href="css/contents.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="./css/main.css">
       <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
      <script src="${contextPath}/js/recipeList.js"></script>
      <script src="${contextPath}/js/header.js"></script>

    <style>
    .searchIcon {
    width: 45px;
    opacity: 1.0;
    padding-right: 3%;
}

.searchIcon:hover {
    cursor: pointer;
    opacity: 1.0;
}
        .leftSection{
        	text-align: center;
        	padding-top: 45px;
        	background-color:  white;
/*         	 box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2); */
        }
        .rightSection{
        	margin-bottom:40px;
        	
        }
        
.card {
    display: inline-block;
    background-color: white;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    transition: 0.3s;
    width: 18%;
    margin: 3% auto 3% auto;
    color: #919aaa;
}

.card:hover {
    box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
    cursor: pointer;
    color: black;
    transition: 0.3s;
}

.card>img {
    width: 100%;
    height: 150px;
}

.cardContainer {
    padding: 0 16px;
}

.cardContainer > h4,
.cardContainer > p{
   display: block;
   width: 100%;
   text-overflow: ellipsis;
   white-space: nowrap;
   overflow: hidden;
}

.like,
.dislike {
    opacity: 0.5;
    transition: 0.3s;
}

.like:hover,
.dislike:hover {
    opacity: 1.0;
}

.favorite,
.like,
.dislike {
    float: right;
    margin-left: 10px;
    margin-bottom: 10px;
}
        
         .rightSection>.recipeInfo { 
         	justify-content:center; 
         	display: flex; 
         	flex-wrap: wrap; 
         	padding: 25px 0px; 
             width: 98%; 
              padding: 30px; 
              box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);   
              background-color:  #F4EFEA; 
         } 
 
         
		.leftSection>hr {
			
  	 		width: 55%;
 		    border: 2px solid #D2302C;
   			
		}
	
		.reSearch{			
			border:solid;
			border-radius: 5px;
			width:85%;
			margin: 0 auto 0 auto;
			background-color: white;
			height: 35px;
		}
        .mainButton {
        	color:  #F4EFEA;
        	font-weight: bold;
        } 
        .mainButton:hover {
        	color:  #F4EFEA;;
        	cursor: pointer;
        }
        .searchText{
        	border: none;
        	width: 80%;
        	height: 85%;
        }
       
         
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
    <script src="js/dropdownMenu.js"></script>
    <script src="js/favoriteBtn.js"></script>
    <script src="js/header.js"></script>
    <script src="js/footer.js"></script>
</head>

<body>
    <header>
        <!-- 왼쪽 영역 -->
        <div class="headerLeftSection">
            <!-- 로고(홈 버튼) -->
            <h1 class="home">RECIPE MARKET</h1>
        </div>
        <!-- 오른쪽 영역 -->
        <div class="headerRightSection">
            <jsp:include page="dropdownMenu.jsp"></jsp:include>
        </div>
    </header>
    <div class="divContent">
        <!-- 왼쪽 영역 (화면에 따라 동적 생성 필요) -->
		<section class="leftSection">
			<!-- 화면 제목(또는 레시피 이름) -->
			<h1>검색결과</h1>
			<hr>
			<br>
			<p>'${requestScope.ingName}'로 ${list.size()}건이 조회되었습니다.
			<p>
				<br>
			<div class="reSearch">
				<input type="text" class="searchText" size="18px"
					placeholder="다시 검색할래요">  
				<a class="fa fa-search searchIcon"></a>

			</div>

			<!-- 레시피 상세 화면이기때문에 아래 재료 리스트를 생성하였음. 다른 화면의 경우 필요에 따라 구현 -->

		</section>
		<!-- 오른쪽 영역 (화면에 따라 동적 생성 필요) -->
		<section class="rightSection">

			<div class="recipeInfo">
			<c:choose>
			<c:when test="${list.size() > 0 }">
				<c:forEach begin="0" end="${list.size()-1}" var="i">
					<div class="card">
						<img src="${list[i].imgUrl}">
						<div class="cardContainer">
							<h4>
								<b>${list[i].recipeName}</b>
							</h4>
							<p>${list[i].recipeSumm}</p>
							<c:choose>
								<c:when test="${favoriteCheckList[i] == true}">
									<img src="${contextPath}/img/filled_heart.png" class="favorite"> 
								</c:when>
								<c:otherwise>
									<img src="${contextPath}/img/heart.png" class="favorite"> 
								</c:otherwise>
							</c:choose>
				 			<img src="${contextPath}/img/dislike.png" class="dislike">
				 			<img src="${contextPath}/img/like.png" class="like">
				 			<input type="hidden" value="${list[i].recipeCode}">
						</div>
					</div>
					</c:forEach>
				</c:when>
					<c:otherwise>
						
						<div>
						
						<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
						<h1 style="text-align: center;">검색결과가 없습니다.</h1>
						<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
						
						</div>
					</c:otherwise>
				</c:choose>
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
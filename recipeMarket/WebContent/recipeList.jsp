<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <style>
        .leftSection{
        	text-align: center;
        	padding-top: 45px;
        	background-color:  white;
/*         	 box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2); */
        }
        .rightSection{
        	margin-bottom:40px;
        	
        }
        
        .rightSection>.recipeInfo {
        	justify-content:center;
        	display: flex;
        	flex-wrap: wrap;
        	padding: 25px 0px;
            width: 98%;
            /* padding: 30px; */
             box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);  
             background-color:  #F4EFEA;
        }
        
        .rightSection>.recipeInfo>img {
            width: 100%;
        }
        .cardContainer>p {
        	font-size: small;
        }
        
        .rightSection>.recipeInfo>.recipeProcessSection>ul {
            list-style-type: none;
            line-height: 50px;
            padding-left: 5%;
        }
        
        .rightSection>.recipeInfo>.recipeProcessSection>ul>li {
            width: 95%;
            border-radius: 5px;
            background-color: rgb(239, 239, 239);
            margin-bottom: 2%;
            padding-left: 2%;
            padding-right: 2%;
            word-break: normal;
        }
        
        .rightSection>.recipeInfo>.reviewSection {
            padding-bottom: 20px;
        }      
       
        
        .like>img,
        .dislike>img {
            width: 100%;
        }
          .card{        	  
          	margin: 18px; 
          	width: 20%;  
         }  
         
		.leftSection>hr {
			
  	 		width: 55%;
 		    border: 2px solid #D2302C;
   			
		}
		.card>h4:hover{			
			
			font-size: larger;			
		}
		.reSearch{			
			border:solid;
			border-radius: 5px;
			width:85%;
			margin: 0 auto 0 auto;
			background-color: white;
		}
        .mainButton {
        	color:  #F4EFEA;
        	font-weight: bold;
        } 
        .mainButton:hover {
        	color:  #F4EFEA;;
        	cursor: pointer;
        }
       
         
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
    <script src="js/dropdownMenu.js"></script>
    <script src="js/favoriteBtn.js"></script>
    <script src="js/header.js"></script>
    <script src="js/footer.js"></script>
    <script>
//     addEventListener("load", () => {
//         $(".rightSection").height(window.innerHeight - (90 + $('footer').outerHeight() + $('header').outerHeight())); //rightSection의 높이를 window의 높이에 따라 동적 설정
//     });
    </script>
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
            <!-- 드롭다운 메뉴 -->
            <div class="dropdown">
                <!-- 사람 모양 아이콘(누르면 드롭다운 메뉴 보이도록) -->
<!--                 <img src="./images/whisk.png" class="account"> -->
               <label class="mainButton">LOGIN</label>
                <!-- 드롭다운 메뉴 구성 (동적 생성 필요) -->
                <div class="dropdown-content">
                    <a href="#">로그인</a>
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
            <h1>검색결과</h1>
             <hr>
             <br>
             <p> '동그랑땡'으로 30건이 조회되었습니다.<p>
             <br>
             
              <div class="reSearch">
             <input type="text" class="searchText" size="18px" placeholder="다시 검색할래요">
             <span class="fa fa-search searchIcon"></span>
             
             </div>
             
            <!-- 레시피 상세 화면이기때문에 아래 재료 리스트를 생성하였음. 다른 화면의 경우 필요에 따라 구현 -->
         
        </section>
        <!-- 오른쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="rightSection">
        	
            <div class="recipeInfo">
                 <div class="card">
                    <img src="images/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 1</b></h4>
                        <p>똥글똥글 똥그랑땡! 맛있어용 짱 맛있땅</p>
                        <img src="./images/heart.png" class="favorite">
                        <img src="./images/dislike.png" class="dislike">
                        <img src="./images/like.png" class="like">
                    </div>
                </div>
                <div class="card">
                    <img src="images/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 2</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="./images/heart.png" class="favorite">
                        <img src="./images/dislike.png" class="dislike">
                        <img src="./images/like.png" class="like">
                    </div>
                </div>
                <div class="card">
                    <img src="images/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 3</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="./images/heart.png" class="favorite">
                        <img src="./images/dislike.png" class="dislike">
                        <img src="./images/like.png" class="like">
                    </div>
                </div>
                <div class="card">
                    <img src="images/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 4</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="./images/heart.png" class="favorite">
                        <img src="./images/dislike.png" class="dislike">
                        <img src="./images/like.png" class="like">
                    </div>
                </div>
               
              <div class="card">
                    <img src="images/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 5</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="./images/heart.png" class="favorite">
                        <img src="./images/dislike.png" class="dislike">
                        <img src="./images/like.png" class="like">
                    </div>
                </div>    
                
                <div class="card">
                    <img src="images/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 5</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="./images/heart.png" class="favorite">
                        <img src="./images/dislike.png" class="dislike">
                        <img src="./images/like.png" class="like">
                    </div>
                </div>       
                <div class="card">
                    <img src="images/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 5</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="./images/heart.png" class="favorite">
                        <img src="./images/dislike.png" class="dislike">
                        <img src="./images/like.png" class="like">
                    </div>
                </div>       
                <div class="card">
                    <img src="images/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 5</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="./images/heart.png" class="favorite">
                        <img src="./images/dislike.png" class="dislike">
                        <img src="./images/like.png" class="like">
                    </div>
                </div>       
                 <br>
              <div class="card">
                    <img src="images/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 5</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="./images/heart.png" class="favorite">
                        <img src="./images/dislike.png" class="dislike">
                        <img src="./images/like.png" class="like">
                    </div>
                </div>       
                <div class="card">
                    <img src="images/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 5</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="./images/heart.png" class="favorite">
                        <img src="./images/dislike.png" class="dislike">
                        <img src="./images/like.png" class="like">
                    </div>
                </div>       
                <div class="card">
                    <img src="images/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 5</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="./images/heart.png" class="favorite">
                        <img src="./images/dislike.png" class="dislike">
                        <img src="./images/like.png" class="like">
                    </div>
                </div>       
                <div class="card">
                    <img src="images/148299577268400131.gif" alt="Avatar">
                    <div class="cardContainer">
                        <h4><b>레시피 5</b></h4>
                        <p>레시피 한 줄 요약</p>
                        <img src="./images/heart.png" class="favorite">
                        <img src="./images/dislike.png" class="dislike">
                        <img src="./images/like.png" class="like">
                    </div>
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
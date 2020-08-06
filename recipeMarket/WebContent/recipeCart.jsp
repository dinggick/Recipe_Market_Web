<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recipe Market - 오늘 뭐 먹지?</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">
    <link rel="icon" href="./images/titlecon.png">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/customScrollBar.css">
    <link rel="stylesheet" href="css/divContent.css">
    <link rel="stylesheet" href="css/contents.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/customScrollBar.css">
    
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css">
    <style>
    	 .leftSection{
           text-align: center;
           padding-top: 45px;
        }
    	
		    
        .leftSection>ul {
            list-style-type: none;
            line-height: 50px;
            padding-left: 5%;
            overflow: auto;
        }
        
        .rightSection>.cartInfo {
            width:98%;
            /* padding: 30px; */
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2); 
        }
        
        .rightSection{
        	background-color:#F4EFEA;
          /*overflow:auto;*/
        }
        
        .rightSection>.cartInfo>img {
            width: 100%;
        }
        
        .rightSection>.cartInfo>.recipeProcessSection>ul {
            list-style-type: none;
            line-height: 50px;
            padding-left: 5%;
        }
        
        .rightSection>.cartInfo>.recipeProcessSection>ul>li {
            width: 95%;
            border-radius: 5px;
            background-color: rgb(239, 239, 239);
            margin-bottom: 2%;
            padding-left: 2%;
            padding-right: 2%;
            word-break: normal;
        }
        
        .rightSection>.cartInfo>.reviewSection {
            padding-bottom: 20px;
        }
        
        .rightSection>.cartInfo>.reviewSection>ul {
            list-style-type: none;
            line-height: 50px;
            padding-left: 5%;
        }
        
        .like>img,
        .dislike>img {
            width: 100%;
        }
        
        #Cart{
           width:100%;
           border-spacing:0 20px;
           font-size:x-large;
           text-align:center;
           padding:20px;
           padding-top:0%;   
        }
        
        table>tbody>tr>td{ 	   
       	   margin:0;
       	   padding:0;
        }
       
       .conditon{
       	  font-size:1vw;
       }
       
       .line2{
          padding:2.5%;
          border-bottom:2px solid #D2302C;  
       }   
             
       h1{
        font-size:xx-large; 
       }
       
       .delete{
       	width:80%;
       	font-size:1vw;
       	background-color:#F4EFEA;
       	border:0;
       	outline:0;   
       }
       
       .click{
       	width:50px;
       	height:30px;
       }
       
       .quantity{
       	width:20%;
       	height:30px;  
       }
       
       .totalQuantity{
         padding-left:85%;
         font-size:xx-large;	   
       }
       
       .purchaseCart{
         margin-left:45%;
         margin-bottom:1.5%;
         margin-top:1.5%;
         width:10%;
         background-color:#8bd8bd;
         border:0;
         outline:0;
         color:white;
         font-size:1.3vw;
         height:50px; 
         text-align:center;
         
       }
       
       .recipePhoto{
       	 width:30px; 
       }
       
       .check{
       	 width:35px;
         height:35px;
       
       }
       
        hr{
        width:55%;
        border:2px solid #D2302C;
       }
          
       .purchaseLine{
         width:13.5%;
       	 display:block;
       	 margin-left:85%;
       	 margin-top:3%;
         border:1px solid #D2302C;
       }
    </style>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="js/dropdownMenu.js"></script>
    <script src="js/favoriteBtn.js"></script>
    <script src="js/header.js"></script>
    <script src="js/footer.js"></script>
<script>
    /* addEventListener("load", () => {
        $(".rightSection").height(window.innerHeight - (90 + $("footer").outerHeight() + $("header").outerHeight())); //rightSection의 높이를 window의 높이에 따라 동적 설정
    }); */
    
   $(function(){
	   $('#check-All').click(function(){
		  $('.check').prop('checked',this.checked); 
	   });
	   
	  
	   $('.delete').click(function(e){
		   $(e.target).parents('tr').remove();
	   });
	   
	   $('.purchaseCart').click(function(){
		   alert('주문이 완료되었습니다');
	   })
    });
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
                <img src="./images/user.png" class="account">
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
            <h1>장바구니</h1>
            <hr>
        </section>
        
        <!-- 오른쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="rightSection">
            <div class="cartInfo">
                <table id="Cart">
                	<tr><td class="line2"><input type="checkbox" class="check" id="check-All"></td><td class="line2">사진</td><td class="line2">상품명/한줄요약</td><td class="line2">가격</td><td class="line2">수량</td><td class="line2">총금액</td><td class="line2"></td></tr>
                	<tr class="cartList">
                	   <td><input type="checkbox" class="check" ><label></label> </td>
                	   <td><a href="./purchaseList.html"><img src="./img/list.png" class="recipePhoto"></a></td>
                	   <td>김치찌개/맛나요</td>
                	   <td>1000원</td>
                	   <td><input type="number" class="quantity" value="1" min=1></td>
                	   <td>3000원</td>
                	   <td><button type="submit" class="delete">X</button></td>
                	</tr>
                	<tr class="cartList">
                	   <td><input type="checkbox" class="check" ><label></label> </td>
                	   <td><a href="./purchaseList.html"><img src="./img/list.png" class="recipePhoto"></a></td>
                	   <td>김치찌개/맛나요</td>
                	   <td>1000원</td>
                	   <td><input type="number" class="quantity" value="1" min=1></td>
                	   <td>3000원</td>
                	   <td><button type="submit" class="delete">X</button></td>
                	</tr>
                </table>
                <hr class="purchaseLine">
            	  <div class="totalQuantity">총 금액 : 3000원</div>
            	  <button type="submit" class="purchaseCart">주문하기</button>
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
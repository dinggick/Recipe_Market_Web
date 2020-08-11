<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
/*         	background-color:#F4EFEA; */
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
         padding-left:80%;
         font-size:2vw;
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
       	  width: 60px; 
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
       	 margin-left:80.5%;
       	 margin-top:3%;
         border:1px solid #D2302C;
       }
       
       .cartInfo {
       	background-color:#F4EFEA;
       }
    </style>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="js/dropdownMenu.js"></script>
    <script src="js/favoriteBtn.js"></script>
    <script src="js/header.js"></script>
    <script src="js/footer.js"></script>
<script>
   $(function(){
	   $('#check-All').click(function(){
		  $('.check').prop('checked',this.checked);
		  
	   });
	   
	  //내 레시피 삭제 
	   $('.delete').click(function(e){
		  var $recipeCode = $(this).parent().parent().find('input[type=hidden]').val();
		  $.ajax({
			   url:'/recipeMarket/cartRemove',
			   data:{recipeCode:$recipeCode},
			   success:function(responseObj){
				   if(responseObj.status=="success"){
					   $(e.target).parents('tr').remove();
				   }else{
					   alert('삭제실패');
				   }
			   }
		  })
		  return false;
	   });
	   
	  //체크항목 구매
	   $('.purchaseCart').click(function(){
		  var $check = $('.check').val();
		  var recipeCode = $(this).parent().parent().find('input[type=hidden]').val();
		  var quantity = $('.quantity').val();
		  	$.ajax({
				url:'/recipeMarket/purchase',
				data:{recipeCode : recipeCode , purchaseQuantity : quantity},
				success:function(responseObj){
					if(responseObj.status=="success"){
						alert('구매가 완료되었습니다');
					}
				}
		 	})
			return false;
	   })
	   
	   //수량 업데이트
	   $('.rightSection').on('click','.quantity',function(){
		   console.log('test');
		  var $recipeCode = $(this).parent().parent().find('input[type=hidden]').val();
		  console.log($recipeCode);
		  var quantity = $(this).parent().parent().find('.quantity	').val();
		  var priceList = $('.price');
		  
		  var recipePrice = parseInt($(this).parent().parent().find('td:nth-child(4)').html());
		  var $price = $(this).parent().parent().find('td:nth-child(6)');
		  var $total = $('.totalQuantity');
		  var totals = 0;
		  
		  $.ajax({
			  url:'/recipeMarket/cartUpdate',
			  data:{recipeCode:$recipeCode,quantity:quantity,customerEmail:"pyonjw@recipe.com"},
			  success:function(responseObj){
				  if(responseObj.status=="success"){
					  $price.html((recipePrice*quantity)+'원');
					  for(x in priceList){
						console.log(priceList[x].innerHTML);
						totals += priceList[x].innerHTML; /*.substring(0,priceList[x].innerHTML.length-1) */ 
					  }
					  $total.html("총 금액 : " + totals+"원");
				  }else{
					  alert('수량변경실패');
				  }
			  }
		  })
		  return false;
	   });
	   
	   
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
                <h1 class="account">Sign in</h1>
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
                	<c:set var="total" value="0"></c:set>
                	  <c:forEach items="${requestScope.list}" var="c">
                	  	  <c:set var="total" value="${total + c.recipeInfo.recipePrice*c.cartQuantity}" />
						  <tr class="cartList">
                	   		<td><input type="checkbox" class="check" ><label></label> </td>
                	   		<td><a href="./recipeInfo.jsp"><img src="${c.recipeInfo.imgUrl}" class="recipePhoto"></a></td>
                	   		<td>${c.recipeInfo.recipeName}<input type="hidden" value="${c.recipeInfo.recipeCode}"/></td>
                	   		<td>${c.recipeInfo.recipePrice}</td>
                	   		<td><input type="number" class="quantity" value="${c.cartQuantity}" min=1></td>
                	  	 	<td class="price">${c.recipeInfo.recipePrice*c.cartQuantity}</td>
                	   		<td><button type="submit" class="delete">X</button></td>
                	   		</tr>                		
                	  </c:forEach>
                </table>
                <hr class="purchaseLine">
            	  <div class="totalQuantity">총 금액 : <fmt:formatNumber value="${total}" pattern="#,###"/>원</div>
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
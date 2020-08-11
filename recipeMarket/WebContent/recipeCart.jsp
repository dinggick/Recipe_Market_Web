<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="list" value="${requestScope.list}"/>
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
         padding-left:80%;
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
	   
	  //내 레시피 삭제 
	   $('.delete').click(function(e){
		  var $recipeCode = $(this).parent().parent().find('input[type=hidden]').val();
		  
		  var $price = $(this).parent().parent().find('td:nth-child(6)');
		  var $total = $('.totalQuantity');
		  var totals = 0;
		  
		  $.ajax({
			   url:'/recipeMarket/cartRemove',
			   data:{recipeCode:$recipeCode},
			   success:function(responseObj){
				   if(responseObj.status=="success"){
					   $(e.target).parents('tr').remove();
					   
					   var priceList = $('.price').find("span");
						  for(x of priceList){
							totals += parseInt(x.innerHTML);
						  }
						  
						  $total.html("총 금액 : " + totals.toLocaleString()+"원");
				   }else{
					   alert('삭제실패');
				   }
			   }
		  })
		  return false;
	   });
	   
	  //체크항목 구매
	   $('.purchaseCart').click(function(){
		 let picks = '';
		 let quantities ="";
		 if($("input[id=check]:checked")==false){
			 alert('상품을 선택해주세요');
			 return;
		 }
		 $("input[id=check]:checked").each(function(){
			 picks += $(this).val()+",";
			 quantities += $(this).parent().parent().find('input.quantity').val()+",";
			 //quantities += $(this).siblings('.quantity').val()+",";
			 console.log(quantities);
		 });
		 picks = picks.slice(0,-1);
		 quantities = quantities.slice(0,-1);
		  $.ajax({
			url:'/recipeMarket/purchase',
			data:{recipeCode : picks , purchaseQuantity : quantities},
			success:function(responseObj){
				if(responseObj.status=="success"){
					alert('구매가 완료되었습니다');
					location.reload();
				}else{
					alert('구매실패! 다시시도해주세요');
				}
			}
		  })
		  return false;
	   });
	   
	   //수량 업데이트
	   $('.rightSection').on('click','.quantity',function(){
		  var $recipeCode = $(this).parent().parent().find('input[type=hidden]').val();
		  var quantity = $(this).parent().parent().find('.quantity').val();
		  
		  var recipePrice = parseInt($(this).parent().parent().find('td:nth-child(4)').html());
		  var $price = $(this).parent().parent().find('td:nth-child(6)');
		  var $total = $('.totalQuantity');
		  var totals = 0;
		 
		  
		  $.ajax({
			  url:'/recipeMarket/cartUpdate',
			  data:{recipeCode:$recipeCode,quantity:quantity},
			  success:function(responseObj){
				  if(responseObj.status=="success"){
					  $price.html("<span>" + (recipePrice*quantity) +'</span>원');
					  
					  var priceList = $('.price').find("span");
					  for(x of priceList){
						totals += parseInt(x.innerHTML);
					  }
					  
					  $total.html("총 금액 : " + totals.toLocaleString()+"원");
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
            <jsp:include page="dropdownMenu.jsp"></jsp:include>
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
                	 <c:if test="${list.size()==0}">
                	  	장바구니내역이없습니다
         			  </c:if>
                	  <c:forEach items="${requestScope.list}" var="c">
                	  	  <c:set var="total" value="${total + c.recipeInfo.recipePrice*c.cartQuantity}" />
						  <tr class="cartList">
                	   		<td><input type="checkbox" id="check" class="check" value="${c.recipeInfo.recipeCode}"><label></label> </td>
                	   		<td><a href="/recipeMarket/recipeInfo?recipeCode=${c.recipeInfo.recipeCode}"><img src="${c.recipeInfo.imgUrl}" class="recipePhoto"></a></td>
                	   		<td>${c.recipeInfo.recipeName}<input type="hidden" value="${c.recipeInfo.recipeCode}"/></td>
                	   		<td>${c.recipeInfo.recipePrice}</td>
                	   		<td><input type="number" class="quantity" value="${c.cartQuantity}" min=1></td>
                	  	 	<td class="price"><span>${c.recipeInfo.recipePrice*c.cartQuantity}</span>원</td>
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
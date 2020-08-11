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
    <link rel="stylesheet" href="css/recipeCart.css">
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css">
   
    
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
                	 <c:if test="${empty list}">
                	  	<tr><td colspan='6'>장바구니내역이없습니다</td></tr>
         			  </c:if>
                	  <c:forEach items="${requestScope.list}" var="c">
                	  	  <c:set var="total" value="${total + c.recipeInfo.recipePrice*c.cartQuantity}" />
						  <tr class="cartList">
                	   		<td><input type="checkbox" id="check" class="check" value="${c.recipeInfo.recipeCode}"><label></label> </td>
                	   		<td><a href="/recipeMarket/recipeInfo?recipeCode=${c.recipeInfo.recipeCode}"><img src="${c.recipeInfo.imgUrl}" class="recipePhoto"></a></td>
                	   		<td>${c.recipeInfo.recipeName}<input type="hidden" value="${c.recipeInfo.recipeCode}"/></td>
                	   		<td>${c.recipeInfo.recipePrice}원</td>
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
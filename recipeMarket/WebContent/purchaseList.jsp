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
    
    
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="${contextPath}/css/header.css">
    <link rel="stylesheet" href="${contextPath}/css/customScrollBar.css">
    <link rel="stylesheet" href="${contextPath}/css/divContent.css">
    <link rel="stylesheet" href="${contextPath}/css/contents.css">
    <link rel="stylesheet" href="${contextPath}/css/footer.css">
    <link rel="stylesheet" href="${contextPath}/css/customScrollBar.css">
   	<link rel="stylesheet" href="${contextPath}/css/purchaseList.css">
   	<link rel="stylesheet" href="${contextPath}/css/review.css">
   	<link rel="stylesheet" href="${contextPath}/css/modal.css">
    <!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"> --> 
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    
    
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
    
    
    
    <script src="${contextPath}/js/dropdownMenu.js"></script>
    <script src="${contextPath}/js/favoriteBtn.js"></script>
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/footer.js"></script>
<script>
    
$(function(){
    $("#datepicker").hide();
	
    //날짜 조건검색
	$('#dt').click(function() {
			$("#datepicker").show();
			$("#datepicker").datepicker({
				onSelect : function(date) {
					//var date = $("#datepicker").datepicker("getDate");
					var date1 = $('#datepicker').datepicker({ dateFormat: 'yyyy-mm-dd' }).val();
					
					var date = new Date(date1);
					console.log(date);
					
					var d = Date.parse(date1);
					console.log(d);
						
					location.href = "/recipeMarket/purchaseListByDatePick?date=" + d;
					$("#datepicker").hide();
				}
			});
		});

		//1주일 날짜검색
		$('.weeks').click(function() {
			var date = new Date();

			console.log(date.getDate() - 7);
			var weeks = date.setDate(date.getDate() - 7);
			console.log(weeks);

			location.href = "/recipeMarket/purchaseList?date=" + weeks;

		});

		//1달 날짜검색
		$('.month').click(function() {
			var date = new Date();

			console.log(date.getDate() - 31);
			var months = date.setDate(date.getDate() - 31);
			console.log(months);

			location.href = "/recipeMarket/purchaseList?date=" + months;

		});

		//3개월 날짜검색
		$('.quarter').click(function() {
			var date = new Date();

			console.log(date.getDate() - 99);
			var quarters = date.setDate(date.getDate() - 99);
			console.log(quarters);

			location.href = "/recipeMarket/purchaseList?date=" + quarters;

		});

		//6개월 날짜검색
		$('.half').click(function() {
			var date = new Date();

			console.log(date.getDate() - 186);
			var halfs = date.setDate(date.getDate() - 186);
			console.log(date);

			location.href = "/recipeMarket/purchaseList?date=" + halfs;

		});
		
		//레시크 상세이동시 모달창 이벤트막기
		$('.recipeName').click(function(event){
			event.stopPropagation();
		});
		
		////////////////////////////////////////////
	  var sectionObj = $('section.rightSection');
      var purchaseTableObj = $('section.rightSection > div.purchaseInfo > table#pucrhase');
      
      //입력 모달창 호출
       $.ajax ({
         url : '${contextPath}/reviewAdd.jsp'
         , method : 'POST'
         , success : function (data) {
            console.log(data);
            sectionObj.append(data);
            $("#reviewModal").hide();
         }
      });
       
      purchaseTableObj.on('click', '.addReview', function(e){
         var purchaseCode = $(this).parent().find('input.purchaseCode').val();
         var recipeCode = $(this).parent().find('input.recipeCode').val();
         
          $("#reviewModal").show();

          var reviewBtnObj = $('button.reviewBtn');
          reviewBtnObj.on('click', function() {
             var reviewContent = $("div.reviewContent>input").val();
             console.log ("리뷰내용 : " + reviewContent); 
             console.log ("purchaseCode : " + purchaseCode); 
             console.log ("recipeCode : " + recipeCode); 
             
             $.ajax ({
               url : '${contextPath}/review/add'
               , method : 'POST'
               , data: {"purchaseCode" : purchaseCode 
                      , "reviewContent" : reviewContent
                      , "recipeCode" : recipeCode }
               , success : function (data) {
                  if (data.status == "success") {
                     location.reload();
                  } else {
                     alert ("후기등록실패 " + data.msg);
                  }
               }
            }); // 리뷰등록 ajax
            return false;
         }); // 리뷰등록 버튼 클릭 이벤트
       }); // 테스트이벤트
		
		
		/////////////////////////////////////////////////
}); //ready이벤트 종료
</script>
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
            <!-- 드롭다운 메뉴 -->
           <jsp:include page="/dropdownMenu.jsp"></jsp:include>
        </div>
    </header>
    <div class="divContent">
        <!-- 왼쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="leftSection">
            <!-- 화면 제목(또는 레시피 이름) -->
            <h1>구매내역</h1>
            <hr>
        </section>
        <!-- 오른쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="rightSection">
            <div class="purchaseInfo">
            <div id="datepicker" class="date"></div>
            <div class="dd"><button type="submit" class="weeks conditon">1주일</button><button type="submit" class="month conditon">1개월</button><button type="submit" class="quarter conditon">3개월</button><button type="submit" class="half conditon">6개월</button><button type="submit" class="conditon" id="dt">조건검색</button></div>
                <table id="pucrhase">
                	<tr><td class="line2">구매날짜</td><td class="line2">상품명</td><td class="line2">수량</td><td class="line2">구매금액</td><td class="line2">후기등록<td></tr>
                	   <c:if test="${empty list}">
                	  	<tr><td colspan="5">구매내역이 없습니다</td></tr>
         			  </c:if>
                	   <c:forEach items="${requestScope.list}" var="p">
                	   		<c:forEach items="${p.purchaseDetails}" var="purchaseDetail">
                	   		<tr><td>${p.purchaseDate}</td>
                	   		<td><a href="/recipeMarket/recipeInfo?recipeCode=${purchaseDetail.recipeInfo.recipeCode}" class="recipeName">${purchaseDetail.recipeInfo.recipeName}</a></td>
                	   		<td>${purchaseDetail.purchaseDetailQuantity}개</td>
                	   		<td><fmt:formatNumber value="${purchaseDetail.purchaseDetailQuantity*purchaseDetail.recipeInfo.recipePrice}" pattern="#,###"/>원</td>
                	   		<td>
                	   			<c:if test="${p.review.reviewComment eq null}">
                	   				<button type="submit" class="addReview" data-toggle="modal"  data-target="#reviewModal"><img src="./img/list.png" class="toy"></button>
	               	   				<input type="hidden" class="purchaseCode" value="${p.purchaseCode}" />
	               	   				<input type="hidden" class="recipeCode" value="${purchaseDetail.recipeInfo.recipeCode}" />
	               	   			</c:if>
                	   		</td></tr>
                	   		</c:forEach>
                	   </c:forEach>
                </table>
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
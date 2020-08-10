<%@ page contentType="text/html; charset=UTF-8"%>
<%@	page import="com.recipe.model.PageBean"%>
<%@	page import="com.recipe.vo.Review"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<c:set var="pb" value="${requestScope.pb}"/>
<c:set var="currentPage" value="${pb.currentPage}"/>
<c:set var="list" value="${pb.list}"/>
<c:set var="CNT_PER_PAGE" value="${PageBean.CNT_PER_PAGE}"/>
<c:set var="CNT_PER_PAGEGROUP" value="${PageBean.CNT_PER_PAGEGROUP}"/>
<c:set var="startRow" value="${pb.startRow}"/>
<c:set var="totalPage" value="${pb.totalPage}"/>
<c:set var="startPage" value="${pb.startPage}"/>
<c:set var="endPage" value="${pb.endPage}"/>
<c:set var="url" value="${pb.url}"/>

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
	// 페이지변동 생길 때 목록 reload
	
	// 후기 목록 중 <td>클릭시 레시피상세정보 보기
	$myReviewListObj.on('click', 'tr', function(e){
	var purchaseCode = $(this).find('input[name=deleteCode]').val();
		
		if ( e.target.className == 'delete' ) {
			$.ajax({
				url : '${contextPath}/review/remove' 
				, data : {"purchaseCode" : purchaseCode}
				, success : function(data) {
					if ( data.status == 'success') {
						alert("삭제에 성공했습니다.");
						location.reload();
					} else {
						alert("삭제에 실패했습니다.");
					}
					
				} //end of success
			}); //end of Ajax
			return false;
		} // 삭제 이벤트 발생
		
		var recipeCodeForm = $("div.divContent>section.rightSection>form");
		var recipeCodeObj = $('input[id=recipeCode]');
		recipeCodeObj.attr("value", recipe_code);
		
    	var form = document.createElement("form");
    	form.setAttribute("method", "POST");
    	form.setAttribute("action", "/recipeMarket/recipeInfo");
    	
    	var input = document.createElement("input");
    	input.setAttribute("type", "hidden");
    	input.setAttribute("name", "recipeCode");
    	input.setAttribute("value", recipe_code);
    	form.appendChild(input);
    	document.body.appendChild(form);
    	form.submit();
    	return false;
	});
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
            <p> ${requestScope.myReviewList.size()} 건이 조회 되었습니다.<p>
        </section>
        <!-- 오른쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="rightSection">
	        <div class="recipeInfo">
		        <table class="myReviewList">
		        	<colgroup>
		        		<col width="3%"></col>
		        		<col width="20%"></col>
		        		<col width="10%"></col>
		        		<col width="*"></col>
		        		<col width="5%"></col>
		        	</colgroup>
		        	<thead>
		        	<tr>
			        	<td>NO</td>
			        	<td>레시피명</td>
			        	<td>구매일자</td>
			        	<td>내용</td>
			        	<td></td>
		        	</thead>
		        	<tbody>
						<c:if test="${empty requestScope.myReviewList}" > 
                	   		<tr> 
                	   			<td colspan='5'> 등록된 후기가 없습니다.</td>
							</tr>
						
						</c:if>
                	   <c:forEach items="${requestScope.myReviewList}" var="review" varStatus="status">
                	   		<c:forEach items="${review.purchase.purchaseDetails}" var="purchaseDetail" >
                	   		<tr> 
                	   			<td> ${status.index+1} </td>
                	   			<td> ${purchaseDetail.recipeInfo.recipeName} </td>
                	   			<td> ${review.purchase.purchaseDate} </td>
                	   			<td><span> ${review.reviewComment} </span> 
                	   				<input type="hidden" name="recipe_code" value="${purchaseDetail.recipeInfo.recipeCode}"/>
                	   			</td>
                	   			<td class="delete"><a href="#">
                	   				<img class="delete" src="${contextPath}/img/delete.png" class="remove-button" />
                	   				<input type="hidden" name="deleteCode" value="${review.purchase.purchaseCode}"/></a>
			        			</td>
							</tr>
                	   		</c:forEach>
                	   </c:forEach>
		        	</tbody>
		        </table>
		        
       	        <div class="pagingSection">
		            <img src="${contextPath}/img/prev2.png" alt="prev2">
		            <img src="${contextPath}/img/prev1.png" alt="prev1">
		            <span><a href="#">1</a></span>
		            <span><a href="#">2</a></span>
		            <span><a href="#">3</a></span>
		            <span><a href="#">4</a></span>
		            <span><a href="#">5</a></span>
		            
		            <img src="${contextPath}/img/next1.png" alt="next1">
		            <img src="${contextPath}/img/next2.png" alt="next2">
	        	</div>

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
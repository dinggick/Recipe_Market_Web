<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="icon" href="./img/titlecon.png">
    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/customScrollBar.css">
    <link rel="stylesheet" href="css/divContent.css">
    <link rel="stylesheet" href="css/contents.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/customScrollBar.css">
    <link rel="stylesheet" href="./css/review.css">
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
        
        .rightSection>.purchaseInfo {
            width:98%;
            /* padding: 30px; */
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2); 
        }
        
        .rightSection{
        	background-color:#F4EFEA;
          /*overflow:auto;*/
        }
        
        .rightSection>.purchaseInfo>img {
            width: 100%;
        }
        
        .rightSection>.purchaseInfo>.recipeProcessSection>ul {
            list-style-type: none;
            line-height: 50px;
            padding-left: 5%;
        }
        
        .rightSection>.purchaseInfo>.recipeProcessSection>ul>li {
            width: 95%;
            border-radius: 5px;
            background-color: rgb(239, 239, 239);
            margin-bottom: 2%;
            padding-left: 2%;
            padding-right: 2%;
            word-break: normal;
        }
        
        .rightSection>.purchaseInfo>.reviewSection {
            padding-bottom: 20px;
        }
        
        .rightSection>.purchaseInfo>.reviewSection>ul {
            list-style-type: none;
            line-height: 50px;
            padding-left: 5%;
        }
        
        .like>img,
        .dislike>img {
            width: 100%;
        }
        
        #pucrhase{
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
       
       .addReview{
       	  width:30px;
       	  border:0;
		  outline:0;
		  margin:0;
		  padding:0;
		  background-color:#F4EFEA; 	
       }
       
       .toy{
       	  width:100%;
       	  margin:0;
       	  padding:0;
       }
      
      
       .conditon{
          width:10%;
          height:50px;
          margin:3%;
          margin-top:1.5%;
          margin-bottom:0%;
       	  text-align:center;
       	  background:#8bd8bd;
       	  border:0;
       	  outline:0;
       	  color:white;
       	  font-size:1vw;
       	  opacity:0.7; 
       }
       
       .line2{
          padding:2.5%;
          margin:0;
          border-bottom:2px solid #D2302C;
       }
       hr{
        
        width:55%;
        border:2px solid #D2302C;
        
       }
       
       /*조건검색 이벤트 */
       .date{
       	 position:absolute;
       	 margin-top:3.4%;
       	 padding-left:53%;  
       }
       
       .dd{
       	margin-left:18%; 
       }
       
       h1{
        font-size:xx-large; 
       }
       
       .recipeName{
       	text-decoration:none;
       	color:black;  
       }     
    </style>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
    <script src="js/dropdownMenu.js"></script>
    <script src="js/favoriteBtn.js"></script>
    <script src="js/header.js"></script>
    <script src="js/footer.js"></script>
<script>
    /* addEventListener("load", () => {
        $(".rightSection").height(window.innerHeight - (90 + $("footer").outerHeight() + $("header").outerHeight())); //rightSection의 높이를 window의 높이에 따라 동적 설정
    }); */
    
   $(function(){
	   $("#datepicker").hide();
	   
	   $('#dt').click(function(){
		   $("#datepicker").show();
		   $("#datepicker").datepicker({
	    		onSelect : function(date){
					$("#datepicker").hide();
	    		}
	    	});
	   });
	   
	  	
    });
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
            <div class="dd"><button type="submit" class="conditon">1주일</button><button type="submit" class="conditon">1개월</button><button type="submit" class="conditon">3개월</button><button type="submit" class="conditon">6개월</button><button type="submit" class="conditon" id="dt">조건검색</button></div>
                <table id="pucrhase">
                	<tr><td class="line2">구매날짜</td><td class="line2">상품명</td><td class="line2">수량</td><td class="line2">구매금액</td><td class="line2">후기등록<td></tr>
                	   <c:forEach items="${requestScope.list}" var="p">
                	   		<c:forEach items="${p.purchaseDetails}" var="purchaseDetail">
                	   		<tr><td>${p.purchaseDate}</td>
                	   		<td><a href="./recipeCart.html" class="recipeName">${purchaseDetail.recipeInfo.recipeName}</a></td>
                	   		<td>${purchaseDetail.purchaseDetailQuantity}</td>
                	   		<td>${purchaseDetail.purchaseDetailQuantity*purchaseDetail.recipeInfo.recipePrice}</td>
                	   		<td>
                	   			<c:if test="${p.review.reviewComment eq null}">
                	   				<button type="submit" class="addReview" data-toggle="modal"  data-target="#reviewModal" value="${p.purchaseCode}"><img src="./img/list.png" class="toy"></button>
                	   			</c:if>
                	   		</td></tr>
                	   		</c:forEach>
                	   </c:forEach>
                </table>
            </div>
        </section>
    </div>
    <jsp:include page="./static/reviewAdd.html"></jsp:include>
    <footer>
        <p>
            © 2020 RECIPE MARKET All rights reserved.
        </p>
        <a class="topBtn">&uarr;TOP</a>
    </footer>
</body>

</html>
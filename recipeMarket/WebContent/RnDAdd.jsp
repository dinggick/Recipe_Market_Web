<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	page import="com.recipe.vo.Customer"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<c:set var="rnd" value="${requestScope.rnd}"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>RnDAdd</title>

    <link rel="icon" href="/recipeMarket/images/titlecon.png">

    <link rel="stylesheet" href="/recipeMarket/css/adminCommonSection.css">

    <link rel="stylesheet" href="/recipeMarket/css/header.css">
    <link rel="stylesheet" href="/recipeMarket/css/footer.css">
    
    <link rel="stylesheet" href="/recipeMarket/css/RnDAdd.css">
    <!-- <link rel="stylesheet" href="./css/RnDInfo.css"> -->
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <script src="/recipeMarket/js/adminCommonSection.js"></script>
    <script src="/recipeMarket/js/RnDAdd.js"></script>

	<c:choose>
		<c:when test="${userType == 'A'}">
			<script src="${contextPath}/js/header_admin.js"></script>
	    </c:when>
	    <c:otherwise>
	    	<script src="${contextPath}/js/header_rnd.js"></script>
	    </c:otherwise>
    </c:choose>
    
    <script src="/recipeMarket/js/footer.js"></script>
    <script src="/recipeMarket/js/dropdownMenu.js"></script>
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
			<c:choose>
				<c:when test="${userType == 'A'}">
					<jsp:include page="/dropdownMenu_admin.jsp"></jsp:include>
				</c:when>
				<c:otherwise>
					<jsp:include page="/dropdownMenu_rnd.jsp"></jsp:include>		
				</c:otherwise>
			</c:choose>
		</div>
    </header>

    <div class="bodySection">
    <section class="leftSection">
        <div class="titleWrapper">
            <h3>
                	계정 추가
            </h3>
        </div>

        <div class="underLineWrapper">
            <hr>
        </div>

        <div class="menuWrapper">
            <ul>
                <li>
                    <span>RnD 관리</span>
                    <ul>
                        <li><a href="/recipeMarket/RnDAdd.jsp">계정 추가</a></li>
                        <li><a href="/recipeMarket/rnd/list?currentPage=">계정 목록</a></li>
                    </ul>
                </li>
                
                <li>
                    <span>통계</span>
                    <ul>
                        <li><a href="/recipeMarket/statistics/graph1?year=2020">성별 & 연령별 구매량</a></li>
                        <li><a href="/recipeMarket/statistics/graph2?year=2020&count=10">RnD 매출 비중</a></li>
                        <li><a href="/recipeMarket/statistics/graph3?term=202006_202008&count=10">레시피 판매 순위</a></li>
                        <li><a href="/recipeMarket/rnd/search">조건별 통계 산출</a></li>
                    </ul>
            </ul>                            
        </div>
    </section>

    <section class="rightSection">
                                        
        <div class="contentsWrapper">

            <div class="infoWrapper">

                <form class="formWrapper" action="#" method="post">

                    <tr>
                        <td><input class="dataInput" type="text" id="rd_email" name="rd_email" placeholder="이메일을 입력하세요." required></td>
                        <td><span class="validId" style="color: blue">사용 가능한 이메일입니다.</span></td>
                        <td><span class="duplicatedId">이미 가입된 이메일입니다.</span></td>
                        <td><span class="invalidId">메일 형식이 올바르지 않습니다.</span></td>
                    </tr>
                    <tr>
                        <td><input class="dataInput" type="password" id="rd_pwd" name="rd_pwd" placeholder="비밀번호를 입력하세요. (8자 이상, 숫자, 영문 및 특수문자 포함)" required></td>
                        <td><span class="invalidPwd">비밀번호 형식이 올바르지 않습니다.</span></td>
                    </tr>
                    <tr>
                        <td><input class="dataInput" type="password" id="r_rd_pwd" name="r_rd_pwd" placeholder="비밀번호를 재입력하세요." required></td>
                        <td><span class="eqaulPwd">비밀번호가 일치하지 않습니다.</span></td>
                    </tr>
                    <tr>
                        <td><input class="dataInput" type="text" id="rd_manager_name" name="rd_manager_name" placeholder="이름을 입력하세요." required></td>
                    </tr>
                    <tr>
                        <td><input class="dataInput" type="text" id="rd_team_name" name="rd_team_name" placeholder="부서를 입력하세요." required></td>
                    </tr>
                    <tr>
                        <td><input class="dataInput" type="tel" id="rd_phone" name="rd_phone" placeholder="연락처를 입력하세요. ('-' 제외)" required></td>
                        <td><span class="invalidPhone">연락처 형식이 올바르지 않습니다.</span></td>
                    </tr>
                                        
                    <div class="buttonSection">
                        <button class="addBtn" type="submit" style="cursor: pointer">
                            	추가
                        </button>
                    </div>
                </form>
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
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	page import="com.recipe.vo.RnD"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<%	List<RnD> rd_list = (List)request.getAttribute("rd_list");%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>조건별 통계</title>

    <link rel="icon" href="../images/titlecon.png">

    <link rel="stylesheet" href="${contextPath}/css/adminCommonSection.css">

    <link rel="stylesheet" href="${contextPath}/css/header.css">
    <link rel="stylesheet" href="${contextPath}/css/footer.css">

    <link rel="stylesheet" href="${contextPath}/css/conditionSearch.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${contextPath}/js/adminCommonSection.js"></script>
    <script src="${contextPath}/js/conditionSearch.js"></script>

	<c:choose>
		<c:when test="${userType == 'A'}">
			<script src="${contextPath}/js/header_admin.js"></script>
	    </c:when>
	    <c:otherwise>
	    	<script src="${contextPath}/js/header_rnd.js"></script>
	    </c:otherwise>
    </c:choose>
    
    <script src="${contextPath}/js/footer.js"></script>
    <script src="${contextPath}/js/dropdownMenu.js"></script>

    <style>
    .formWrapper label {
        font-size: 1em;
        font-weight: border;

        padding-right: 20px;
    }
    table {
        height: 400px;
        width: 500px;
        background-color: darkkhaki"
    }
    .searchBtn {
	    height: 40px;
    	width: 100px;

    	color: white;
    	font-size: 1em;
    	/* font-weight: border; */
    	/* font-family: fantasy; */

    	border: none;
    	border-radius: 5px;

    	outline: none;
    	background-color: #8bd8bd;
    	opacity: 0.7;
	}
	.searchBtn:hover {
    	opacity: 1;
	}
	select {
		border-radius: 5px;
		font-size: 0.9em;
		color: black;
	}
    </style>

    <style>
        input:-webkit-autofill,
        input:-webkit-autofill:hover, 
        input:-webkit-autofill:focus,
        textarea:-webkit-autofill,
        textarea:-webkit-autofill:hover,
        textarea:-webkit-autofill:focus,
        select:-webkit-autofill,
        select:-webkit-autofill:hover,
        select:-webkit-autofill:focus {
            transition: background-color 5000s ease-in-out 0s;
        }
    </style>
    
    <script>
		function isDatetime(d) { 
			var re = /[0-9]{4}\/(0[1-9]|1[0-2])\/(0[1-9]|[1-2][0-9]|3[0-1])/;
			return re.test(d);
		}
		
    	$(function() {
        	var start_date_flg = false;
        	var end_date_flg = false;

        	$("#start_date").on("blur", function() {
        		var startDate = $("#start_date").val();
        		if (isDatetime(startDate)) {
        			start_date_flg = true;
        		} else {
        			start_date_flg = false;
        		}
        	});
    	
        	$("#end_date").on("blur", function() {
        		var endDate = $("#end_date").val();
        		if (isDatetime(endDate)) {
        			end_date_flg = true;
        		} else {
        			end_date_flg = false;
        		}      		
        	});
        	        	
        	$(".searchBtn").click(function() {
        		if (!start_date_flg || !end_date_flg) {
    				alert("날짜형식이 잘못되었습니다.")
    				return false;
    			}
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

            <span>
                	통계
            </span>

        </div>

        <div class="underLineWrapper">
            <hr> 
        </div>

        <div class="menuWrapper">
            <ul>
            	<c:if test="${userType == 'A'}">
					<jsp:include page="adminMenu.jsp"/>
				</c:if>
        	</ul>                              
        </div>

    </section>

    <section class="rightSection">                                        
        <div class="contentsWrapper">
            <div class="infoWrapper">
                <form autocomplete="off" class="formWrapper" action="${contextPath}/statistics/graph4" method="post">
                    <table>
                        <tr>
                            <td>
                            	<label for="rd_email">이메일</label>
                            </td>
                            
                            <td style="text-align: center">
                            	<select id="rd_email" name="rd_email" style="padding: 7px 20px">
                            		<c:choose>
                            			<c:when test="${userType == 'R'}">
                            				<option value="${rndAccount}">
                            					${rndAccount}
                            				</option>
                            			</c:when>
                            			<c:otherwise>
                                 			<option value="all">
                                 				전체
                                 			</option>                            				
                            				<c:forEach items="${rd_list}" var="rd">
                            				    <option value="${rd.rdEmail}">
                            						${rd.rdEmail}
                            					</option>
                            				</c:forEach>
                            			</c:otherwise>
                            		</c:choose>
                            	</select>
<!--                             	<input class="dataInput" type="text" id="rd_email" name="rd_email" value=""> -->
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                            	<label for="start_date">시작일</label>
                            </td>
                            
                            <td>
                            	<input class="dataInput" type="text" id="start_date" name="start_date" placeholder="ex) 2020/06/27">
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                            	<label for="end_date">마지막일</label>
                            </td>
                            
                            <td>
                            	<input class="dataInput" type="text" id="end_date" name="end_date" placeholder="ex) 2020/09/27">
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                            	<label for="customer_gender">성별</label>
                            </td>
                            <td style="text-align: center">
                            	<input class="dataInput" type="radio" id="customer_gender" name="customer_gender" value="MM"><span style="margin: 0px 30px">male</span>
                            	<input class="dataInput" type="radio" name="customer_gender" value="FF"><span style="margin: 0px 30px">female</span>                        
                            	<input class="dataInput" type="radio" name="customer_gender" value="MF" checked><span style="margin: 0px 30px">전체</span>                        
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                            	<label for="age_group">연령대</label>
                            </td>
                            
                            <td style="text-align: center">
                                <select id="age_group" name="age_group" style="padding: 7px 20px">
                                    <option value="0_99">전체</option>                   
                            		<option value="10_19">10대</option>
                            		<option value="20_29">20대</option>
                            		<option value="30_39">30대</option>
                            		<option value="40_49">40대</option>
                            		<option value="50_59">50대</option>
                            		<option value="60_99">60대 이상</option>                            		
                            	</select>
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                            	<label for="order_by">정렬</label>
                            </td>
                            
                            <td style="text-align: center">
                                <select id="order_by" name="order_by" style="padding: 7px 20px">
                            		<option value="2">매출 순</option>
                            		<option value="3">판매개수 순</option>
                            	</select>
                            </td>
                        
                        <tr>
                            <td>
                            	<label for="count">나타낼 개수</label>
                            </td>
                            
                            <td style="text-align: center">
                                <select id="count" name="count" style="padding: 7px 20px">
                            		<option value="10">상위 10</option>
                            		<option value="20">상위 20</option>
                            		<option value="30">상위 30</option>                         		
                            	</select>
                            </td>
                        </tr>
                                            
                    </table>
                    <button class="searchBtn" type="submit" style="cursor: pointer">검색</button>               

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
</html>>
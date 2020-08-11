<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	page import="com.recipe.vo.RnD"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<%	List<RnD> rd_list = (List)request.getAttribute("rd_list");
	RnD rd = (RnD)session.getAttribute("loginInfo");
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>RnDInfo</title>

    <link rel="icon" href="../images/titlecon.png">

    <link rel="stylesheet" href="${contextPath}/css/adminCommonSection.css">

    <link rel="stylesheet" href="${contextPath}/css/header.css">
    <link rel="stylesheet" href="${contextPath}/css/footer.css">

    <link rel="stylesheet" href="${contextPath}/css/conditionSearch.css">

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${contextPath}/js/adminCommonSection.js"></script>
    <script src="${contextPath}/js/conditionSearch.js"></script>

    <script src="${contextPath}/js/header.js"></script>
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
    	$(function() {
    	    $(".deleteBtn").on("click", function(evt) {
    	        $.ajax({
    	            url: "./remove",
    	            data: { rd_email : $("#rd_email").val() },
    	            success: function(responseObj) {
    	                if (responseObj.status == "success") {
    	                	alert("Account has been deleted");
    	                	location.href = "${contextPath}/rnd/list?currentPage=${recentPage}";
    	                } else {
    	                	alert("Account has not been deleted");
    	                }
    	            }
    	        });

    	        return false;
    	    });
    	})
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
            <div class="dropdown">
                <!-- 로그인 버튼(누르면 드롭다운 메뉴 보이도록) -->
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

    <div class="bodySection">
    
    <section class="leftSection">

        <div class="titleWrapper">

            <span>
                RnDInfo
            </span>

        </div>

        <div class="underLineWrapper">
            <hr> 
        </div>

        <div class="menuWrapper">
            <ul>
                <li>
                    <span>RnD management</span>
                    <ul>
                        <li><a href="${contextPath}/static/RnDAdd.html">AddRnD</a></li>
                        <li><a href="${contextPath}/rnd/list?currentPage=">RnDList</a></li>
                    </ul>
                </li>                
                <li>
                    <span>CRM</span>
                    <ul>
                        <li><a href="${contextPath}/statistics/graph1?year=2020">graph1</a></li>
                        <li><a href="${contextPath}/statistics/graph2?year=2020&count=10">graph2</a></li>
                        <li><a href="${contextPath}/statistics/graph3?term=202006_202008&count=10">graph3</a></li>
                    </ul>
                </li>
            </ul>                            
        </div>

    </section>

    <section class="rightSection">                                        
        <div class="contentsWrapper">
            <div class="infoWrapper">
                <form autocomplete="off" class="formWrapper" action="#" method="post">
                    <table>
                    
                        <tr>
                            <td>
                            	<label for="rd_email">rd_email</label>
                            </td>
                            
                            <td style="text-align: center">
                            	<select id="rd_email" name="rd_email" style="padding: 7px 20px">
                            		<c:choose>
                            			<c:when test="${empty rd_list}">
                            				<option value="${loginInfo}">
                            					${loginInfo}
                            				</option>
                            			</c:when>
                            			<c:otherwise>
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
                            	<label for="start_date">start_date</label>
                            </td>
                            
                            <td>
                            	<input class="dataInput" type="text" id="start_date" name="start_date" placeholder="ex) 2020/06/27">
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                            	<label for="end_date">end_date</label>
                            </td>
                            
                            <td>
                            	<input class="dataInput" type="text" id="end_date" name="end_date" placeholder="ex) 2020/09/27">
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                            	<label for="customer_gender">customer_gender</label>
                            </td>
                            <td style="text-align: center">
                            	<input class="dataInput" type="radio" id="customer_gender" name="customer_gender" value="male"><span style="margin: 0px 30px">male</span>
                            	<input class="dataInput" type="radio" name="customer_gender" value="female"><span style="margin: 0px 30px">female</span>                        
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                            	<label for="age_group">age_group</label>
                            </td>
                            
                            <td style="text-align: center">
                                <select id="age_group" name="age_group" style="padding: 7px 20px">
                            		<option value="10">10대</option>
                            		<option value="20">20대</option>
                            		<option value="30">30대</option>
                            		<option value="40">40대</option>
                            		<option value="50">50대</option>
                            		<option value="60">60대 이상</option>                            		
                            	</select>
                            </td>
                        </tr>
                                            
                    </table>                   
                    <div class="buttonSection">
                        <button class="searchBtn" type="button">
                            confirmBtn
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
</html>>
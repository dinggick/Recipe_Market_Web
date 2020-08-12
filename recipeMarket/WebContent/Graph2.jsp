<%@page import="com.recipe.pair.Pair"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>CRM</title>

    <link rel="icon" href="${contextPath}/images/titlecon.png">

    <link rel="stylesheet" href="${contextPath}/css/adminCommonSection.css">

    <link rel="stylesheet" href="${contextPath}/css/header.css">
    <link rel="stylesheet" href="${contextPath}/css/footer.css">

    <link rel="stylesheet" href="${contextPath}/css/CRM.css">
    
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${contextPath}/js/adminCommonSection.js"></script>

    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/footer.js"></script>
    <script src="${contextPath}/js/dropdownMenu.js"></script>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {

        var data = google.visualization.arrayToDataTable([
            ['rd_email', 'total_sales'],
            	<%List<Pair<String, Integer>> list = (List)request.getAttribute("data_list");
            	for (int i = 0; i < list.size(); ++i) {%>
            		<%if (i > 0)%>,
            		["<%=list.get(i).getKey()%>", <%=list.get(i).getValue()%>]
            	<%}%>
        ]);

        var options = {
            title: 'RnD 매출 비중',
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
  }
	</script>

	<script>
		$(function() {
			$("option[value=" + ${year} + "]").attr("selected", "selected");
			$("option[value=" + ${count} + "]").attr("selected", "selected");
			
			$(".selectYear, .topCount").on("change", function(evt) {
				$(evt.target).prop("selected", true);
				var url = "${contextPath}/statistics/graph2?";
				url += "year=" + $(".selectYear").val();
				url += "&count=" + $(".topCount").val();
				location.href = url;
			});
			$("form").on("submit", function(evt) {
				return false;
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
                	통계
            </span>

        </div>

        <div class="underLineWrapper">
            <hr> 
        </div>

        <div class="menuWrapper">
            <ul>
                <!-- <li>
                    <span>Customer management</span>
                    <ul>
                        <li><span>CustomerList</span></li>
                    </ul>
                </li> -->

                <li>
                    <span>RnD 관리</span>
                    <ul>
                        <li><a href="${contextPath}/static/RnDAdd.html">계정 추가</a></li>
                        <li><a href="${contextPath}/rnd/list?currentPage=">계정 목록</a></li>
                    </ul>
                </li>
                
                <li>
                    <span>통계</span>
                    <ul>
                        <li><a href="${contextPath}/statistics/graph1?year=2020">성별 & 연령별 구매량</a></li>
                        <li><a href="${contextPath}/statistics/graph2?year=2020&count=10">RnD 매출 비중</a></li>
                        <li><a href="${contextPath}/statistics/graph3?term=202006_202008&count=10">레시피 판매 순위</a></li>
                        <li><a href="${contextPath}/rnd/search">조건별 통계 산출</a></li>
                    </ul>
                </li>
            </ul>                                 
        </div>

    </section>

    <section class="rightSection">
                                        
        <div class="contentsWrapper">

            <div class="GraphWrapper">

                <form class="selectSection">

                    <select class="selectYear" name="year">
                        <option value="2020">2020</option>
                        <option value="2019">2019</option>
                        <option value="2018">2018</option>
                    </select>

                    <select class="topCount" name="count">
                        <option value="10">상위 10</option>
                        <option value="20">상위 20</option>
                        <option value="30">상위 30</option>
                    </select>
                    
                </form>

                <div class="graphApiWrapper">
                    <!-- <div id="barchart_material" style="width: 100%; height: 100%"></div> -->
                    <div id="piechart" style="width: 81%; height: 100%"></div>
                 </div>
                 
            </div>
            
    	</div>
    	             
    </section>

    </div>

    <footer>
        <p>© 2020 RECIPE MARKET All rights reserved.</p>
        <a class="topBtn">&uarr;TOP</a>
    </footer>

</body>
</html>

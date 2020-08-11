<%@page import="com.recipe.pair.Pair"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	page import="com.recipe.vo.RnD"%>

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

<%--     <script src="${contextPath}/js/CRM.js"></script> --%>
    
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
          var data = google.visualization.arrayToDataTable([
          		<%List<Pair<String, Integer>> list = (List)request.getAttribute("data_list");%>
      			[ "Number of sales"
            	<%for (int i = 0; i < list.size(); ++i) {%>
            		,            		
            		"<%=list.get(i).getKey()%>"
            	<%}%>
            	],
            	[ 0.0
                <%for (int i = 0; i < list.size(); ++i) {%>
            		,            		
            		<%=list.get(i).getValue()%>
            	<%}%>
            	]
            ]);

        var options = {
          chart: {
            title: '${rd_email}\'s recipe ranking',
            subtitle: '판매기간: ${start_date}~${end_date}, 성별: ${gender}, 연령대: ${start_age} 대',
          },
          bars: 'horizontal' // Required for Material Bar Charts.
        };

        var chart = new google.charts.Bar(document.getElementById('barchart_material'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
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
                CRM
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
                        <li><a href="${contextPath}/rnd/search">graph4</a></li>
                    </ul>
                </li>
            </ul>                                 
        </div>

    </section>

    <section class="rightSection">
                                        
        <div class="contentsWrapper">

            <div class="GraphWrapper">

                <form class="selectSection">

<!--                     <select class="selectTerm" name="term"> -->
<!--                         <option value="202006_202008">2020 Summer</option> -->
<!--                         <option value="202003_202005">2020 Spring</option> -->
<!--                         <option value="201912_202002">2019 Winter</option> -->
<!--                         <option value="201909_201911">2019 Fall</option> -->
<!--                         <option value="201906_201908">2019 Summer</option> -->
<!--                     </select> -->

<!--                     <select class="topCount" name="count"> -->
<!--                         <option value="10" selected>상위 10</option> -->
<!--                         <option value="20">상위 20</option> -->
<!--                         <option value="30">상위 30</option> -->
<!--                     </select> -->
                    
                </form>

                <div class="graphApiWrapper">
                    <div id="barchart_material" style="width: 100%; height: 100%;"></div>
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

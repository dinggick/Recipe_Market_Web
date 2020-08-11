<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.recipe.pair.Pair"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	page import="com.recipe.vo.RnD"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<%	List<Pair<Integer, Pair<String, Integer>>> list = (List)request.getAttribute("data_list");
        		
	Map<String, Integer> map = new HashMap<>();
    			
	for (int i = 1; i < 10; ++i) {
		map.put(Integer.toString(i * 10) + "M", 0);
		map.put(Integer.toString(i * 10) + "F", 0);
	}
%>
<%	for (int i = 0; i < list.size(); ++i) {
	String age_group = Integer.toString(list.get(i).getKey());
	String group_gender = list.get(i).getValue().getKey();
	Integer purchase_amount = list.get(i).getValue().getValue();
        		
	String group = age_group + group_gender;
	Integer pre_amount = map.get(group);
        		        		        		
	map.put(group, pre_amount + purchase_amount);     		
}%>

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

    <script src="${contextPath}/js/CRM.js"></script>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['구매 총액', '남', '여' ],
			<% for (int i = 1; i < 6; ++i) {
				String age_group = Integer.toString(i * 10);
				System.out.println(age_group);
			%>
				<% if (i > 1) %>,
				["<%=age_group + "대"%>", <%=map.get(age_group + "M")%>, <%=map.get(age_group + "F")%>]
			<%}%>
			,["<%="60대 이상"%>", 
				<%=map.get(60 + "M") + map.get(70 + "M") + map.get(80 + "M") + map.get(90 + "M")%>, 
				<%=map.get(50 + "F") + map.get(70 + "F") + map.get(80 + "F") + map.get(90 + "F")%>]
        ]);

        var options = {
            chart: {
                title: '성별 및 연령 별 구매총액',
                subtitle: '',
            }
        };

        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));
        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
    </script>
    
    <script>
		$(function() {
			$("option[value=" + ${year} + "]").attr("selected", "selected");
			
			$(".selectYear").on("change", function(evt) {
				$(evt.target).prop("selected", true);
				var url = "${contextPath}/statistics/graph1?";
				url += "year=" + $(".selectTerm").val();
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
                        <option value="2020" selected>2020</option>
                        <option value="2019">2019</option>
                        <option value="2018">2018</option>
                    </select>
                </form>

                <div class="graphApiWrapper">
                    <div id="columnchart_material" style="width: 100%; height: 100%;"></div>                     
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
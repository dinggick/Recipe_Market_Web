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
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${contextPath}/js/adminCommonSection.js"></script>

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
                title: '성별 & 연령별 구매량',
                subtitle: ${year} + "년",
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
            	<c:if test="${empty rndAccount}">
					<jsp:include page="adminMenu.jsp"/>
				</c:if>
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
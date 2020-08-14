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
        	<%List<Pair<String, Pair<String, Integer>>> list = (List)request.getAttribute("data_list");%>
        		[ "판매량"
            	<%for (int i = 0; i < list.size(); ++i) {%>
            		,            		
            		"<%=list.get(i).getValue().getKey() + "(" + list.get(i).getKey() + ")"%>"
            	<%}%>
            	],
            	[ 0.0
                <%for (int i = 0; i < list.size(); ++i) {%>
            		,            		
            		<%=list.get(i).getValue().getValue()%>
            	<%}%>
            	]
        ]);
        
        var options = {
          chart: {
            title: '레시피 매출 순위',
            subtitle: '판매기간: ${startMonth}/${startDate}~${endMonth}/${endDate}',
          },
          bars: 'horizontal' // Required for Material Bar Charts.
        };

        var chart = new google.charts.Bar(document.getElementById('barchart_material'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
    </script>
    
    	<script>
		$(function() {			
  			$("option[value=${startMonth}${startDate}_${endMonth}${endDate}]").attr("selected", "selected");
 			$("option[value=" + "${count}" + "]").attr("selected", "selected");
						
			$(".selectTerm, .topCount").on("change", function(evt) {
				$.ajax({
					url: "${contextPath}/statistics/graph3",
					data: { "term" : $(".selectTerm").val(), "count" : $(".topCount").val() },
					success: function(responseObj) {
						$(evt.target).prop("selected", true);
						if (responseObj.status == "fail") {
							alert(responseObj.msg);
						}
						else {
// 							alert($("option:selected").val());
							location.href = "${contextPath}/statistics/graph3?term=" + $("option:selected").val() + "&count=" + $(".topCount").val();
						}
					}
				});
// 				$(evt.target).prop("selected", true);
// 				var url = "${contextPath}/statistics/graph3?";
// 				url += "term=" + $(".selectTerm").val();
// 				url += "&count=" + $(".topCount").val();
// 				location.href = url;
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

            <h3>
                	통계
            </h3>

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

            <div class="GraphWrapper">

                <form class="selectSection">

                    <select class="selectTerm" name="term">
	                    <optgroup label="2020년">
	                        <option value="202006_202008">2020 여름</option>
	                        <option value="202003_202005">2020 봄</option>
	                    </optgroup>
	                    
	                    <optgroup label="2019년">                    
	                        <option value="201912_202002">2019 겨울</option>
	                        <option value="201909_201911">2019 가을</option>
	                        <option value="201906_201908">2019 여름</option>
	                        <option value="201903_201905">2019 봄</option>
	                    </optgroup>
	                    
	                    <optgroup label="2018년">                        
	                        <option value="201812_201902">2018 겨울</option>
	                        <option value="201809_201811">2018 가을</option>
	                        <option value="201806_201808">2018 여름</option>
	                        <option value="201803_201805">2018 봄</option>
	                    </optgroup>
                    </select>

                    <select class="topCount" name="count">
                        <option value="10" selected>상위 10</option>
                        <option value="20">상위 20</option>
                        <option value="30">상위 30</option>
                    </select>
                    
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

<%@page import="com.recipe.pair.Pair"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	page import="com.recipe.vo.RnD"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<%	List<Pair<String, Pair<Integer, Integer>>> list = (List)request.getAttribute("data_list");%>

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

<%--     <script src="${contextPath}/js/CRM.js"></script> --%>

	<c:choose>
		<c:when test="${start_age == 0}">
			<c:set var="age_aroup" value="전체"/>
		</c:when>
		<c:otherwise>
			<c:set var="age_aroup" value="${start_age}대"/>
		</c:otherwise>
	</c:choose>
    
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawStuff);

      function drawStuff() {
        var data = new google.visualization.arrayToDataTable([
          	['레시피 명', '매출', '판매량'],
			<% for (int i = 0; i < list.size(); ++i) {%>
			<%if (i > 0)%>,
			["<%=list.get(i).getKey()%>", <%=list.get(i).getValue().getKey()%>, <%=list.get(i).getValue().getValue()%>]
			<%}%>
        ]);
        
        var name = "${rd_email}";
        if (name == "all") {
        	name = "모든 RnD"
        }

        var options = {
          width: 1000,
          chart: {
              title: name + ' 가 등록한 레시피 목록',
              subtitle: '판매기간: ${start_date}~${end_date} 성별: ${gender} 연령대: ${age_aroup}',
          },
          bars: 'horizontal', // Required for Material Bar Charts.
          series: {
            0: { axis: 'distance' }, // Bind series 0 to an axis named 'distance'.
            1: { axis: 'brightness' } // Bind series 1 to an axis named 'brightness'.
          },
          axes: {
            x: {
              distance: {label: '매출'}, // Bottom x-axis.
              brightness: {side: 'top', label: '판매량'} // Top x-axis.
            }
          }
        };

      	var chart = new google.charts.Bar(document.getElementById('dual_x_div'));
      chart.draw(data, options);
    };
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
					<jsp:include page="/adminMenu.jsp"/>
				</c:if>
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
    				<div id="dual_x_div" style="width: 100%; height: 100%;"></div>
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

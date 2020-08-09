<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	page import="com.recipe.model.PageBean"%>
<%@	page import="com.recipe.vo.RnD"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<c:set var="pb" value="${requestScope.pb}"/>
<c:set var="currentPage" value="${pb.currentPage}"/>
<c:set var="list" value="${pb.list}"/>
<c:set var="startRow" value="${pb.startRow}"/>
<c:set var="totalPage" value="${pb.totalPage}"/>
<c:set var="startPage" value="${pb.startPage}"/>
<c:set var="endPage" value="${pb.endPage}"/>
<c:set var="url" value="${pb.url}"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>RnDList</title>

    <link rel="icon" href="./images/titlecon.png">

    <link rel="stylesheet" href="${contextPath}/css/adminCommonSection.css">

    <link rel="stylesheet" href="${contextPath}/css/header.css">
    <link rel="stylesheet" href="${contextPath}/css/footer.css">

    <link rel="stylesheet" href="${contextPath}/css/RnDList.css">
    
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${contextPath}/js/adminCommonSection.js"></script>

    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/footer.js"></script>
    <script src="${contextPath}/js/dropdownMenu.js"></script>

    <script src="${contextPath}/js/RnDList.js"></script>
    <script>
    	$(function() {
    		$("table > tbody img").attr({ "src": "${contextPath}" + "/img/register.png", "alt": "register" });
    		
    	});
    </script>
</head>
<body>

    <header id="header" style="background-color: #D2302C;">
        <div class="headerLeftSection" style="background-color: #D2302C;">
            <!-- <img src="./images/titlecon.png" style="width: 50px; padding-left: 10px; background-color: #D2302C;"> -->
            <h1 class="home">RECIPE MARKET</h1>
        </div>
        <div class="headerRightSection" style="background-color: #D2302C;">
            <div class="dropdown">
                <img src="${contextPath}/images/user.png" class="account">
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
                RnDList
            </span>

        </div>

        <div class="underLineWrapper">
            <hr> 
        </div>

        <div class="menuWrapper">
            <ul>
                <li>
                	<span>${contextPath}</span>
                    <span>RnD management</span>
                    <ul>
                        <li><a href="${contextPath}/static/RnDAdd.html">AddRnD</a></li>
                        <li><a href="${contextPath}/rnd/list?currentPage=">RnDList</a></li>
                    </ul>
                </li>
                
                <li>
                    <span>CRM</span>
                    <ul>
                        <li><a href="#">graph1</a></li>
                        <li><a href="#">graph2</a></li>
                    </ul>
                </li>
            </ul>                                 
        </div>

    </section>

    <section class="rightSection">
                                        
        <div class="contentsWrapper">

            <div class="listWrapper">

                    <table>

                        <thead>
                            <tr><th>number</th><th>id</th><th>team_name</th><th>show_info</th></tr>
                        </thead>

                        <tbody>
                        	<c:forEach items="${list}" var="r" varStatus="status">
                            	<tr>
                            		<td>${status.index + startRow}</td>
                            		<td>${r.rdEmail}</td>
                            		<td>${r.rdTeamName}</td>
                            		<td><img></td>
                            	</tr>
                            </c:forEach>
                        </tbody>
                        
                    </table>
                    
                    <div class="pagingSection">

						<c:if test="${startPage > 1 }">
                        	<img src="${contextPath}/img/prev2.png" alt="prev2">
						</c:if>
						
                        <c:if test="${currentPage > 1 }">
                        	<img src="${contextPath}/img/prev1.png" alt="prev1">
                        </c:if>

						<c:forEach begin="${startPage}" end="${endPage}" var="i">
							<c:choose>
                        		<c:when test="${currentPage == i}">
                        			<span style="
                        							color: #D2302C; 
                        							font-weight: border">
                        				${i}
                        			</span>				
                        		</c:when>
                        		<c:otherwise>
                        			<span>${i}</span>
                        		</c:otherwise>
                        	</c:choose>
                        </c:forEach>
                        
                        <c:if test="${totalPage > endPage }">
                        	<img src="${contextPath}/img/next1.png" alt="next1">
                       	</c:if>
                       	
                       	<c:if test="${endPage < totalPage}">
                        	<img src="${contextPath}/img/next2.png" alt="next2">
                    	</c:if>
                    	
                    </div>
            </div>
        </div>                             
    </section>

    </div>

    <footer>
        <p>
            Â© 2020 RECIPE MARKET All rights reserved.
        </p>
        <a class="topBtn">&uarr;TOP</a>
    </footer>

</body>
</html>
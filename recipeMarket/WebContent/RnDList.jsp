<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	page import="com.recipe.model.PageBean"%>
<%@	page import="com.recipe.vo.RnD"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<c:set var="pb" value="${requestScope.pb}"/>
<c:set var="currentPage" value="${pb.currentPage}"/>
<c:set var="list" value="${pb.list}"/>
<c:set var="CNT_PER_PAGE" value="${PageBean.CNT_PER_PAGE}"/>
<c:set var="CNT_PER_PAGEGROUP" value="${PageBean.CNT_PER_PAGEGROUP}"/>
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

    <script src="${contextPath}/js/RnDList.js"></script>
    <script>
    	$(function() {
    		function targetPageURL(page) {
    			return "${contextPath}/rnd/list?currentPage=" + page;
    		}
    		$("table > tbody img").attr({ "src": "${contextPath}/img/register.png", "alt": "showInfo" });
 			$("img[alt=prev1]").click(function() {
 				location.href = targetPageURL(${currentPage - 1});
 			});
    		$("img[alt=next1]").click(function() {
 				location.href = targetPageURL(${currentPage + 1});
 			});
 			$("img[alt=prev2]").click(function() {
 				location.href = targetPageURL(${startPage - CNT_PER_PAGEGROUP});
 			});
    		$("img[alt=next2]").click(function() {
 				location.href = targetPageURL(${endPage + 1});
 			});
    		$("img[alt=showInfo]").click(function(evt) {
    			var url = "${contextPath}/rnd/info?rd_email=";
    			var rd_email = $(this).parent().parent().children("td:nth-child(2)").text();
    			location.href = url + rd_email;
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
                	계정 목록
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

            <div class="listWrapper">

                    <table>

                        <thead>
                            <tr><th>번호</th><th>이메일</th><th>부서</th><th>정보보기</th></tr>
                        </thead>

                        <tbody>
                        	<c:forEach items="${list}" var="r" varStatus="status">
                        		<c:set var="cur" value="${status.index}"/>
                            	<tr>
                            		<td>${cur + startRow}</td>
                            		<td>${r.rdEmail}</td>
                            		<td>${r.rdTeamName}</td>
                            		<td><img></td>
                            	</tr>
                            </c:forEach>
                            <c:forEach begin="${cur + 1}" end="${CNT_PER_PAGE - 1}">
                            	<tr>
                            		<td colspan="4" style="color: #F4EFEA">#</td>
                            	</tr>
                            </c:forEach>
                        </tbody>
                        
                    </table>
                    
                    <div class="pagingSection">

						<c:if test="${startPage > 1}">
                        	<img src="${contextPath}/img/prev2.png" alt="prev2">
						</c:if>
						
                        <c:if test="${currentPage > 1}">
                        	<img src="${contextPath}/img/prev1.png" alt="prev1">
                        </c:if>

						<c:forEach begin="${startPage}" end="${endPage}" var="i">
							<c:choose>
                        		<c:when test="${currentPage == i}">
                        			<a href="${contextPath}/rnd/list?currentPage=${i}" style="
                        							color: #D2302C; 
                        							font-weight: border">
                        				${i}
                        			</a>				
                        		</c:when>
                        		<c:otherwise>
                        			<a href="${contextPath}/rnd/list?currentPage=${i}">${i}</a>
                        		</c:otherwise>
                        	</c:choose>
                        </c:forEach>
                        
                        <c:if test="${totalPage > endPage}">
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
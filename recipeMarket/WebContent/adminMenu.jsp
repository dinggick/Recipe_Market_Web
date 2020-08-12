<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

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
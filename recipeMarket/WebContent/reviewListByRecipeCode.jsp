<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="#" value="${#}"></c:set>

<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function()  {

}); // end of load
</script>

<!-- recipeInfo ReviewList 영역 -->
<div class="reviewSection">
<h1>후기</h1>
<ul>
    <li> <%-- 후기목록반복--%>
	    <fieldset>
	        <legend>최종국</legend>
	        <span class="reviewContent">맛있어요</span>
	    </fieldset>
	</li>
</ul>
</div>
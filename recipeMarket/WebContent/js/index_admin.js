addEventListener("load", () => {
    //main화면의 middleSection 영역의 크기를 동적으로 결정하기 위한 코드. 최초에 페이지 load시, 화면이 변경될 시에 크기를 결정한다
    $(".middleSection").css("height", screen.height - parseFloat($("header").css("height")) * 3.5);
    $(".middleSection").css("background-size", $(".middleSection").css("width") + " " + $(".middleSection").css("height"));

    //페이지 최초 접속 시 검색창에 focus를 준다
    $(".searchText").focus();
    
    $.ajax({
    	url : "/recipeMarket/recommendRecipe",
    	success : (data, textStatus, jqXHR) => {
    		$(".bottomSection").append(data);
    	}
    });
    
    $(".bottomSection").on("click", ".card", function() {
    	var recipeCode = $(this).find("input[type=hidden]").val();
    	//location.href = "/recipeMarket/recipeInfo?recipeCode="+recipeCode;
    	
    	var form = document.createElement("form");
    	form.setAttribute("method", "POST");
    	form.setAttribute("action", "/recipeMarket/recipeInfo");
    	
    	var input = document.createElement("input");
    	input.setAttribute("type", "hidden");
    	input.setAttribute("name", "recipeCode");
    	input.setAttribute("value", recipeCode);
    	form.appendChild(input);
    	document.body.appendChild(form);
    	form.submit();
    });

    
    $(".bottomSection").on("click", ".like", function(e) {
    	var recipeCode = $(this).parent().find("input[type=hidden]").val();
    	$.ajax({
    		url : "/recipeMarket/point/like",
    		data: {recipeCode: recipeCode},
    		success: (data, textStatus, jqXHR) => {
    			if(data.status == "success") {
    				alert("좋아요를 누르셨습니다");
    			} else {
    				alert("좋아요 실패 : " + data.msg);
    			}
    		}
    	});
    	return false;
    });
    
    $(".bottomSection").on("click", ".dislike", function(e) {
    	var recipeCode = $(this).parent().find("input[type=hidden]").val();
    	$.ajax({
    		url : "/recipeMarket/point/dislike",
    		data: {recipeCode: recipeCode},
    		success: (data, textStatus, jqXHR) => {
    			if(data.status == "success") {
    				alert("싫어요를 누르셨습니다");
    			} else {
    				alert("싫어요 실패 : " + data.msg);
    			}
    		}
    	});
    	return false;
    });

    $('body > div > div > div >label > a').click(function(){
    	var value = $('.searchText').val();
    	location.href = "/recipeMarket/recipeSearch?ingName="+value;    	
    });
    $('.searchText').keypress(function(event){
	     if ( event.which == 13 ) {
	    	 $('body > div > div > div > label > a').click();
	         return false;
	     }
	});


});
addEventListener("change", () => {
    //main화면의 middleSection 영역의 크기를 동적으로 결정하기 위한 코드. 최초에 페이지 load시, 화면이 변경될 시에 크기를 결정한다
    $(".middleSection").css("height", screen.height - parseFloat($("header").css("height")) * 3.5);
    $(".middleSection").css("background-size", $(".middleSection").css("width") + " " + $(".middleSection").css("height"));
});


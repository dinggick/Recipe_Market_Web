$(function(){
	 $('body > div > section.leftSection > div > a').click(function(){
	    	var value = $('.searchText').val();
	    	location.href = "/recipeMarket/recipeSearch?ingName="+value;
	    	
	    });
	 $(".rightSection").on("click", ".card", function() {
	    	var recipeCode = $(this).find("input[type=hidden]").val();
	    	
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
	 $('.searchText').keypress(function(event){
	     if ( event.which == 13 ) {
	    	 $('body > div > section.leftSection > div > a').click();
	         return false;
	     }
	});

	    
	 $(".rightSection").on("click", ".like", function() {
	 	console.log("test");
	 	var recipeCode = $(this).parent().find("input[type=hidden]").val();
	 	
	 	$.ajax({
	 		url : "/recipeMarket/point/like",
	 		data : {recipeCode : recipeCode},
	 		success : (data, textStatus, jqXHR) => {
	 			if(data.status == "success") {
	 				alert("좋아요를 누르셨습니다");
	 			} else {
	 				alert("좋아요 실패 : " + msg);
	 			}
	 		}
	 	});
	 	return false;
	 });
	 
	 $(".rightSection").on("click", ".dislike", function() {
	 	var recipeCode = $(this).parent().find("input[type=hidden]").val();
	 	
	 	$.ajax({
	 		url : "/recipeMarket/point/dislike",
	 		data : {recipeCode : recipeCode},
	 		success : (data, textStatus, jqXHR) => {
	 			if(data.status == "success") {
	 				alert("싫어요를 누르셨습니다");
	 			} else {
	 				alert("싫어요 실패 : " + msg);
	 			}
	 		}
	 	});
	 	return false;
	 });
});
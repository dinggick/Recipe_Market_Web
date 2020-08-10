$(function(){
	 $('body > div > section.leftSection > div > a').click(function(){
	    	var value = $('.searchText').val();
	    	location.href = "/recipeMarket/recipeSearch?ingName="+value;
	    	
	    });
	 $(".rightSection").on("click", ".card", function() {
	    	var recipeCode = $(this).find("input[type=hidden]").val();
	    	location.href = "/recipeMarket/recipeInfo?recipeCode="+recipeCode;
	    });
	 $('.searchText').keypress(function(event){
	     if ( event.which == 13 ) {
	    	 $('body > div > section.leftSection > div > a').click();
	         return false;
	     }
	});



});
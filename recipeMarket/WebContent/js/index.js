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
    	location.href = "/recipeMarket/recipeInfo?recipeCode="+recipeCode;
    });
});
addEventListener("change", () => {
    //main화면의 middleSection 영역의 크기를 동적으로 결정하기 위한 코드. 최초에 페이지 load시, 화면이 변경될 시에 크기를 결정한다
    $(".middleSection").css("height", screen.height - parseFloat($("header").css("height")) * 3.5);
    $(".middleSection").css("background-size", $(".middleSection").css("width") + " " + $(".middleSection").css("height"));
});

$('.searchIcon').click(function(){
	var value = $('.searchText').val();
	
});
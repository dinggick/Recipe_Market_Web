function checkFavoriteBtn(favoriteBtn) {
	if(favoriteBtn.src == "http://localhost/recipeMarket/img/filled_heart.png") return true;
	else return false;
}

window.addEventListener("load", () => {
    //즐겨찾기 이벤트 처리
	
	$(".bottomSection").on("mouseover", ".favorite", function(e){
		if(checkFavoriteBtn(e.target)) e.target.src = "http://localhost/recipeMarket/img/heart.png";
		else e.target.src = "http://localhost/recipeMarket/img/filled_heart.png";
	});
	$(".bottomSection").on("mouseout", ".favorite", function(e) {
		if(checkFavoriteBtn(e.target)) e.target.src = "http://localhost/recipeMarket/img/heart.png";
		else e.target.src = "http://localhost/recipeMarket/img/filled_heart.png";
	});
	$(".bottomSection").on("click", ".favorite", function(e) {
		if(checkFavoriteBtn(e.target)) { //즐겨찾기 되어있지 않다면 즐겨찾기 추가
			var recipeCode = $(this).parent().find("input[type=hidden]").val();
			
			$.ajax({
				url : "/recipeMarket/favorite/add",
				data : {recipeCode : recipeCode},
				success : (data, textStatus, jqXHR) => {
					if(data.status == "success") {
						alert("즐겨찾기에 추가되었습니다");
						location.reload();
					} else {
						alert("즐겨찾기 실패 : " + data.msg);
					}
				}
			});
		} else { //즐겨찾기 되어있다면 해제
			var recipeCode = $(this).parent().find("input[type=hidden]").val();
			
			$.ajax({
				url : "/recipeMarket/favorite/remove",
				data : {recipeCode : recipeCode},
				success : (data, textStatus, jqXHR) => {
					if(data.status == "success") {
						alert("즐겨찾기 해제되었습니다");
						location.reload();
					} else {
						alert("즐겨찾기 해제 실패 : " + data.msg);
					}
				}
			});
		}
		return false;
	});
});
window.addEventListener("load", () => {
    //즐겨찾기 이벤트 처리
    var $favoriteBtns = $(".favorite");
    for (const favoriteBtn of $favoriteBtns) {
        $(favoriteBtn).mouseover((e) => {
            e.target.src = "/recipeMarket/img/filled_heart.png";
        });
        $(favoriteBtn).mouseout((e) => {
            e.target.src = "/recipeMarket/img/heart.png";
        });
        $(favoriteBtn).click((e) => {
            //클릭 이벤트 처리
        });
    }
});
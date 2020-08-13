addEventListener("load", () => {
    $("header>.headerLeftSection>.home").click((e) => {
        location.href = "/recipeMarket/index_rnd.jsp";
    });
    
  //rightSection의 높이를 window의 높이에 따라 동적 설정
    var innerHeight = window.innerHeight - ($("footer").outerHeight() + $("header").outerHeight());
    if($(".rightSection").height() < innerHeight) $(".rightSection").height(window.innerHeight);
    
    if($(".bodySection").height() < innerHeight) $(".bodySection").height(window.innerHeight);
});
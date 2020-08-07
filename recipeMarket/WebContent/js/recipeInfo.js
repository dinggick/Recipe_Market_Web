addEventListener("load", () => {
    //remote control 각 항목 클릭 이벤트 처리
    $("#remoteBtnTop").click((e) => {
        window.scrollTo(0, 0);
    });
    $("#remoteBtnIng").click(() => {
        $(".ingredientSection")[0].scrollIntoView();
    });
    $("#remoteBtnProcess").click(() => {
        $(".recipeProcessSection")[0].scrollIntoView();
    });
    $("#remoteBtnReview").click(() => {
        $(".reviewSection")[0].scrollIntoView();
    });

    // $(".ad").height($(".recipeInfo").height() - $(".remoteControl").outerHeight());
    $(".ad").height($(".ad").height() * 1.05);

    $(".purchaseBtn").click((e) => {
        var quantity = prompt("수량을 입력하세요");
        if (confirm("총 가격 : " + parseFloat($(".recipePrice").html()) * quantity + "원\n구매하시겠습니까?")) {
            console.log("구매 처리");
        }
    });

    $(".cartBtn").click((e) => {
        //장바구니 추가 구현
    });
});
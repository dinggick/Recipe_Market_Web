addEventListener("load", () => {
    //card 요소에 포함되는 img의 크기(height) 지정
    var imgs = $(".card>img");
    for (const img of imgs) {
        $(img).css("height", parseFloat($(img).css("width")) * 0.7);
    }

    //card click 이벤트 처리
    var card = $(".card");
    for (const c of card) {
        $(c).click(() => {

        });
    }
});
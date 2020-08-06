addEventListener("load", () => {
    $("header>.headerLeftSection>.home").click((e) => {
        location.href = "main.html";
    });

    // 뒤로가기 버튼 클릭 이벤트 처리
    // $("header>.headerLeftSection>.prev").click((e) => {
    //     history.back();
    // });
});
addEventListener("load", () => {
    $(".rightSection").height(window.innerHeight - (60 + $("footer").outerHeight() + $("header").outerHeight())); //rightSection의 높이를 window의 높이에 따라 동적 설정

    $("input[name=id]").focus(); //페이지에 최초 접속시 아이디 입력란에 focus

    //로그인 버튼 클릭 이벤트 처리
    $("button[type=submit]").click((e) => {
        if ($("input[name=id]").val() == "") { //아이디 입력 여부 검사
            alert("아이디를 입력하세요");
            return false;
        }

        if ($("input[name=pwd]").val() == "") { //비밀번호 입력 여부 검사
            alert("비밀번호를 입력하세요");
            return false;
        }
    });
});
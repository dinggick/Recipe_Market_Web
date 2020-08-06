window.addEventListener("load", () => {
    //드롭다운 클릭 이벤트 처리
    var dropdownImg = document.querySelector(".account");
    var dropdownContent = document.querySelector(".dropdown-content");
    dropdownContent.style.display = "none";
    dropdownImg.onclick = (e) => {
        if (dropdownContent.style.display == "none") {
            e.target.style.opacity = "1.0";
            dropdownContent.style.display = "block";
        } else {
            e.target.style.opacity = "0.5";
            dropdownContent.style.display = "none";
        }
    };
    dropdownImg.onmouseover = (e) => {
        e.target.style.opacity = "1.0";
    };
    dropdownImg.onmouseout = (e) => {
        if (dropdownContent.style.display == "none")
            e.target.style.opacity = "0.5";
    };

    //드롭다운 영역 바깥의 요소를 클릭한 경우 드롭다운 메뉴를 숨기고 이미지의 투명도를 설정한다
    $(document).click((e) => {
        if ($(".dropdown").has(e.target).length == 0) {
            dropdownImg.style.opacity = "0.5";
            dropdownContent.style.display = "none";
        }
    });
});
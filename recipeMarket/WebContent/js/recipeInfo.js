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

	$(".labelLike").click(function() {
		var recipeCode = $(this).parent().find("input[type=hidden]").val();
		$.ajax({
			url : "/recipeMarket/point/like",
			data : {recipeCode : recipeCode},
			success : (data, textStatus, jqXHR) => {
				if(data.status == "success") {
    				alert("좋아요를 누르셨습니다");
    				location.reload();
    			} else {
    				alert("좋아요 실패 : " + data.msg);
    			}
    			return false;
			}
		});
	});
	
	$(".labelDisLike").click(function() {
		var recipeCode = $(this).parent().find("input[type=hidden]").val();
		$.ajax({
			url : "/recipeMarket/point/dislike",
			data : {recipeCode : recipeCode},
			success : (data, textStatus, jqXHR) => {
				if(data.status == "success") {
    				alert("싫어요를 누르셨습니다");
    				location.reload();
    			} else {
    				alert("싫어요 실패 : " + data.msg);
    			}
    			return false;
			}
		});
	});

    $(".cartBtn").click((e) => {
        //장바구니 추가 구현
    });
});
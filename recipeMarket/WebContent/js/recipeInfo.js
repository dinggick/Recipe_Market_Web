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

	var recipeCode = $("input[type=hidden]").val();

    $(".purchaseBtn").click((e) => {
    	
    });

	$(".labelLike").click(function() {
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

    $(".cartBtn").click(function(e) {
    	var quantity = $(".buttonSection>input[type=number]").val();
    	
    	$.ajax({
    		url : "/recipeMarket/cartAdd",
    		data : { recipeCode : recipeCode, quantity : quantity },
    		success : (data, textStatus, jqXHR) => {
    			if(data.status == "success") {
    				alert("장바구니에 추가되었습니다.");
    				if(confirm("장바구니를 확인하시겠습니까?")) {
    					var form = document.createElement("form");
				    	form.setAttribute("method", "POST");
				    	form.setAttribute("action", "/recipeMarket/recipeCart");
				    	document.body.appendChild(form);
				    	form.submit();
    				}
    			} else {
    				location.href = "/recipeMarket/static/login.html";
    			}
    		}
    	});
    });
    
    
    $.ajax({
    	url : "/recipeMarket/review/reviewListByRecipeCode",
    	data : {recipeCode : recipeCode},
    	success : (data, textStatus, jqXHR) => {
    		$(".recipeInfo").append(data);
    	}
    });
});
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

    $(".purchaseBtn").click(function(e){
    	var quantity = $(".buttonSection>input[type=number]").val();
    	var recipeName = $(this).parent().parent().find("h1").html();
    	var recipePrice = $(this).parent().parent().find("span").html();
    	
    	$.ajax({
    		url : "/recipeMarket/purchase",
    		data : { recipeCode : recipeCode, 
    				purchaseQuantity : quantity, 
    				recipeName : recipeName, 
    				recipePrice : recipePrice },
    		success : (data, textStatus, jqXHR) => {
    			if(data.status == "success") {
    				if(confirm("구매가 완료되었습니다. 구매 내역을 확인하시겠습니까?")) {
    					var form = document.createElement("form");
				    	form.setAttribute("method", "POST");
				    	form.setAttribute("action", "/recipeMarket/purchaseList");
				    	document.body.appendChild(form);
				    	form.submit();
    				}
    			} else {
    				if(data.msg == "loginIssue") {
    					alert("로그인이 필요합니다.");
    					location.href = "/recipeMarket/static/login.html";
    				}
    			}
    		}
    	});
    });
    
    $(".modifyBtn").click(function(e) {    	
    	var form = document.createElement("form");
    	form.setAttribute("method", "POST");
    	form.setAttribute("action", "/recipeMarket/recipeModify");
    	
    	var input = document.createElement("input");
    	input.setAttribute("type", "hidden");
    	input.setAttribute("name", "recipeCode");
    	input.setAttribute("value", recipeCode);
    	form.appendChild(input);
    	
    	document.body.appendChild(form);
    	form.submit();
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
    				if(confirm("장바구니에 추가되었습니다. 장바구니를 확인하시겠습니까?")) {
    					var form = document.createElement("form");
				    	form.setAttribute("method", "POST");
				    	form.setAttribute("action", "/recipeMarket/recipeCart");
				    	document.body.appendChild(form);
				    	form.submit();
    				}
    			} else {
    				alert("로그인이 필요합니다.");
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
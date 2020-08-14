$(() => {
    var $buttonSection = $(".buttonSection");
    $(".dataInput").attr("size", "40");

    var $dataInput = $(".dataInput");
    $dataInput.not(":first").on("focus", function(evt) {
        if (!$(this).prop("readonly"))
            $(this).css("border", "0.5px solid black");
    });

    $dataInput.on("blur change", function(evt) {
        $(this).css("border", "none");
    });

    var $infoWrapper = $(".infoWrapper");
    $infoWrapper.on("focus", "#r_rd_pwd", function(evt) {
        $(this).css("border", "0.5px solid black");
    });

    $infoWrapper.on("blur", "#r_rd_pwd", function(evt) {
        $(this).css("border", "none");
    }); 

    $buttonSection.on("click", ".reviseBtn", function(evt) {
        $dataInput.not(":first").removeAttr("readonly");
        $(this).remove();
        $(".deleteBtn").remove();

        $buttonSection.append("<button class=\"confirmBtn\" type=\"submit\" style=\"cursor: pointer\">확인</button>");
        $("input[type=password").parent().parent().after("<tr><td><label for=\"r_rd_pwd\">비밀번호 확인</label></td><td><input class=\"dataInput\" type=\"password\" id=\"r_rd_pwd\" name=\"r_rd_pwd\" size=\"40\"></td></tr>");
    });

    var rdPwd = false;
    
    $infoWrapper.on("blur", "#rd_pwd, #r_rd_pwd", function(evt) {
        if ($("#rd_pwd").val() == "" || $("#r_rd_pwd").val() == "") {
        	rdPwd = false;
            return;
        }
        
        if ($("#rd_pwd").val() != $("#r_rd_pwd").val()) {
            $("#r_rd_pwd").css("border", "0.5px solid red");
            rdPwd = false;
        }
        else {
            $("#r_rd_pwd").css("border", "none");
            rdPwd = true;
        }
    });
    
    $buttonSection.on("click", ".confirmBtn", function(evt) { 
        if (!rdPwd) {
            alert("비밀번호가 일치하지않습니다");
            $("#r_rd_pwd").css("border", "0.5px solid red");
            return false;
        }
        
        rdPwd = false;

        $dataInput.attr("readonly", "true");
        $(this).remove();

        $("#r_rd_pwd").parent().parent().remove();

        $buttonSection.append("<button class=\"reviseBtn\" type=\"button\">수정</button>");
        $buttonSection.append("<button class=\"deleteBtn\" type=\"submit\">삭제</button>");
        
        $.ajax({
            url: "./modify",
            data: $("form").serialize(),
            success: (responseObj) => {
                if (responseObj.status == "success") {
                	alert("삭제되었습니다");
                	location.href="/recipeMarket/rnd/list";
                } else {
                	alert("삭제에 실패하였습니다");
                	location.reload();
                }
            	
            }
        });
        
        return false;
    });

    $(form).on("submit", function() {
        return false;
    });
});

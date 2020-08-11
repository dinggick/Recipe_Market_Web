/* 비밀번호 유효성 검사 */
function chkPwd(pwd){
    var num = pwd.search(/[0-9]/g);
    var eng = pwd.search(/[a-z]/ig);
    var spe = pwd.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
   
    if (pwd.length < 8 || pwd.length > 20) return false;
    else if (pwd.search(/\s/) != -1) return false;
    else if (num < 0 || eng < 0 || spe < 0 ) return false;
    else return true;
}

$(() => {
    $(".dataInput").not(":first").on("focus", function(evt) {
        $(this).css("border", "0.5px solid black");
    });

    $(".dataInput").on("blur change", function(evt) {
        if ($(this).val() == "") {
            $(this).css("border", "none");
        }
        else {
            $(this).css("border", "none");
        }
    });

    $(".infoWrapper").on("focus", "#customer_re_pwd", function(evt) {
        $(this).css("border", "0.5px solid black");
    });

    $(".infoWrapper").on("blur", "#customer_re_pwd", function(evt) {
        $(this).css("border", "none");
    }); 
    
    $(".buttonSection").on("click", ".reviseBtn", function(evt) {
        $(".dataInput").not(":first").removeAttr("readonly");
        $(this).remove();
        $(".deleteBtn").remove();

        $(".buttonSection").append("<button class=\"confirmBtn\" type=\"button\">확인</button>");
        $("input[type=password").parent().parent().after("<tr><td><label for=\"customer_re_pwd\">비밀번호 확인</label></td><td><input class=\"dataInput\" type=\"password\" id=\"customer_re_pwd\" name=\"customer_re_pwd\" size=\"40\"></td></tr>");
    });

    $(".buttonSection").on("click", ".confirmBtn", function(evt) {
        $(".dataInput").attr("readonly", "true");
        $(this).remove();

        $("#customer_re_pwd").parent().parent().remove();

        $(".buttonSection").append("<button class=\"reviseBtn\" type=\"button\">수정</button>");
        $(".buttonSection").append("<button class=\"deleteBtn\" type=\"button\">삭제</button>");
    });

    $(".infoWrapper").on("blur", "#customer_pwd, #customer_re_pwd", function(evt) {
        if ($("#customer_pwd").val() == undefined || $("#customer_re_pwd").val() == undefined)
            return;
        
        if ($("#customer_pwd").val() != $("#customer_re_pwd").val())
            $("#customer_re_pwd").css("border", "0.5px solid red");

        else
            $("#customer_re_pwd").css("border", "none");
            
        if (chkPwd($("#customer_pwd").val())) {
            $(".invalidPwd").fadeOut();
        }
        else {
            $("#customer_pwd").css("border", "0.5px solid red");
            $(".invalidPwd").fadeIn();
        }
            
        if ($("#customer_pwd").val() != $("#customer_re_pwd").val()) {
            $("#customer_re_pwd").css("border", "0.5px solid red");
            $(".eqaulPwd").fadeIn();
        }
        else {
            $("#customer_re_pwd").css("border", "none");
            $(".eqaulPwd").fadeOut();
        }
    });
    
    $(".deleteBtn").on("click", function(evt) {
    	$.ajax({
    		url: "./customer/delete",
    		data: $("form").serialize(),
    		success: function(responseObj) {
    			
    		}
    	});
    });
    
    $(".buttonSection").on("click", ".deleteBtn", function(evt) {
    	$.ajax({
    		url: "./customer/delete",
    		data: $("form").serialize(),
    		success: function(responseObj) {
    			
    		}
    	});
    });
    
    $(".buttonSection").on("click", ".confirmBtn", function(evt) {
    	$.ajax({
    		url: "./customer/update",
    		data: $("form").serialize(),
    		success: function(responseObj) {
    			
    		}
    	});
    }
});

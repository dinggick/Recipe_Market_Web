/* 이메일 유효성 검사 */
function chkEmail(str) {                                                 
    var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

    if(!reg_email.test(str)) return false;
    else return true;
}       

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
//    $(".dataInput").attr("size", "30");
    
    $(".dataInput").on("focus", function(evt) {
        $(this).css("border", "0.5px solid black");
        $(this).css("background-color", "ghostwhite");
    });
   ////////////////////////////////////////////////////////////////////////
	$('.ingredientsWrapper').on("focus",'.dataInput', function(evt) {
        $(this).css("border", "0.5px solid black");
        $(this).css("background-color", "ghostwhite");
    });
   
    $('.ingredientsWrapper').on('blur change','.dataInput', function(evt) {
        if ($(this).val() == "") {
            $(this).css("background-color", "#ddd");
            $(this).css("border", "none");
        }
        else {
            $(this).css("background-color", "ghostwhite");
            $(this).css("border", "none");
        }
    });
    ////////////////////////////////////////////////////////////
       ////////////////////////////////////////////////////////////////////////
	$('.recipeProcessWrapper').on("focus",'.dataInput', function(evt) {
        $(this).css("border", "0.5px solid black");
        $(this).css("background-color", "ghostwhite");
    });
   
    $('.recipeProcessWrapper').on('blur change','.dataInput', function(evt) {
        if ($(this).val() == "") {
            $(this).css("background-color", "#ddd");
            $(this).css("border", "none");
        }
        else {
            $(this).css("background-color", "ghostwhite");
            $(this).css("border", "none");
        }
    });
    ////////////////////////////////////////////////////////////
        $(".dataInput").on("blur change", function(evt) {
        if ($(this).val() == "") {
            $(this).css("background-color", "#ddd");
            $(this).css("border", "none");
        }
        else {
            $(this).css("background-color", "ghostwhite");
            $(this).css("border", "none");
        }
    });

    $("#rd_id").on("blur", function(evt) {
        if (chkEmail($("#rd_id").val())) {
            $(".invalidId").fadeOut();
        }
        else {
            $("#rd_id").css("border", "0.5px solid red");
            $(".invalidId").fadeIn();
        }
    });

    $("#rd_pwd, #r_rd_pwd").on("blur", function(evt) {
        if ($("#rd_pwd").val() == undefined || $("#r_rd_pwd").val() == undefined) {
            return;
        }

        if (chkPwd($("#rd_pwd").val())) {
            $(".invalidPwd").fadeOut();
        }
        else {
            $("#rd_pwd").css("border", "0.5px solid red");
            $(".invalidPwd").fadeIn();
        }
        
        if ($("#rd_pwd").val() != $("#r_rd_pwd").val()) {
            $("#r_rd_pwd").css("border", "0.5px solid red");
            $(".eqaulPwd").fadeIn();
        }
        else {
            $("#r_rd_pwd").css("border", "none");
            $(".eqaulPwd").fadeOut();
        }
    });
});

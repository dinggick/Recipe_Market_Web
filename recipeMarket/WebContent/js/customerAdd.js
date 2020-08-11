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
   $(".dataInput").on("focus", function(evt) {
        $(this).css("border", "0.5px solid black");
        $(this).css("background-color", "ghostwhite");
    });

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

    $("#customer_email").on("blur", function(evt) {
        if (chkEmail($("#customer_email").val())) {
            $(".invalidId").fadeOut();
        }
        else {
            $("#customer_email").css("border", "0.5px solid red");
            $(".invalidId").fadeIn();
        }
    });

    $("#customer_pwd, #customer_re_pwd").on("blur", function(evt) {
        if ($("#customer_pwd").val() == undefined || $("#customer_re_pwd").val() == undefined) {
            return;
        }

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
   
});

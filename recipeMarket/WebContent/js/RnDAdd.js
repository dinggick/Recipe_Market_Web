function chkEmail(str) {                                                 
    var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;

    if(!reg_email.test(str)) return false;
    else return true;
}       

function chkPwd(pwd){
    var num = pwd.search(/[0-9]/g);
    var eng = pwd.search(/[a-z]/ig);
    var spe = pwd.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
   
    if (pwd.length < 8 || pwd.length > 20) return false;
    else if (pwd.search(/\s/) != -1) return false;
    else if (num < 0 || eng < 0 || spe < 0 ) return false;
    else return true;
}

function chkPhone(phone) {
    phone = phone.split('-').join('');
    var regPhone = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})$/;
    return regPhone.test(phone);
}

$(() => {
    var $dataInput = $(".dataInput");
    $dataInput.attr("size", "70");
    
    $dataInput.on("focus", function(evt) {
        $evtObj = $(this);
        $evtObj .css("border", "0.5px solid black");
        $evtObj .css("background-color", "ghostwhite");
    });

    $dataInput.on("blur change", function(evt) {
        $evtObj = $(this);
        if ($evtObj .val() == "") {
            $evtObj .css("background-color", "#ddd");
            $evtObj .css("border", "none");
        } else {
            $evtObj .css("background-color", "ghostwhite");
            $evtObj .css("border", "none");
        }
    });

    var $rdEmailInput = $("#rd_email");
    var $invalidIdSpan = $(".invalidId");
    var $duplicatedIdSpan = $(".duplicatedId");
    $rdEmailInput.on("blur", function(evt) {
        if (chkEmail($rdEmailInput.val())) {
            $invalidIdSpan.fadeOut();
        } else {
        	$rdEmailInput.css("border", "0.5px solid red");
            $invalidIdSpan.fadeIn();
        }
        $.ajax({
        	url: "rnd/find",
        	data: { "rd_email" : $rdEmailInput.val() },
        	success: (responseObj) => {
        		if (responseObj.status == "success") {
        			$duplicatedIdSpan.fadeIn();
        		} else {
        			$duplicatedIdSpan.fadeOut();
        		}
        	}
        });
    });

    var $rdPwdInput = $("#rd_pwd");
    var $RrdPwdInput = $("#r_rd_pwd");
    var $invalidPwdSpan = $(".invalidPwd");
    var $equalPwdSpan = $(".eqaulPwd");
    $("#rd_pwd, #r_rd_pwd").on("blur", function(evt) {
        if ($rdPwdInput.val() == undefined || $($RrdPwdInput).val() == undefined) {
            return;
        }

        if (chkPwd($($rdPwdInput).val())) {
            $($invalidPwdSpan).fadeOut();
        } else {
            $($rdPwdInput).css("border", "0.5px solid red");
            $($invalidPwdSpan).fadeIn();
        }
        
        if ($($rdPwdInput).val() != $($RrdPwdInput).val()) {
            $($RrdPwdInput).css("border", "0.5px solid red");
            $equalPwdSpan.fadeIn();
        } else {
            $($RrdPwdInput).css("border", "none");
            $equalPwdSpan.fadeOut();
        }
    });

    var $rdPhoneInput = $("#rd_phone");
    var $invalidPhoneSpan = $(".invalidPhone");
    $rdPhoneInput.on("blur", function(evt) {
        if ($rdPhoneInput.val == undefined) {
            return;
        }

        if (chkPhone($rdPhoneInput.val())) {
            $invalidPhoneSpan.fadeOut();
        } else {
            $rdPhoneInput.css("border", "0.5px solid red");
            $invalidPhoneSpan.fadeIn();
        }    
    });
    
    $(".dataInput").keypress(function(evt) {
		 if (evt.keyCode == 13) {
			 return false;
		 } 
    });
    
    $(".addBtn").on("click", function(evt) {
		alert($("form").serialize());
    	$.ajax({
    		url: "rnd/add",
    		method: "POST",
    		data: $("form").serialize(),
    		success: (responseObj) => {
    			alert(responseObj.status);
				location.reload();
    		}
    	});
    	return false;
    });
});

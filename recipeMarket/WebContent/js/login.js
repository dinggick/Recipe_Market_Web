//이메일 유효성 검사
function emailCheck(email) {
    return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email);
}


function openModal(){
	var modal = document.getElementById("pwdModal");
	 modal.style.display = "block";
}

window.onclick = function(event) {
	var modal = document.getElementById("pwdModal");
	if (event.target == modal) {
	    modal.style.display = "none";
	  }
}
var span = document.getElementsByClassName("close");
span.onclick = function() {
	var modal = document.getElementById("pwdModal");
	  modal.style.display = "none";
}


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

        if (!emailCheck($("input[name=id").val())) { //아이디 입력란에 입력된 이메일 주소의 유효성 검사
            alert("이메일을 정확하게 입력하세요");
            return false;
        }
    });
    $("#sendEmail").click(function(){
    	var email = $("#emailVal").val();
    	console.log(email);
    	if (!emailCheck(email)){
    		alert("이메일을 정확하게 입력하세요");
    		return false;
    	}
    	$.ajax({
    		url : "/recipeMarket/findPwd",
	 		data : {email : email},
	 		success : function(data){
	 			if (data.status == "success") {
	 				alert("비밀번호가 발송되었습니다");
	 				location.reload();
	 			} else {
	 				alert("발송중 오류가 발생하였습니다");
	 			}
	 		}
    	});
    	
    	return false;
    });
  
    
});
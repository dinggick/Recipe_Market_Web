<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>signUp</title>

<link rel="icon" href="./img/titlecon.png">

<link rel="stylesheet" href="./css/commonSection.css">

<link rel="stylesheet" href="./css/header.css">
<link rel="stylesheet" href="./css/footer.css">

<link rel="stylesheet" href="./css/customerAdd.css">

<link
	href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900"
	rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="./js/commonSection.js"></script>

<script src="./js/header.js"></script>
<script src="./js/footer.js"></script>
<script src="./js/dropdownMenu.js"></script>
<script src="./js/customerAdd.js"></script>

<style>
.inputWrapper {
	width: 336px;
	display: flex;
	flex-direction: row;
	justify-content: center;
	align-items: center;
}
</style>
<script>
	$(function() {
	    $(".infoWrapper").on("blur", "#customer_pwd, #customer_re_pwd", function(evt) {
	        if ($("#customer_pwd").val() == undefined || $("#customer_re_pwd").val() == undefined) return;
	        
	        if ($("#customer_pwd").val() != $("#customer_re_pwd").val())
	            $("#customer_re_pwd").css("border", "2px solid red");

	        else
	            $("#customer_re_pwd").css("border", "none");
	    });
	});
</script>
<script>
$(() => {
	$("form").submit(function(evt) {
		$.ajax({
			url: "./customer/register",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			method: "POST",
			data: $("form").serialize(),
			success: function(responseObj) {
				if (responseObj.status == "success") {
					alert("회원가입 성공");
					location.href = "/recipeMarket";
				} else {
					alert("회원가입 실패 : " + responseObj.msg);
				}
			}
		});
		return false;
	});
	var $postalBtObj = $("#search_zip"); 
	$postalBtObj.click(function(){
		//새창띄우기
		var searchPostURL = 'searchPost.html';
		var name = 'postal';
		var option = 'width=500,height=200, top=200, left=250';
		window.open(searchPostURL, name, option);
	});
});
</script>
</head>
<body>
	<header id="header">
		<!-- 왼쪽 영역 -->
		<div class="headerLeftSection">
			<!-- 로고(홈 버튼) -->
			<h1 class="home">RECIPE MARKET</h1>
		</div>
		<!-- 오른쪽 영역 -->
		<div class="headerRightSection">
			<jsp:include page="/dropdownMenu.jsp"></jsp:include>
		</div>
	</header>

	<div class="bodySection">

		<section class="leftSection">

			<div class="titleWrapper">

				<span> 회원가입 </span>

			</div>

			<div class="underLineWrapper">
				<hr>
			</div>


		</section>

		<section class="rightSection"
			style="background-color: #F4EFEA; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);">

			<div class="contentsWrapper">

				<div class="infoWrapper">

					<form class="formWrapper" action="./customer/register"
						method="post">
						<div class="inputWrapper">
							<tr>
								<td><input class="dataInput" type="text"
									id="customer_email" size="70" name="customer_email"
									placeholder="이메일을 입력하세요."></td>
							</tr>
						</div>

						<span class="duplicatedId" style="display: none">이미 가입된
							이메일입니다.</span> <span class="invalidId" style="display: none">메일
							형식이 올바르지 않습니다.</span>

						<div class="inputWrapper">
							<tr>
								<td><input class="dataInput" type="password"
									id="customer_pwd" size="70" name="customer_pwd"
									placeholder="비밀번호를 입력하세요. (숫자,영문,특수문자 포함 8자 이상)"></td>

							</tr>
						</div>
						<span class="invalidPwd" style="display: none">비밀번호 형식이
							올바르지 않습니다.</span>
						<div class="inputWrapper">
							<tr>
								<td><input class="dataInput" type="password"
									id="customer_re_pwd" size="70" name="customer_re_pwd"
									placeholder="비밀번호를 재입력하세요."></td>
							</tr>
						</div>
						<span class="eqaulPwd" style="display: none">비밀번호가 일치하지
							않습니다.</span>
						<div class="inputWrapper">
							<tr>
								<td><input class="dataInput" type="text" id="customer_name"
									size="70" name="customer_name" placeholder="이름을 입력하세요."></td>
							</tr>
						</div>

						<div class="inputWrapper">
							<tr>
								<td><input class="dataInput" type="text"
									id="customer_birth_date" size="70" name="customer_birth_date"
									placeholder="생년월일을 입력하세요."></td>
							</tr>
						</div>

						<div class="inputWrapper"
							style="display: flex; flex-direction: row; justify-content: space-evenly; align-items: center; width: 622px; height: 42px; border-radius: 5px; background-color: #ddd;">
							<tr>
								<td>
									<div class="radioWrapper">
										<label><input type="radio" name="customer_gender"
											value="M" class="input_radio" checked> <span>남</span>
									</div>
									</label>

									<div class="radioWrapper">
										<label><input type="radio" name="customer_gender"
											value="F" class="input_radio"> <span>여</span>
									</div>
									</label>

								</td>
							</tr>
						</div>

						<div class="inputWrapper">
							<tr>
								<td><input class="dataInput" type="tel" id="customer_phone"
									size="70" name="customer_phone" placeholder="연락처를 '-'없이 숫자만 입력하세요"></td>
							</tr>
						</div>

						<div class="inputWrapper" style="width: 800px">
							<tr>
								<td><input class="dataInput" type="text" id="customer_zip"
									size="58" name="customer_zip" placeholder="검색버튼을 눌러 우편번호를 찾으세요">
									<button id="search_zip" type="button"
										style="width: 100px; height: 42px;">검색</button></td>
							</tr>
						</div>
						<input id="buildingno" style="display: none" name="buildingno">
						<div class="inputWrapper">
							<tr>
								<td><input class="dataInput" type="text" id="customer_juso"
									size="70" name=customer_juso placeholder="주소를 입력하세요.">
							</tr>
						</div>

						<div class="inputWrapper">
							<tr>
								<td><input class="dataInput" type="text" id="customer_addr"
									size="70" name="customer_addr" placeholder="상세주소를 입력하세요.">
							</tr>
						</div>


						<button class="submitBtn" type="submit">가입</button>
					</form>
				</div>
			</div>
		</section>

	</div>

    <footer>
        <p>
            © 2020 RECIPE MARKET All rights reserved.
        </p>
        <a class="topBtn">&uarr;TOP</a>
    </footer>
</body>
</html>
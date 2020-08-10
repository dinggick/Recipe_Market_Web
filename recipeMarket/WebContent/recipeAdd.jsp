<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recipe Market - 오늘 뭐 먹지?</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">
    <link rel="icon" href="${contextPath}/img/titlecon.png">
    <link rel="stylesheet" href="${contextPath}/css/header.css">
    <link rel="stylesheet" href="${contextPath}/css/customScrollBar.css">
    <link rel="stylesheet" href="${contextPath}/css/divContent.css">
    <link rel="stylesheet" href="${contextPath}/css/contents.css">
    <link rel="stylesheet" href="${contextPath}/css/footer.css">
    <link rel="stylesheet" href="${contextPath}/css/recipeInfo.css">
    <link rel="stylesheet" href="./css/recipeAdd.css">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${contextPath}/js/jquery.form.min.js"></script>
    <script src="${contextPath}/js/dropdownMenu.js"></script>
    <script src="${contextPath}/js/favoriteBtn.js"></script>
    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/footer.js"></script>
    <script src="${contextPath}/js/recipeInfo.js"></script>
    <script src="${contextPath}/js/recipeAdd.js"></script>
    
        <script>
    	$(function(){
    		$('.ingAddBtn').click(function(){
    			var tag = ''
    			tag +='<tr>';
    			tag +='<td align="center">';
    			tag +='<input size="28" class="dataInput" type="text" id="ingredientsName" name="ingredientsName" placeholder="재료을 입력하세요.">';
    			tag +='</td>';
    			tag +='<td align="center">';
    			tag +='<input size="28" class="dataInput" type="text" id="ingredientsSize" name="ingredientsSize" placeholder="용량을 입력하세요.">';
    			tag +='</td>';
    			tag +='<td align="left">';
    			tag +='<button class="ingDeleteBtn" type="button">';
    			tag +='ingDelete';
    			tag +='</button>';
    			tag +='</td>';
    			tag +='</tr>';
    			$('.ingredientsWrapper').append(tag);
    		});
    		
    		$('.ingredientsWrapper').on('click','.ingDeleteBtn', function() {
    			if (confirm("선택하신 재료를 삭제하시겠습니까?")) {
        			var clickedRow = $(this).parent().parent();
        			clickedRow.remove();
    			}
    		});
    		
    		$('.summAddBtn').click(function(){
    			var tag = ''
    			tag +='<tr>';
    			tag +='<td colspan="2">';
    			tag +='<input size="60" class="dataInput" type="text" id="recipeSumm" name="recipeSumm" placeholder="과정을 입력하세요.">';
    			tag +='</td>';
    			tag +='<td align="left">';
    			tag +='<button class="summDeleteBtn" type="button">';
    			tag +='summDelete';
    			tag +='</button>';
    			tag +='</td>';
    			tag +='</tr>';
    			$('.recipeSummWrapper').append(tag);
    		});
    		
    		$('.recipeSummWrapper').on('click','.summDeleteBtn', function(){
				if(confirm("선택하신 과정을 삭제하시겠습니까?")){
	    			var clickedRow = $(this).parent().parent();
	    			clickedRow.remove();
				}
    		});
    		
    		$('.recipeAddBtn').click(function(){
    			//alert("recipeAdd버튼을 클릭하셨습니다.");
    			
    			console.log("레시피명", $("#recipeName").val());
    			var recipeName = $("#recipeName").val();
    			var ingName = $("input[name=ingredientsName]");
    			var ingSize = $("input[name=ingredientsSize]");
    			var summ = $("input[name=recipeSumm]");
    			var ingData ='';
    				
    			console.log($("input[name=ingredientsName]").val());
    			console.log($("input[name=ingredientsSize]").val());
    			console.log("가격", $("#recipePrice").val());
    			console.log("요리과정",$("input[name=recipeSumm]").val());
    			console.log("이미지경로",$("#imageUpload").val());
    			
    			
    			$('#multiform').ajaxForm({
    				cache: false,
    				dataType:'json',
    				beforeSubmit: function(data, frm, opt) {
    					// submit 하기전 유효성 검사하는 곳
    					console.log("유효성검사", data);
    					return true;
    				},
    				success: function(data, statusText) {
    					console.log(data);
    					if (data != null && data.status == "success") {
    						alert("등록되었습니다.");
    					} else {
    						alert("등록이 실패하였습니다.\n잠시 후 다시 시도해주세요.");
    					}
    				}
    			});
    			
    			$('#multiform').submit(); 

    			/* var formData = new FormData();
    			formData.append("recipeName", recipeName);
    			var ingName = [];
    			for (var i=0; i<ingName.length; i++) {
    				ingName.push(ingName[i].value);
    			}
    			formData.append("ingName", ingName);
    			
    			console.log(formData);
    			$.ajax({
    				url: '/recipeMarket/recipeEdit'
    				,method:'post'
    				,enctype:'multipart/form-data'
       				,processData: false
       				,contentType: false
    				,data:formData
    				,success:function(data){
    					console.log(data);
    					if (data != null && data.status == "success") {
    						alert("등록되었습니다.");
    					} else {
    						alert("등록이 실패하였습니다.\n잠시 후 다시 시도해주세요.");
    					}
    					
    				}
    			}); */
    			
    		});
    		
    	});
    </script>
    
</head>

<body>
    <header>
        <!-- 왼쪽 영역 -->
        <div class="headerLeftSection">
            <!-- 뒤로 가기 버튼 -->
            <a class="glyphicon glyphicon-chevron-left"></a>
            <!-- 로고(홈 버튼) -->
            <h1 class="home">RECIPE MARKET</h1>
        </div>
        <!-- 오른쪽 영역 -->
        <div class="headerRightSection">
            <jsp:include page="dropdownMenu.jsp"></jsp:include>
        </div>
    </header>
    <div class="divContent">
        <section class="leftSection">
            <div class="remoteControl">
                <ul>
                    <li id="remoteBtnTop">TOP</li>
                    <li id="remoteBtnIng">재료</li>
                    <li id="remoteBtnProcess">과정</li>
                    <li id="remoteBtnReview">후기</li>
                </ul>
            </div>
            <%-- <div class="ad">
                <img src="${contextPath}/img/ad1.jpg" class="adImg">
                <img src="${contextPath}/img/ad2.jpg" class="adImg">
                <img src="${contextPath}/img/ad3.jpg" class="adImg">
            </div>--%>
        </section>
        <!-- 오른쪽 영역 (화면에 따라 동적 생성 필요) -->
        <section class="rightSection">
                                        
        <div class="contentsWrapper">

            <div class="infoWrapper">

                <form id="multiform" name="multiform" class="formWrapper" action="/recipeMarket/recipeEdit" method="post" enctype="multipart/form-data">
					<table>
						<thead>
		                    <tr>
		                        <td colspan="2"><input size="60" class="dataInput" type="text" id="recipeName" name="recipeName" placeholder="레시피명을 입력하세요."></td><!-- 레시피명이 이미 존재합니다 -->
		                    </tr>
						</thead>
						<tbody class="ingredientsWrapper">
		                    <tr>
		                        <td align="center"><input size="28" class="dataInput" type="text" id="ingredientsName" name="ingredientsName" placeholder="재료을 입력하세요."></td>
		                        <td align="center"><input size="28" class="dataInput" type="text" id="ingredientsSize" name="ingredientsSize" placeholder="용량을 입력하세요."></td>
		                        <td align="left">
		                       		<button class="ingAddBtn" type="button">
		                           		ingAdd
			                        </button>
			                    </td>
		                    </tr>
						</tbody>
						<tbody>
		                    <tr>
		                        <td colspan="2"><input size="60" class="dataInput" type="text" id="recipePrice" name="recipePrice" placeholder="가격을 입력하세요."></td><!-- 숫자만 입력하세요(유효성검사) -->
		                    </tr>
						</tbody>
						<tbody class="recipeSummWrapper">
		                    <tr>
		                        <td colspan="2"><input size="60" class="dataInput" type="text" id="recipeSumm" name="recipeSumm" placeholder="과정을 입력하세요."></td>
		                        <td align="left">
		                       		<button class="summAddBtn" type="button">
		                           		summAdd
			                        </button>
			                    </td>
		                    </tr>
						</tbody>
						<tbody>
		                    <tr>
		                        <td colspan="2"><input size="60" class="dataInput" type="file" id="imageUpload" name="imageUpload" placeholder="이미지를 추가하세요."></td>
<!-- 		                        <td align="left"> -->
<!-- 		                       		<button class="imageAddBtn" type="button"> -->
<!-- 		                           		imageAdd -->
<!-- 			                        </button> -->
<!-- 			                    </td> -->
		                    </tr>
	                   </tbody>
                   </table>
                   <div class="buttonSection">
                       <button class="recipeAddBtn" type="button">
                           recipeAdd
                       </button>
                   </div>
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
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@	page import="com.recipe.vo.Customer"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<c:set var="c" value="${requestScope.info}"></c:set>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>RnDInfo</title>

    <link rel="icon" href="${contextPath}/images/titlecon.png">

    <link rel="stylesheet" href="${contextPath}/css/commonSection.css">

    <link rel="stylesheet" href="${contextPath}/css/header.css">
    <link rel="stylesheet" href="${contextPath}/css/footer.css">

    <link rel="stylesheet" href="${contextPath}/css/customerInfo.css">

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,700,900" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${contextPath}/js/CommonSection.js"></script>

    <script src="${contextPath}/js/header.js"></script>
    <script src="${contextPath}/js/footer.js"></script>
    <script src="${contextPath}/js/dropdownMenu.js"></script>
    <script src="${contextPath}/js/customerInfo.js"></script>

    <style>
    .infoWrapper {
        height: 650px;
        width: 700px;
    }
    .formWrapper label {
        font-size: 1em;
        font-weight: border;
        /* font-family: fantasy; */

        padding-right: 20px;
    }
    table {
        height: 500px;
        width: 500px;
        background-color: darkkhaki"
    }
    </style>

</head>
<body>
    <header id="header">
        <!-- 왼쪽 영역 -->
        <div class="headerLeftSection">
            <img src="./images/titlecon.png" style="width: 50px; padding-left: 10px;">
            <!-- 로고(홈 버튼) -->
            <h1 class="home">RECIPE MARKET</h1>
        </div>
        <!-- 오른쪽 영역 -->
        <div class="headerRightSection">
            <!-- 드롭다운 메뉴 -->
            <div class="dropdown">
                <!-- 사람 모양 아이콘(누르면 드롭다운 메뉴 보이도록) -->
                <img src="./images/user.png" class="account">
                <!-- 드롭다운 메뉴 구성 (동적 생성 필요) -->
                <div class="dropdown-content">
                    <a href="#">로그인</a>
                    <a href="#">Menu 2</a>
                    <a href="#">Menu 3</a>
                </div>
            </div>
        </div>
    </header>

    <div class="bodySection">
    
    <section class="leftSection">

        <div class="titleWrapper">

            <span>
                               내 정보
            </span>

        </div>

        <div class="underLineWrapper">
            <hr> 
        </div>

       

    </section>

    <section class="rightSection" style="background-color:#F4EFEA; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);">
                                        
        <div class="contentsWrapper">

            <div class="infoWrapper">

                <form class="formWrapper" action="#" method="post">

                    <table>

                        <tr ><td ><label for="customer_email">${c.customer_email}</label></td><td><input class="dataInput" type="text" id="customer_email" name="customer_email" size="40" readonly ></td></tr>
                        <tr ><td ><label for="customer_name">${c.customer_name}</label></td><td><input class="dataInput" type="text" id="customer_name" name="customer_name" size="40" readonly ></td></tr>
                        <tr ><td ><label for="customer_pwd">${c.customer_pwd}</label></td><td><input class="dataInput" type="password" id="customer_pwd" name="customer_pwd" size="40" readonly placeholder="비밀번호를 입력하세요.(8자 이상,영문 및 특수문자 포함)"></td></tr>
                        <tr ><td ><label for="customer_birth_date">${c.customer_birth_date}</label></td><td><input class="dataInput" type="text" id="customer_birth_date" name="customer_birth_date" size="40" readonly ></td></tr>
                        <tr ><td ><label for="customer_gender">${c.customer_gender}</label></td><td><input class="dataInput" type="text" id="customer_gender" name="customer_gender" size="40" readonly ></td></tr>
                        <tr ><td ><label for="customer_phone">${c.customer_phone}</label></td><td><input class="dataInput" type="tel" id="customer_phone" name="customer_phone" size="40" readonly ></td></tr>
                        <tr ><td ><label for="customer_zip">${c.customer_zip}</label></td><td><input class="dataInput" type="text" id="customer_zip" name="customer_zip" size="40" readonly></td></tr>
                        <tr ><td ><label for="customer_juso">${c.customer_juso}</label></td><td><input class="dataInput" type="text" id="customer_juso" name="customer_juso" size="40" readonly></td></tr>
                        <tr ><td ><label for="customer_addr">${c.customer_addr}</label></td><td><input class="dataInput" type="text" id="customer_addr" name="customer_addr" size="40" readonly></td></tr>
                     </table>
                    
                    <div class="buttonSection">

                        <button class="reviseBtn" type="submit">
                                                               수정
                        </button>

                        <button class="deleteBtn" type="submit">
                                                               삭제
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